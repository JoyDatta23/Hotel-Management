package hotelmanagement;

import java.util.*;

public class Maintenance {
    private int requestId;
    private String description;
    String status; // e.g., "Pending", "In Progress", "Completed"
    String scheduledDate;
    private double cost;
    String assignedStaff;

    static Map<Integer, Maintenance> maintenanceDatabase = new HashMap<>(); // Simulates a database
    static int requestCounter = 1;

    // Constructor
    public Maintenance(int requestId, String description, String status, String scheduledDate, double cost, String assignedStaff) {
        this.requestId = requestId;
        this.description = description;
        this.status = status;
        this.scheduledDate = scheduledDate;
        this.cost = cost;
        this.assignedStaff = assignedStaff;
    }

    // Log a new maintenance request
    public static void logMaintenanceRequest(String description, String scheduledDate, double cost) {
        Maintenance newRequest = new Maintenance(requestCounter++, description, "Pending", scheduledDate, cost, "");
        maintenanceDatabase.put(newRequest.requestId, newRequest);
        System.out.println("Maintenance request logged successfully with ID: " + newRequest.requestId);
    }

    // Update the status of a maintenance request
    public static void updateMaintenanceStatus(int requestId, String newStatus) {
        Maintenance request = maintenanceDatabase.get(requestId);
        if (request != null) {
            request.status = newStatus;
            System.out.println("Request ID " + requestId + " status updated to: " + newStatus);
        } else {
            System.out.println("Maintenance request not found.");
        }
    }

    // Get details of a maintenance request
    public static void getMaintenanceDetails(int requestId) {
        Maintenance request = maintenanceDatabase.get(requestId);
        if (request != null) {
            System.out.println("Request ID: " + request.requestId);
            System.out.println("Description: " + request.description);
            System.out.println("Status: " + request.status);
            System.out.println("Scheduled Date: " + request.scheduledDate);
            System.out.println("Cost: " + request.cost);
            System.out.println("Assigned Staff: " + request.assignedStaff);
        } else {
            System.out.println("Maintenance request not found.");
        }
    }

    // Schedule maintenance
    public static void scheduleMaintenance(int requestId, String newDate) {
        Maintenance request = maintenanceDatabase.get(requestId);
        if (request != null) {
            request.scheduledDate = newDate;
            System.out.println("Request ID " + requestId + " scheduled for: " + newDate);
        } else {
            System.out.println("Maintenance request not found.");
        }
    }

    // Track maintenance history
    public static void trackMaintenanceHistory() {
        System.out.println("--- Maintenance History ---");
        for (Maintenance request : maintenanceDatabase.values()) {
            System.out.println("Request ID: " + request.requestId + ", Status: " + request.status + ", Description: " + request.description);
        }
    }

    // Get upcoming maintenance
    public static void getUpcomingMaintenance() {
        System.out.println("--- Upcoming Maintenance ---");
        for (Maintenance request : maintenanceDatabase.values()) {
            if (request.status.equals("Pending")) {
                System.out.println("Request ID: " + request.requestId + ", Scheduled Date: " + request.scheduledDate);
            }
        }
    }

    // Generate maintenance report
    public static void generateMaintenanceReport() {
        System.out.println("--- Maintenance Report ---");
        for (Maintenance request : maintenanceDatabase.values()) {
            System.out.println("Request ID: " + request.requestId + ", Description: " + request.description + ", Status: " + request.status + ", Cost: " + request.cost);
        }
    }

    // Assign a maintenance task to staff
    public static void assignMaintenanceTask(int requestId, String staffName) {
        Maintenance request = maintenanceDatabase.get(requestId);
        if (request != null) {
            request.assignedStaff = staffName;
            System.out.println("Request ID " + requestId + " assigned to: " + staffName);
        } else {
            System.out.println("Maintenance request not found.");
        }
    }

    // Validate maintenance data
    public static boolean validateMaintenanceData(int requestId) {
        return maintenanceDatabase.containsKey(requestId);
    }

    // Get total maintenance costs
    public static void getMaintenanceCosts() {
        double totalCost = 0;
        for (Maintenance request : maintenanceDatabase.values()) {
            totalCost += request.cost;
        }
        System.out.println("Total Maintenance Costs: " + totalCost);
    }

    public static void main(String[] args) {
        // Example usage
        logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);

        getMaintenanceDetails(1);
        updateMaintenanceStatus(1, "In Progress");
        scheduleMaintenance(2, "2024-12-29");

        trackMaintenanceHistory();
        getUpcomingMaintenance();
        generateMaintenanceReport();

        assignMaintenanceTask(1, "John Doe");
        getMaintenanceDetails(1);

        getMaintenanceCosts();
    }
}
