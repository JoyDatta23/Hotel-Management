package hotelmanagement;

//Menu.java
import java.util.*;

public class Menu {
 private int itemId;
 String itemName;
 String category; // e.g., "Food", "Beverage"
 double price;
 boolean isDailySpecial;
 int popularity; // A metric for popular items

 static Map<Integer, Menu> menuDatabase = new HashMap<>(); // Simulates a database
 static int menuCounter = 1;

 // Constructor
 public Menu(int itemId, String itemName, String category, double price, boolean isDailySpecial, int popularity) {
     this.itemId = itemId;
     this.itemName = itemName;
     this.category = category;
     this.price = price;
     this.isDailySpecial = isDailySpecial;
     this.popularity = popularity;
 }

 // Add a new menu item
 public static void addMenuItem(String itemName, String category, double price, boolean isDailySpecial) {
     Menu newItem = new Menu(menuCounter++, itemName, category, price, isDailySpecial, 0);
     menuDatabase.put(newItem.itemId, newItem);
     System.out.println("Menu item added successfully with ID: " + newItem.itemId);
 }

 // Delete a menu item
 public static void deleteMenuItem(int itemId) {
     if (menuDatabase.remove(itemId) != null) {
         System.out.println("Menu item ID " + itemId + " removed successfully.");
     } else {
         System.out.println("Menu item not found.");
     }
 }

 // Update details of a menu item
 public static void updateMenuItemDetails(int itemId, String itemName, String category, double price, boolean isDailySpecial) {
     Menu item = menuDatabase.get(itemId);
     if (item != null) {
         item.itemName = itemName;
         item.category = category;
         item.price = price;
         item.isDailySpecial = isDailySpecial;
         System.out.println("Menu item ID " + itemId + " updated successfully.");
     } else {
         System.out.println("Menu item not found.");
     }
 }

 // Get details of a menu item
 public static void getMenuItemDetails(int itemId) {
     Menu item = menuDatabase.get(itemId);
     if (item != null) {
         System.out.println("Item ID: " + item.itemId);
         System.out.println("Name: " + item.itemName);
         System.out.println("Category: " + item.category);
         System.out.println("Price: " + item.price);
         System.out.println("Daily Special: " + item.isDailySpecial);
     } else {
         System.out.println("Menu item not found.");
     }
 }

 // Search for a menu item
 public static void searchMenuItem(String keyword) {
     System.out.println("--- Search Results for: " + keyword + " ---");
     for (Menu item : menuDatabase.values()) {
         if (item.itemName.toLowerCase().contains(keyword.toLowerCase())) {
             System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Category: " + item.category + " | Price: " + item.price);
         }
     }
 }

 // Generate menu report
 public static void generateMenuReport() {
     System.out.println("--- Menu Report ---");
     for (Menu item : menuDatabase.values()) {
         System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Category: " + item.category + " | Price: " + item.price + " | Popularity: " + item.popularity);
     }
 }

 // Apply discount to menu items
 public static void applyMenuDiscount(double discountPercentage) {
     System.out.println("Applying " + discountPercentage + "% discount to all menu items...");
     for (Menu item : menuDatabase.values()) {
         item.price -= item.price * (discountPercentage / 100);
     }
     System.out.println("Discount applied successfully.");
 }

 // Get popular items
 public static void getPopularItems(int threshold) {
     System.out.println("--- Popular Items (Popularity > " + threshold + ") ---");
     for (Menu item : menuDatabase.values()) {
         if (item.popularity > threshold) {
             System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Popularity: " + item.popularity);
         }
     }
 }

 // Validate menu data
 public static boolean validateMenuData(int itemId) {
     boolean isValid = menuDatabase.containsKey(itemId);
     System.out.println("Validation for Item ID " + itemId + ": " + isValid);
     return isValid;
 }

 // Get daily specials
 public static void getDailySpecials() {
     System.out.println("--- Daily Specials ---");
     for (Menu item : menuDatabase.values()) {
         if (item.isDailySpecial) {
             System.out.println("Item ID: " + item.itemId + " | Name: " + item.itemName + " | Price: " + item.price);
         }
     }
 }

 public static void main(String[] args) {
     // Example usage
     addMenuItem("Cheeseburger", "Food", 150.0, true);
     addMenuItem("Latte", "Beverage", 80.0, false);

     getMenuItemDetails(1);
     searchMenuItem("Latte");

     updateMenuItemDetails(1, "Veggie Burger", "Food", 140.0, true);
     getMenuItemDetails(1);

     applyMenuDiscount(10);
     getMenuItemDetails(1);

     generateMenuReport();
     getDailySpecials();

     deleteMenuItem(2);
     generateMenuReport();
 }
}
