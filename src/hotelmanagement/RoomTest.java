package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    // Sets up the environment by adding initial test data before each test.
    @Before
    public void setUp() {
        Room.addRoom(101, "Deluxe", 200.0);
        Room.addRoom(102, "Suite", 300.0);
    }

    // Cleans up the environment by removing test data after each test.
    @After
    public void tearDown() {
        Room.deleteRoom(101);
        Room.deleteRoom(102);
    }

    // Tests the addition of a room and verifies the room type and price are correctly added.
    @Test
    public void testAddRoom() {
        Room.addRoom(103, "Single", 150.0);
        assertEquals("Single", Room.getRoomType(103)); // Check room type
        assertEquals(150.0, Room.getRoomPrice(103), 0.01); // Check room price
        Room.deleteRoom(103); // Cleanup after test
    }

    // Tests the deletion of a room and ensures it no longer exists in the database.
    @Test
    public void testDeleteRoom() {
        Room.addRoom(104, "Standard", 180.0);
        Room.deleteRoom(104);
        assertNull(Room.getRoomType(104)); // Confirm room is deleted
    }

    // Tests updating a room's details and verifies the updated type and price are correct.
    @Test
    public void testUpdateRoomDetails() {
        Room.updateRoomDetails(101, "Premium Deluxe", 250.0);
        assertEquals("Premium Deluxe", Room.getRoomType(101)); // Verify updated type
        assertEquals(250.0, Room.getRoomPrice(101), 0.01); // Verify updated price
    }

    // Tests checking the availability of a room before and after booking.
    @Test
    public void testCheckAvailability() {
        assertTrue(Room.checkAvailability(101)); // Initially available
        Room.bookRoom(101); // Book the room
        assertFalse(Room.checkAvailability(101)); // Should be unavailable
    }

    // Tests booking a room and verifies that its availability status changes to unavailable.
    @Test
    public void testBookRoom() {
        Room.bookRoom(102);
        assertFalse(Room.checkAvailability(102)); // Confirm room is booked
    }

    // Tests canceling a room booking and ensures the room becomes available again.
    @Test
    public void testCancelBooking() {
        Room.bookRoom(101); // Book the room first
        assertFalse(Room.checkAvailability(101)); // Check if unavailable
        Room.cancelBooking(101); // Cancel the booking
        assertTrue(Room.checkAvailability(101)); // Should be available now
    }

    // Tests retrieving room details. Ensures no exception is thrown for valid rooms.
    @Test
    public void testGetRoomDetails() {
        Room.getRoomDetails(101); // Verify details can be retrieved without errors
    }

    // Tests retrieving the type of a room and ensures it matches expectations.
    @Test
    public void testGetRoomType() {
        assertEquals("Deluxe", Room.getRoomType(101)); // Existing room
        assertNull(Room.getRoomType(999)); // Non-existent room
    }

    // Tests retrieving the price of a room and ensures the price is correct or -1 for non-existent rooms.
    @Test
    public void testGetRoomPrice() {
        assertEquals(200.0, Room.getRoomPrice(101), 0.01); // Existing room
        assertEquals(-1, Room.getRoomPrice(999), 0.01); // Non-existent room
    }

    // Tests getting the status of a room and ensures the method runs without throwing exceptions.
    @Test
    public void testGetRoomStatus() {
        Room.bookRoom(101); // Change status
        Room.getRoomStatus(101); // Verify status output
    }
}
