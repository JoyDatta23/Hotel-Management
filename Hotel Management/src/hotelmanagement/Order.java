package hotelmanagement;

import java.util.*;

public class Order {
    int orderId;
    int customerId;
    int roomId;
    String status; // e.g., "Pending", "Completed", "Cancelled"
    List<String> items;
    double totalPrice;
    private Date orderDate;

    static Map<Integer, Order> orderDatabase = new HashMap<>(); // Simulates a database
    static int orderCounter = 1;

    // Constructor
    public Order(int orderId, int customerId, int roomId, String status, List<String> items, double totalPrice, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.status = status;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Create a new order
    public static void createOrder(int customerId, int roomId, List<String> items, double totalPrice) {
        Order newOrder = new Order(orderCounter++, customerId, roomId, "Pending", items, totalPrice, new Date());
        orderDatabase.put(newOrder.orderId, newOrder);
        System.out.println("Order created successfully with ID: " + newOrder.orderId);
    }

    // Update an existing order
    public static void updateOrder(int orderId, List<String> newItems, double newTotalPrice) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            order.items = newItems;
            order.totalPrice = newTotalPrice;
            System.out.println("Order ID " + orderId + " updated successfully.");
        } else {
            System.out.println("Order not found.");
        }
    }

    // Cancel an order
    public static void cancelOrder(int orderId) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            order.status = "Cancelled";
            System.out.println("Order ID " + orderId + " has been cancelled.");
        } else {
            System.out.println("Order not found.");
        }
    }

    // Get details of an order
    public static void getOrderDetails(int orderId) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.orderId);
            System.out.println("Customer ID: " + order.customerId);
            System.out.println("Room ID: " + order.roomId);
            System.out.println("Status: " + order.status);
            System.out.println("Items: " + String.join(", ", order.items));
            System.out.println("Total Price: " + order.totalPrice);
            System.out.println("Order Date: " + order.orderDate);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Get all pending orders
    public static void getPendingOrders() {
        System.out.println("Pending Orders:");
        for (Order order : orderDatabase.values()) {
            if (order.status.equals("Pending")) {
                System.out.println("Order ID: " + order.orderId + ", Customer ID: " + order.customerId);
            }
        }
    }

    // Generate bill for an order
    public static void generateOrderBill(int orderId) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            System.out.println("--- Order Bill ---");
            System.out.println("Order ID: " + order.orderId);
            System.out.println("Items: " + String.join(", ", order.items));
            System.out.println("Total Price: " + order.totalPrice);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Get order history for a customer
    public static void getOrderHistory(int customerId) {
        System.out.println("Order History for Customer ID: " + customerId);
        for (Order order : orderDatabase.values()) {
            if (order.customerId == customerId) {
                System.out.println("Order ID: " + order.orderId + ", Status: " + order.status);
            }
        }
    }

    // Track the status of an order
    public static void trackOrderStatus(int orderId) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            System.out.println("Order ID: " + orderId + ", Status: " + order.status);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Assign an order to a room
    public static void assignOrderToRoom(int orderId, int roomId) {
        Order order = orderDatabase.get(orderId);
        if (order != null) {
            order.roomId = roomId;
            System.out.println("Order ID " + orderId + " assigned to Room ID " + roomId);
        } else {
            System.out.println("Order not found.");
        }
    }

    // Validate order details
    public static boolean validateOrderDetails(int orderId) {
        return orderDatabase.containsKey(orderId);
    }

    public static void main(String[] args) {
        // Example usage
        createOrder(101, 201, Arrays.asList("Burger", "Fries", "Coke"), 15.99);
        createOrder(102, 202, Arrays.asList("Pizza", "Salad"), 20.50);

        getOrderDetails(1);
        updateOrder(1, Arrays.asList("Burger", "Fries", "Pepsi"), 16.49);
        getOrderDetails(1);

        getPendingOrders();
        generateOrderBill(1);

        assignOrderToRoom(1, 301);
        getOrderDetails(1);

        cancelOrder(2);
        trackOrderStatus(2);

        getOrderHistory(101);
    }
}


