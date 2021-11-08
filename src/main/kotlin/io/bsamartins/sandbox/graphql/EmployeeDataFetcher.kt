package io.bsamartins.sandbox.graphql

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory


@DgsComponent
class EmployeeDataFetcher {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val employees: List<Employee>
    private val employeeManagers: Map<Int, Employee>

    init {
        val e1 = Employee(
            id = 1,
            name = "Tom Cruise",
            role = "CEO",
            salary = 130_000.0,
        )
        val e2 = Employee(
            id = 2,
            name = "Bandit",
            role = "Manager",
            salary = 90_000.0,
        )
        employees = listOf(e1, e2)
        employeeManagers = mapOf(e2.id to e1)
    }

    @DgsQuery
    fun employees(): List<Employee> {
        logger.info("Fetching employee")
        return employees
    }

    @DgsData(parentType = "Employee", field = "manager")
    fun manager(dfe: DgsDataFetchingEnvironment): Employee? {
        val e: Employee = dfe.getSource()
        logger.info("Fetching manager for employee: {}", e)
        return employeeManagers[e.id]
    }
}

data class Employee(
    val id: Int,
    val name: String,
    val role: String,
    val salary: Double
)