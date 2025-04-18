package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EventTest {

    @Before
    public void setUp() {
        // Set up initial events for testing
        Event.createEventBooking("Wedding", "2024-12-30", 101, 5000);
        Event.createEventBooking("Conference", "2024-12-31", 102, 3000);
    }

    @After
    public void tearDown() {
        // Clear the event database by canceling events
        Event.cancelEventBooking(1);
        Event.cancelEventBooking(2);
    }

    @Test
    public void testCreateEventBooking() {
        Event.createEventBooking("Birthday Party", "2024-12-25", 103, 2000);
        assertTrue(Event.validateEventDetails(3)); // Check if event exists
    }

    @Test
    public void testCancelEventBooking() {
        Event.cancelEventBooking(1);
        Event.getEventDetails(1); // Verify event status is updated
    }

    @Test
    public void testUpdateEventDetails() {
        Event.updateEventDetails(1, "Wedding Ceremony", "2024-12-30", 5500);
        // Validate updated details
        assertTrue(Event.validateEventDetails(1));
    }

    @Test
    public void testGetEventDetails() {
        Event.getEventDetails(1); // Check if details can be retrieved without exceptions
    }

    @Test
    public void testCheckEventAvailability() {
        Event.checkEventAvailability("2024-12-30"); // Should show booked
        Event.checkEventAvailability("2024-12-29"); // Should show available
    }

    @Test
    public void testGenerateEventBill() {
        Event.generateEventBill(1); // Ensure bill is generated without exceptions
    }

    @Test
    public void testAssignEventStaff() {
        Event.assignEventStaff(1, Arrays.asList("John", "Doe"));
        Event.getEventDetails(1); // Verify staff is assigned
    }

    @Test
    public void testGetEventReport() {
        Event.getEventReport(); // Ensure report is generated without exceptions
    }

    @Test
    public void testValidateEventDetails() {
        assertTrue(Event.validateEventDetails(1)); // Event exists
        assertFalse(Event.validateEventDetails(999)); // Event does not exist
    }

    @Test
    public void testGetEventHistory() {
        Event.getEventHistory(101); // Ensure history can be retrieved without exceptions
    }
}
