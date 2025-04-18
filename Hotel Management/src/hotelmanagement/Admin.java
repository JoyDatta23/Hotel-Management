package hotelmanagement;

import java.util.*;

public class Admin {
    static Map<Integer, String> policies = new HashMap<>(); // Stores policies with their IDs
    static int policyCounter = 1;
    static List<String> systemLogs = new ArrayList<>(); // Simulates system logs
    static Map<String, String> userRoles = new HashMap<>(); // Stores user roles (username -> role)
    static Map<String, String> hotelPreferences = new HashMap<>(); // Stores hotel preferences

    // Add a new policy
    public static void addPolicy(String policyDescription) {
        policies.put(policyCounter, policyDescription);
        SystemLogs("Policy added with ID: " + policyCounter);
        System.out.println("Policy added successfully with ID: " + policyCounter++);
    }

    // Update an existing policy
    public static void updatePolicy(int policyId, String newDescription) {
        if (policies.containsKey(policyId)) {
            policies.put(policyId, newDescription);
            SystemLogs("Policy updated with ID: " + policyId);
            System.out.println("Policy updated successfully.");
        } else {
            System.out.println("Policy not found.");
        }
    }

    // Delete a policy
    public static void deletePolicy(int policyId) {
        if (policies.remove(policyId) != null) {
            SystemLogs("Policy deleted with ID: " + policyId);
            System.out.println("Policy deleted successfully.");
        } else {
            System.out.println("Policy not found.");
        }
    }

    // Get policy details
    public static void getPolicyDetails(int policyId) {
        if (policies.containsKey(policyId)) {
            System.out.println("Policy ID: " + policyId);
            System.out.println("Description: " + policies.get(policyId));
        } else {
            System.out.println("Policy not found.");
        }
    }

    // Manage user roles
    public static void manageUserRoles(String username, String role) {
        userRoles.put(username, role);
        SystemLogs("Role updated for user: " + username);
        System.out.println("User role updated successfully.");
    }

    // Get system logs
    public static void getSystemLogs() {
        System.out.println("--- System Logs ---");
        for (String log : systemLogs) {
            System.out.println(log);
        }
    }

    // Generate an admin report
    public static void generateAdminReport() {
        System.out.println("--- Admin Report ---");
        System.out.println("Total Policies: " + policies.size());
        System.out.println("Total Users with Roles: " + userRoles.size());
        System.out.println("System Logs Count: " + systemLogs.size());
    }

    // Validate admin actions
    public static boolean validateAdminActions(String action) {
        // This is a placeholder for complex validation logic
        SystemLogs("Action validated: " + action);
        return true;
    }

    // Set hotel preferences
    public static void setHotelPreferences(String key, String value) {
        hotelPreferences.put(key, value);
        SystemLogs("Hotel preference updated: " + key + " = " + value);
        System.out.println("Preference set successfully.");
    }

    // Backup system data
    public static void backupSystemData() {
        SystemLogs("System data backed up.");
        System.out.println("System data backup completed successfully.");
    }

    // Add entry to system logs
    private static void SystemLogs(String message) {
        systemLogs.add(new Date() + " - " + message);
    }

    public static void main(String[] args) {
        // Example usage
        addPolicy("All guests must provide valid ID proof.");
        addPolicy("Smoking is prohibited in non-smoking rooms.");
        
        getPolicyDetails(1);
        updatePolicy(1, "All guests must provide valid government ID proof.");
        getPolicyDetails(1);

        deletePolicy(2);
        getPolicyDetails(2);

        manageUserRoles("john_doe", "Manager");
        manageUserRoles("jane_doe", "Receptionist");

        setHotelPreferences("Check-in Time", "2:00 PM");
        setHotelPreferences("Check-out Time", "11:00 AM");

        validateAdminActions("Add Policy");

        getSystemLogs();
        generateAdminReport();
        backupSystemData();
    }
}
