package hotelmanagement;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BookingTest {

    private Date today;
    private Date tomorrow;
    private Date dayAfterTomorrow;

    @Before
    public void setUp() {
        today = new Date();
        tomorrow = new Date(today.getTime() + 86400000); // +1 day
        dayAfterTomorrow = new Date(today.getTime() + 172800000); // +2 days

        // Create initial bookings
        Booking.createBooking(1, 101, today, tomorrow);
        Booking.createBooking(2, 102, tomorrow, dayAfterTomorrow);
    }

    @After
    public void tearDown() {
        // Reset booking database by canceling all bookings
        Booking.cancelBooking(1);
        Booking.cancelBooking(2);
    }

    @Test
    public void testCreateBooking() {
        Booking.createBooking(3, 103, today, tomorrow);
        assertTrue("Booking should exist", Booking.validateBookingDetails(3));
    }

    @Test
public void testCancelBooking() {
    // Create a booking to ensure it exists
    Date today = new Date();
    Date tomorrow = new Date(today.getTime() + 86400000); // +1 day
    Booking.createBooking(1, 101, today, tomorrow);

    // Cancel the booking
    Booking.cancelBooking(1);
   
}


    @Test
    public void testModifyBooking() {
        Booking.modifyBooking(1, tomorrow, dayAfterTomorrow);
        // Verify by retrieving booking details (manually observe the output)
        Booking.getBookingDetails(1);
    }

    @Test
    public void testGetBookingDetails() {
        Booking.getBookingDetails(1);
        assertTrue("Booking should exist", Booking.validateBookingDetails(1));
    }

    @Test
    public void testCheckBookingStatus() {
        Booking.checkBookingStatus(1);
        // Manually observe the output
    }

    @Test
    public void testGetUpcomingBookings() {
        Booking.getUpcomingBookings();
        // Manually observe the output
    }

    @Test
    public void testGetPastBookings() {
        Booking.getPastBookings();
        // Manually observe the output
    }

    @Test
    public void testGetBookingByCustomer() {
        Booking.getBookingByCustomer(1);
        // Manually observe the output
    }

    @Test
    public void testGetAvailableRoomsForDate() {
        Booking.getAvailableRoomsForDate(today, tomorrow);
        // Manually observe the output
    }

    @Test
    public void testValidateBookingDetails() {
        assertTrue("Booking ID 1 should be valid", Booking.validateBookingDetails(1));
        assertFalse("Invalid Booking ID should not be valid", Booking.validateBookingDetails(999));
    }
}