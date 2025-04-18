package hotelmanagement;

//Inventory.java
import java.util.*;

public class Inventory {
 private int itemId;
 String itemName;
 int quantity;
 double price;
 private String supplier;

 static Map<Integer, Inventory> inventoryDatabase = new HashMap<>(); // Simulates a database
 static int itemCounter = 1;

 // Constructor
 public Inventory(int itemId, String itemName, int quantity, double price, String supplier) {
     this.itemId = itemId;
     this.itemName = itemName;
     this.quantity = quantity;
     this.price = price;
     this.supplier = supplier;
 }

 // Add a new item to the inventory
 public static void addItem(String itemName, int quantity, double price, String supplier) {
     Inventory newItem = new Inventory(itemCounter++, itemName, quantity, price, supplier);
     inventoryDatabase.put(newItem.itemId, newItem);
     System.out.println("Item added successfully with ID: " + newItem.itemId);
 }

 // Remove an item from the inventory
 public static void removeItem(int itemId) {
     if (inventoryDatabase.remove(itemId) != null) {
         System.out.println("Item ID " + itemId + " removed successfully.");
     } else {
         System.out.println("Item not found.");
     }
 }

 // Update details of an item
 public static void updateItemDetails(int itemId, String itemName, int quantity, double price, String supplier) {
     Inventory item = inventoryDatabase.get(itemId);
     if (item != null) {
         item.itemName = itemName;
         item.quantity = quantity;
         item.price = price;
         item.supplier = supplier;
         System.out.println("Item ID " + itemId + " updated successfully.");
     } else {
         System.out.println("Item not found.");
     }
 }

 // Get details of an item
 public static void getItemDetails(int itemId) {
     Inventory item = inventoryDatabase.get(itemId);
     if (item != null) {
         System.out.println("Item ID: " + item.itemId);
         System.out.println("Name: " + item.itemName);
         System.out.println("Quantity: " + item.quantity);
         System.out.println("Price: " + item.price);
         System.out.println("Supplier: " + item.supplier);
     } else {
         System.out.println("Item not found.");
     }
 }

 // Check stock of an item
 public static void checkStock(int itemId) {
     Inventory item = inventoryDatabase.get(itemId);
     if (item != null) {
         System.out.println("Stock for Item ID " + itemId + ": " + item.quantity);
     } else {
         System.out.println("Item not found.");
     }
 }

 // Reorder items
 public static void reorderItems(int itemId, int additionalQuantity) {
     Inventory item = inventoryDatabase.get(itemId);
     if (item != null) {
         item.quantity += additionalQuantity;
         System.out.println("Reordered " + additionalQuantity + " units for Item ID " + itemId);
     } else {
         System.out.println("Item not found.");
     }
 }

 // Get items with low stock
 public static void getLowStockItems(int threshold) {
     System.out.println("Items with stock below " + threshold + ":");
     for (Inventory item : inventoryDatabase.values()) {
         if (item.quantity < threshold) {
             System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Quantity: " + item.quantity);
         }
     }
 }

 // Generate inventory report
 public static void generateInventoryReport() {
     System.out.println("--- Inventory Report ---");
     for (Inventory item : inventoryDatabase.values()) {
         System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Quantity: " + item.quantity + " | Price: " + item.price);
     }
 }

 // Validate inventory data
 public static boolean validateInventoryData(int itemId) {
     return inventoryDatabase.containsKey(itemId);
 }

 // Track suppliers
 public static void trackSuppliers() {
     Set<String> suppliers = new HashSet<>();
     for (Inventory item : inventoryDatabase.values()) {
         suppliers.add(item.supplier);
     }
     System.out.println("Suppliers:");
     for (String supplier : suppliers) {
         System.out.println(supplier);
     }
 }

 public static void main(String[] args) {
     // Example usage
     addItem("Laptop", 10, 50000, "TechSupplier Inc.");
     addItem("Chair", 50, 1500, "FurnitureCo");

     getItemDetails(1);
     checkStock(1);

     updateItemDetails(1, "Gaming Laptop", 8, 60000, "TechSupplier Inc.");
     getItemDetails(1);

     getLowStockItems(10);
     reorderItems(1, 5);
     getItemDetails(1);

     generateInventoryReport();
     trackSuppliers();

     removeItem(2);
     generateInventoryReport();
 }
}
