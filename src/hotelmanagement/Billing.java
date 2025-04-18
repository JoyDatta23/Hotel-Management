package hotelmanagement;

//Billing.java
import java.util.*;

public class Billing {
 int billId;
 int customerId;
 double amount;
 double discount;
 String paymentStatus; // "Paid" or "Pending"
 String paymentMethod; // e.g., "Credit Card", "Cash"
 private Date billDate;

 static Map<Integer, Billing> billingDatabase = new HashMap<>(); // Simulates a database
 private static int billCounter = 1;

 // Constructor
 public Billing(int billId, int customerId, double amount, double discount, String paymentStatus, String paymentMethod, Date billDate) {
     this.billId = billId;
     this.customerId = customerId;
     this.amount = amount;
     this.discount = discount;
     this.paymentStatus = paymentStatus;
     this.paymentMethod = paymentMethod;
     this.billDate = billDate;
 }

 // Create a new bill
 public static void createBill(int customerId, double amount, double discount) {
     double finalAmount = amount - discount;
     Billing newBill = new Billing(billCounter++, customerId, finalAmount, discount, "Pending", "", new Date());
     billingDatabase.put(newBill.billId, newBill);
     System.out.println("Bill created successfully with ID: " + newBill.billId);
 }

 // Add payment details to a bill
 public static void addPaymentDetails(int billId, String paymentMethod) {
     Billing bill = billingDatabase.get(billId);
     if (bill != null && bill.paymentStatus.equals("Pending")) {
         bill.paymentMethod = paymentMethod;
         bill.paymentStatus = "Paid";
         System.out.println("Payment details added successfully for Bill ID: " + billId);
     } else {
         System.out.println("Bill not found or already paid.");
     }
 }

 // Generate invoice for a bill
 public static void generateInvoice(int billId) {
     Billing bill = billingDatabase.get(billId);
     if (bill != null) {
         System.out.println("--- Invoice ---");
         System.out.println("Bill ID: " + bill.billId);
         System.out.println("Customer ID: " + bill.customerId);
         System.out.println("Amount: " + bill.amount);
         System.out.println("Discount: " + bill.discount);
         System.out.println("Final Amount: " + (bill.amount - bill.discount));
         System.out.println("Payment Status: " + bill.paymentStatus);
         System.out.println("Payment Method: " + bill.paymentMethod);
         System.out.println("Bill Date: " + bill.billDate);
     } else {
         System.out.println("Bill not found.");
     }
 }

 // Get details of a bill
 public static void getBillDetails(int billId) {
     Billing bill = billingDatabase.get(billId);
     if (bill != null) {
         System.out.println("Bill ID: " + bill.billId);
         System.out.println("Customer ID: " + bill.customerId);
         System.out.println("Amount: " + bill.amount);
         System.out.println("Discount: " + bill.discount);
         System.out.println("Payment Status: " + bill.paymentStatus);
         System.out.println("Payment Method: " + bill.paymentMethod);
         System.out.println("Bill Date: " + bill.billDate);
     } else {
         System.out.println("Bill not found.");
     }
 }

 // Apply discounts to a bill
 public static void applyDiscounts(int billId, double discount) {
     Billing bill = billingDatabase.get(billId);
     if (bill != null) {
         bill.discount += discount;
         bill.amount -= discount;
         System.out.println("Discount applied successfully to Bill ID: " + billId);
     } else {
         System.out.println("Bill not found.");
     }
 }

 // Process refund for a bill
 public static void processRefund(int billId, double refundAmount) {
     Billing bill = billingDatabase.get(billId);
     if (bill != null && bill.paymentStatus.equals("Paid")) {
         System.out.println("Refund of " + refundAmount + " processed for Bill ID: " + billId);
     } else {
         System.out.println("Bill not found or not paid yet.");
     }
 }

 // Validate payment method
 public static boolean validatePaymentMethod(String paymentMethod) {
     List<String> validMethods = Arrays.asList("Credit Card", "Cash", "Debit Card", "Online");
     return validMethods.contains(paymentMethod);
 }

 // Track pending payments
 public static void trackPendingPayments() {
     for (Billing bill : billingDatabase.values()) {
         if (bill.paymentStatus.equals("Pending")) {
             System.out.println("Pending Bill ID: " + bill.billId);
         }
     }
 }

 // Generate a payment report
 public static void generatePaymentReport() {
     System.out.println("--- Payment Report ---");
     for (Billing bill : billingDatabase.values()) {
         System.out.println("Bill ID: " + bill.billId + " | Amount: " + bill.amount + " | Status: " + bill.paymentStatus);
     }
 }

 // Get payment history for a customer
 public static void getPaymentHistory(int customerId) {
     for (Billing bill : billingDatabase.values()) {
         if (bill.customerId == customerId) {
             System.out.println("Bill ID: " + bill.billId + " | Amount: " + bill.amount + " | Status: " + bill.paymentStatus);
         }
     }
 }

 public static void main(String[] args) {
     // Example usage
     createBill(1, 1000, 50);
     createBill(2, 500, 20);

     addPaymentDetails(1, "Credit Card");
     generateInvoice(1);

     applyDiscounts(2, 30);
     getBillDetails(2);

     processRefund(1, 100);
     trackPendingPayments();

     generatePaymentReport();
     getPaymentHistory(1);
 }
}

