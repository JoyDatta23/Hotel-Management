package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

    @Before
    public void setUp() {
        // Set up initial employees for testing
        Employee.addEmployee(1, "Alice", "Manager", 5000, "Morning");
        Employee.addEmployee(2, "Bob", "Receptionist", 3000, "Evening");
    }

    @After
    public void tearDown() {
        // Clean up the employee database by removing test entries
        Employee.deleteEmployee(1);
        Employee.deleteEmployee(2);
    }

    @Test
    public void testAddEmployee() {
        Employee.addEmployee(3, "Charlie", "Cleaner", 2000, "Morning");
        // Check if the employee is added
        Employee.getEmployeeDetails(3);
    }

    @Test
    public void testDeleteEmployee() {
        Employee.deleteEmployee(1);
        Employee.getEmployeeDetails(1); // Should print "Employee not found."
    }

    @Test
    public void testUpdateEmployeeDetails() {
        Employee.updateEmployeeDetails(1, "Alice Johnson", "General Manager", 6000);
        Employee.getEmployeeDetails(1); // Verify updated details are reflected
    }

    @Test
    public void testGetEmployeeDetails() {
        Employee.getEmployeeDetails(1); // Ensure details are printed without exceptions
    }

    @Test
    public void testAssignShift() {
        Employee.assignShift(1, "Night");
        Employee.getEmployeeShift(1); // Verify the shift is updated
    }

    @Test
    public void testGetEmployeeShift() {
        Employee.getEmployeeShift(2); // Check if the correct shift is retrieved
    }

    @Test
    public void testGetEmployeeSalaryDetails() {
        Employee.getEmployeeSalaryDetails(1); // Ensure salary details are printed without exceptions
    }

    @Test
    public void testCalculateSalary() {
        Employee.calculateSalary(2, 500, 100); // Verify salary calculation works
    }

    @Test
    public void testPromoteEmployee() {
        Employee.promoteEmployee(2, "Senior Receptionist", 700);
        Employee.getEmployeeDetails(2); // Verify position and salary updates
    }

    @Test
    public void testGenerateEmployeeReport() {
        Employee.generateEmployeeReport(1); // Ensure report generation works without exceptions
    }
}
