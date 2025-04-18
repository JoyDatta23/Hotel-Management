package hotelmanagement;
import java.util.*;

public class Event {
    private int eventId;
    private String eventName;
    private String eventDate;
    private int customerId;
    private String status; // e.g., "Booked", "Cancelled"
    private double totalCost;
    private List<String> staffAssigned;

    private static Map<Integer, Event> eventDatabase = new HashMap<>(); // Simulates a database
    private static int eventCounter = 1;

    // Constructor
    public Event(int eventId, String eventName, String eventDate, int customerId, String status, double totalCost, List<String> staffAssigned) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.customerId = customerId;
        this.status = status;
        this.totalCost = totalCost;
        this.staffAssigned = staffAssigned;
    }

    // Create a new event booking
    public static void createEventBooking(String eventName, String eventDate, int customerId, double totalCost) {
        Event newEvent = new Event(eventCounter++, eventName, eventDate, customerId, "Booked", totalCost, new ArrayList<>());
        eventDatabase.put(newEvent.eventId, newEvent);
        System.out.println("Event booking created successfully with ID: " + newEvent.eventId);
    }

    // Cancel an event booking
    public static void cancelEventBooking(int eventId) {
        Event event = eventDatabase.get(eventId);
        if (event != null) {
            event.status = "Cancelled";
            System.out.println("Event ID " + eventId + " has been cancelled.");
        } else {
            System.out.println("Event not found.");
        }
    }

    // Update event details
    public static void updateEventDetails(int eventId, String newEventName, String newEventDate, double newTotalCost) {
        Event event = eventDatabase.get(eventId);
        if (event != null) {
            event.eventName = newEventName;
            event.eventDate = newEventDate;
            event.totalCost = newTotalCost;
            System.out.println("Event ID " + eventId + " updated successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    // Get details of an event
    public static void getEventDetails(int eventId) {
        Event event = eventDatabase.get(eventId);
        if (event != null) {
            System.out.println("Event ID: " + event.eventId);
            System.out.println("Name: " + event.eventName);
            System.out.println("Date: " + event.eventDate);
            System.out.println("Customer ID: " + event.customerId);
            System.out.println("Status: " + event.status);
            System.out.println("Total Cost: " + event.totalCost);
            System.out.println("Staff Assigned: " + String.join(", ", event.staffAssigned));
        } else {
            System.out.println("Event not found.");
        }
    }

    // Check availability for a specific date
    public static void checkEventAvailability(String eventDate) {
        boolean available = true;
        for (Event event : eventDatabase.values()) {
            if (event.eventDate.equals(eventDate) && event.status.equals("Booked")) {
                available = false;
                break;
            }
        }
        if (available) {
            System.out.println("The date " + eventDate + " is available for booking.");
        } else {
            System.out.println("The date " + eventDate + " is already booked.");
        }
    }

    // Generate a bill for an event
    public static void generateEventBill(int eventId) {
        Event event = eventDatabase.get(eventId);
        if (event != null) {
            System.out.println("--- Event Bill ---");
            System.out.println("Event ID: " + event.eventId);
            System.out.println("Name: " + event.eventName);
            System.out.println("Total Cost: " + event.totalCost);
        } else {
            System.out.println("Event not found.");
        }
    }

    // Assign staff to an event
    public static void assignEventStaff(int eventId, List<String> staff) {
        Event event = eventDatabase.get(eventId);
        if (event != null) {
            event.staffAssigned.addAll(staff);
            System.out.println("Staff assigned to Event ID " + eventId + ": " + String.join(", ", staff));
        } else {
            System.out.println("Event not found.");
        }
    }

    // Generate a report for all events
    public static void getEventReport() {
        System.out.println("--- Event Report ---");
        for (Event event : eventDatabase.values()) {
            System.out.println("Event ID: " + event.eventId + ", Name: " + event.eventName + ", Date: " + event.eventDate + ", Status: " + event.status);
        }
    }

    // Validate event details
    public static boolean validateEventDetails(int eventId) {
        return eventDatabase.containsKey(eventId);
    }

    // Get event history for a customer
    public static void getEventHistory(int customerId) {
        System.out.println("Event History for Customer ID: " + customerId);
        for (Event event : eventDatabase.values()) {
            if (event.customerId == customerId) {
                System.out.println("Event ID: " + event.eventId + ", Name: " + event.eventName + ", Status: " + event.status);
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        createEventBooking("Wedding", "2024-12-30", 101, 5000);
        createEventBooking("Conference", "2024-12-31", 102, 3000);

        getEventDetails(1);
        updateEventDetails(1, "Wedding Ceremony", "2024-12-30", 5500);
        getEventDetails(1);

        checkEventAvailability("2024-12-30");
        checkEventAvailability("2024-12-29");

        assignEventStaff(1, Arrays.asList("John", "Doe"));
        getEventDetails(1);

        generateEventBill(1);
        cancelEventBooking(2);
        getEventReport();

        getEventHistory(101);
    }
}
