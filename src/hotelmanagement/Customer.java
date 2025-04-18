package hotelmanagement;

//Customer.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Customer {
 private int customerId;
 String name;
 String email;
 String phone;
 private boolean isMember;
 private List<String> bookingHistory;
 private List<String> feedback;
 static Map<Integer, Customer> customerDatabase = new HashMap<>(); // Simulates a database

 // Constructor
 public Customer(int customerId, String name, String email, String phone, boolean isMember) {
     this.customerId = customerId;
     this.name = name;
     this.email = email;
     this.phone = phone;
     this.isMember = isMember;
     this.bookingHistory = new ArrayList<>();
     this.feedback = new ArrayList<>();
 }

 // Adds a new customer
 public static void addCustomer(int customerId, String name, String email, String phone, boolean isMember) {
     if (customerDatabase.containsKey(customerId)) {
         System.out.println("Customer already exists.");
     } else {
         Customer newCustomer = new Customer(customerId, name, email, phone, isMember);
         customerDatabase.put(customerId, newCustomer);
         System.out.println("Customer added successfully.");
     }
 }

 // Deletes an existing customer
 public static void deleteCustomer(int customerId) {
     if (customerDatabase.remove(customerId) != null) {
         System.out.println("Customer deleted successfully.");
     } else {
         System.out.println("Customer not found.");
     }
 }

 // Updates customer details
 public static void updateCustomerDetails(int customerId, String newName, String newEmail, String newPhone) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         customer.name = newName;
         customer.email = newEmail;
         customer.phone = newPhone;
         System.out.println("Customer details updated successfully.");
     } else {
         System.out.println("Customer not found.");
     }
 }

 // Gets customer details
 public static void getCustomerDetails(int customerId) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         System.out.println("Customer ID: " + customer.customerId);
         System.out.println("Name: " + customer.name);
         System.out.println("Email: " + customer.email);
         System.out.println("Phone: " + customer.phone);
         System.out.println("Membership: " + (customer.isMember ? "Member" : "Non-member"));
     } else {
         System.out.println("Customer not found.");
     }
 }

 // Searches for a customer by name
 public static void searchCustomer(String name) {
     boolean found = false;
     for (Customer customer : customerDatabase.values()) {
         if (customer.name.equalsIgnoreCase(name)) {
             System.out.println("Found Customer ID: " + customer.customerId + ", Name: " + customer.name);
             found = true;
         }
     }
     if (!found) {
         System.out.println("Customer not found.");
     }
 }

 // Gets booking history of a customer
 public static void getBookingHistory(int customerId) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         System.out.println("Booking History for Customer ID " + customerId + ": " + customer.bookingHistory);
     } else {
         System.out.println("Customer not found.");
     }
 }

 // Checks if the customer is a member
 public static boolean checkCustomerMembership(int customerId) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         return customer.isMember;
     } else {
         System.out.println("Customer not found.");
         return false;
     }
 }

 // Applies a discount to a customer based on membership
 public static void applyDiscount(int customerId, double amount) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         if (customer.isMember) {
             System.out.println("Discounted amount: " + (amount * 0.9)); // 10% discount for members
         } else {
             System.out.println("No discount applicable. Amount: " + amount);
         }
     } else {
         System.out.println("Customer not found.");
     }
 }

 // Validates customer identity using email and phone
 public static boolean validateCustomerIdentity(String email, String phone) {
     for (Customer customer : customerDatabase.values()) {
         if (customer.email.equalsIgnoreCase(email) && customer.phone.equals(phone)) {
             return true;
         }
     }
     System.out.println("Customer identity could not be validated.");
     return false;
 }

 // Gets feedback from a customer
 public static void getFeedbackFromCustomer(int customerId) {
     Customer customer = customerDatabase.get(customerId);
     if (customer != null) {
         System.out.println("Feedback from Customer ID " + customerId + ": " + customer.feedback);
     } else {
         System.out.println("Customer not found.");
     }
 }

 public static void main(String[] args) {
     // Example usage
     addCustomer(1, "John Doe", "john@example.com", "1234567890", true);
     addCustomer(2, "Jane Smith", "jane@example.com", "0987654321", false);
     getCustomerDetails(1);
     updateCustomerDetails(1, "John Updated", "john_updated@example.com", "1112223333");
     getCustomerDetails(1);
     searchCustomer("Jane Smith");
     checkCustomerMembership(1);
     applyDiscount(1, 100.0);
     deleteCustomer(2);
     getCustomerDetails(2);
 }
}

