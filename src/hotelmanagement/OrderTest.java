package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;



public class OrderTest {

	
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
        System.setOut(new PrintStream(teeStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void setUp() {
        // Clear static data before each test to avoid interference
        Order.orderDatabase.clear();
        Order.orderCounter = 1;
    }

    
    
    // Test for the create order method
    @Test
    public void testCreateOrder() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        Order order = Order.orderDatabase.get(1);

        
        //verify the order details
        assertEquals(1, order.orderId);
        assertEquals(101, order.customerId);
        assertEquals(201, order.roomId);
        assertEquals(15.99, order.totalPrice, 0.001);
    }
    
    
    

    // Test for update order method
    @Test
    public void testUpdateOrder() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        List<String> newItems = Arrays.asList("Burger", "Fries", "Coke");
        Order.updateOrder(1, newItems, 18.99);

        Order order = Order.orderDatabase.get(1);
        
        
        //check the update order details
        assertEquals("Burger, Fries, Coke", String.join(", ", order.items));
        assertEquals(18.99, order.totalPrice, 0.001);
    }

    
    
    
    // Test for cancel order method
    @Test
    public void testCancelOrder() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        
        //cancle order details verify
        Order.cancelOrder(1);
        Order order = Order.orderDatabase.get(1);
        assertEquals("Cancelled", order.status);
    }
    
    

    // Test for assign order to room method
    @Test
    public void testAssignOrderToRoom() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        //verift assign room status
        Order.assignOrderToRoom(1, 301);
        Order order = Order.orderDatabase.get(1);
        assertEquals(301, order.roomId);
    }

    
    
    // Test for validate order details method
    @Test
    public void testValidateOrderDetails() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        assertFalse(Order.validateOrderDetails(100));   //validate for non existing order details
    }
    
    

    // Test for get order details method
    @Test
    public void testGetOrderDetails() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        Order.getOrderDetails(1);

        
        //order details check 
        String output = outContent.toString();
        assertTrue(output.contains("Order ID: 1"));
        assertTrue(output.contains("Customer ID: 101"));
        assertTrue(output.contains("Room ID: 201"));
        assertTrue(output.contains("Total Price: 15.99"));
        assertTrue(output.contains("Status: Pending"));
    }

    
    
    // Test for get pending orders method
    @Test
    public void testGetPendingOrders() {
    	
        List<String> items1 = Arrays.asList("Burger", "Fries");
        List<String> items2 = Arrays.asList("Pizza", "Salad");

        Order.createOrder(101, 201, items1, 15.99);
        Order.createOrder(102, 202, items2, 20.99);

        Order.cancelOrder(2); // Cancel two number order
        Order.getPendingOrders();

        
        //pending order status test
        String output = outContent.toString();
        assertTrue(output.contains("Order ID: 1"));
        assertFalse(output.contains("Order ID: 2"));
    }

    
    
    // Test for generate order bill method
 
    @Test
    public void testGenerateOrderBill() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        
        Order.generateOrderBill(1);
        String output = outputStream.toString();
        
        
        //test for the order bill 
        assertTrue(output.contains("Order ID: 1"));
        assertTrue(output.contains("Items: Burger, Fries"));
        assertTrue(output.contains("Total Price: 15.99"));
        

    }

    
    

    // Test for get order history method
    @Test
    public void testGetOrderHistory() {
    	
        List<String> items1 = Arrays.asList("Burger", "Fries");
        List<String> items2 = Arrays.asList("Pizza", "Salad");

        Order.createOrder(101, 201, items1, 15.99);
        Order.createOrder(101, 202, items2, 20.99);
        Order.getOrderHistory(101);
        String output = outContent.toString();
        
        
        //order history verify testing
        assertTrue(output.contains("Order ID: 1"));
        assertTrue(output.contains("Order ID: 2"));
    }

    
    
    // Test for track order status method
    @Test
    public void testTrackOrderStatus() {
    	
        List<String> items = Arrays.asList("Burger", "Fries");
        Order.createOrder(101, 201, items, 15.99);

        Order.trackOrderStatus(1);
        String output = outContent.toString();
        
        
        //test for the status of the order
        assertTrue(output.contains("Order ID: 1"));
        assertTrue(output.contains("Status: Pending"));
    }
}
