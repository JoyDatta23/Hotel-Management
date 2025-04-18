package hotelmanagement;

import static org.junit.Assert.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class InventoryTest {
	
	 static Map<Integer, Inventory> inventoryDatabase = new HashMap<>();
	 static int itemCounter = 1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream teeStream = new PrintStream(new PrintStream(outContent) {
        @Override
        public void write(byte[] b, int off, int len) {
            originalOut.write(b, off, len); // Print to the original console
            super.write(b, off, len);       // Capture in outContent
        }
    });

    @Before
    public void setUpStreams() {
        System.setOut(teeStream);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void setUp() {
        // Clear static data before each test to avoid interference
        Inventory.inventoryDatabase.clear();
        Inventory.itemCounter = 1;
    }

    // Test addItem
    @Test
    public void testAddItem() {
        System.out.println("Running testAddItem...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.addItem("Chair", 50, 1500, "FurnitureCo");

        assertEquals(2, Inventory.inventoryDatabase.size());
        assertTrue(Inventory.inventoryDatabase.containsKey(1));
        assertTrue(Inventory.inventoryDatabase.containsKey(2));

        System.out.println(outContent.toString());
    }

    // Test removeItem
    @Test
    public void testRemoveItem() {
        System.out.println("Running testRemoveItem...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.removeItem(1);

        assertFalse(Inventory.inventoryDatabase.containsKey(1));
        System.out.println(outContent.toString());
    }

    @Test
    public void testRemoveItemInvalidId() {
        System.out.println("Running testRemoveItemInvalidId: ");
        Inventory.removeItem(999);

        String output = outContent.toString();
        assertTrue(output.contains("Item not found."));
        System.out.println(output);
    }

    // Test case for updateItemDetails
    @Test
    public void testUpdateItemDetails() {
        System.out.println("Running testUpdateItemDetails: ");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.updateItemDetails(1, "Gaming Laptop", 8, 60000, "TechSupplier Inc.");

        Inventory item = Inventory.inventoryDatabase.get(1); // Access the updated item.
        assertEquals("Gaming Laptop", item.itemName);  // Validate updated itemName.
        assertEquals(8, item.quantity);  // Validate updated quantity.
        assertEquals(60000, item.price, 0.01);  // Validate updated price.

        System.out.println(outContent.toString());
    }


    // Test getItemDetails
    @Test
    public void testGetItemDetails() {
        System.out.println("Running testGetItemDetails: ");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.getItemDetails(1);

        String output = outContent.toString();
        assertTrue(output.contains("Laptop"));
        System.out.println(output);
    }

    @Test
    public void testGetItemDetailsInvalidId() {
        System.out.println("Running testGetItemDetailsInvalidId: ");
        Inventory.getItemDetails(999);

        String output = outContent.toString();
        assertTrue(output.contains("Item not found."));
        System.out.println(output);
    }

    // Test checkStock
    @Test
    public void testCheckStock() {
        System.out.println("Running testCheckStock...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.checkStock(1);

        String output = outContent.toString();
        assertTrue(output.contains("Stock for Item ID 1: 10"));
        System.out.println(output);
    }

    @Test
    public void testCheckStockInvalidId() {
        System.out.println("Running testCheckStockInvalidId...");
        Inventory.checkStock(999);

        String output = outContent.toString();
        assertTrue(output.contains("Item not found."));
        System.out.println(output);
    }

    // Test reorderItems
    @Test
    public void testReorderItems() {
        System.out.println("Running testReorderItems...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.reorderItems(1, 5);

        assertEquals(15, Inventory.inventoryDatabase.get(1).quantity);
        System.out.println(outContent.toString());
    }

    @Test
    public void testReorderItemsInvalidId() {
        System.out.println("Running testReorderItemsInvalidId...");
        Inventory.reorderItems(999, 5);

        String output = outContent.toString();
        assertTrue(output.contains("Item not found."));
        System.out.println(output);
    }

    // Test getLowStockItems
    @Test
    public void testGetLowStockItems() {
        System.out.println("Running testGetLowStockItems...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.addItem("Chair", 5, 1500, "FurnitureCo");
        Inventory.getLowStockItems(10);

        String output = outContent.toString();
        assertTrue(output.contains("Chair"));
        System.out.println(output);
    }

    // Test generateInventoryReport
    @Test
    public void testGenerateInventoryReport() {
        System.out.println("Running testGenerateInventoryReport...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.addItem("Chair", 50, 1500, "FurnitureCo");
        Inventory.generateInventoryReport();

        String output = outContent.toString();
        assertTrue(output.contains("Laptop"));
        assertTrue(output.contains("Chair"));
        System.out.println(output);
    }

    // Test validateInventoryData
    @Test
    public void testValidateInventoryData() {
        System.out.println("Running testValidateInventoryData...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        assertTrue(Inventory.validateInventoryData(1));
        assertFalse(Inventory.validateInventoryData(999));

        System.out.println(outContent.toString());
    }

    // Test trackSuppliers
    @Test
    public void testTrackSuppliers() {
        System.out.println("Running testTrackSuppliers...");
        Inventory.addItem("Laptop", 10, 50000, "TechSupplier Inc.");
        Inventory.addItem("Chair", 50, 1500, "FurnitureCo");
        Inventory.trackSuppliers();

        String output = outContent.toString();
        assertTrue(output.contains("TechSupplier Inc."));
        assertTrue(output.contains("FurnitureCo"));
        System.out.println(output);
    }
}
