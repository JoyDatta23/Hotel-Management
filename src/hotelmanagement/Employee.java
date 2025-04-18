package hotelmanagement;

//Employee.java
import java.util.*;

public class Employee {
 private int employeeId;
 private String name;
 private String position;
 private double salary;
 private String shift;
 private static Map<Integer, Employee> employeeDatabase = new HashMap<>(); // Simulates a database

 // Constructor
 public Employee(int employeeId, String name, String position, double salary, String shift) {
     this.employeeId = employeeId;
     this.name = name;
     this.position = position;
     this.salary = salary;
     this.shift = shift;
 }

 // Adds a new employee
 public static void addEmployee(int employeeId, String name, String position, double salary, String shift) {
     if (employeeDatabase.containsKey(employeeId)) {
         System.out.println("Employee already exists.");
     } else {
         Employee newEmployee = new Employee(employeeId, name, position, salary, shift);
         employeeDatabase.put(employeeId, newEmployee);
         System.out.println("Employee added successfully.");
     }
 }

 // Deletes an existing employee
 public static void deleteEmployee(int employeeId) {
     if (employeeDatabase.remove(employeeId) != null) {
         System.out.println("Employee deleted successfully.");
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Updates employee details
 public static void updateEmployeeDetails(int employeeId, String newName, String newPosition, double newSalary) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         employee.name = newName;
         employee.position = newPosition;
         employee.salary = newSalary;
         System.out.println("Employee details updated successfully.");
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Gets employee details
 public static void getEmployeeDetails(int employeeId) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         System.out.println("Employee ID: " + employee.employeeId);
         System.out.println("Name: " + employee.name);
         System.out.println("Position: " + employee.position);
         System.out.println("Salary: " + employee.salary);
         System.out.println("Shift: " + employee.shift);
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Assigns a shift to an employee
 public static void assignShift(int employeeId, String shift) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         employee.shift = shift;
         System.out.println("Shift assigned successfully.");
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Gets an employee's shift
 public static void getEmployeeShift(int employeeId) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         System.out.println("Employee Shift: " + employee.shift);
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Gets employee salary details
 public static void getEmployeeSalaryDetails(int employeeId) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         System.out.println("Employee Salary: " + employee.salary);
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Calculates the salary of an employee (e.g., adding bonuses or deductions)
 public static void calculateSalary(int employeeId, double bonus, double deduction) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         double finalSalary = employee.salary + bonus - deduction;
         System.out.println("Final Salary after adjustments: " + finalSalary);
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Promotes an employee (e.g., changes position and increases salary)
 public static void promoteEmployee(int employeeId, String newPosition, double salaryIncrease) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         employee.position = newPosition;
         employee.salary += salaryIncrease;
         System.out.println("Employee promoted successfully.");
     } else {
         System.out.println("Employee not found.");
     }
 }

 // Generates a simple report for an employee
 public static void generateEmployeeReport(int employeeId) {
     Employee employee = employeeDatabase.get(employeeId);
     if (employee != null) {
         System.out.println("--- Employee Report ---");
         System.out.println("Employee ID: " + employee.employeeId);
         System.out.println("Name: " + employee.name);
         System.out.println("Position: " + employee.position);
         System.out.println("Salary: " + employee.salary);
         System.out.println("Shift: " + employee.shift);
     } else {
         System.out.println("Employee not found.");
     }
 }

 public static void main(String[] args) {
     // Example usage
     addEmployee(1, "Alice", "Manager", 5000, "Morning");
     addEmployee(2, "Bob", "Receptionist", 3000, "Evening");

     getEmployeeDetails(1);
     assignShift(1, "Night");
     getEmployeeShift(1);

     calculateSalary(2, 500, 100);
     promoteEmployee(2, "Senior Receptionist", 700);
     getEmployeeDetails(2);

     generateEmployeeReport(1);
     deleteEmployee(1);
     getEmployeeDetails(1);
 }
}
