package hotelmanagement;

//Booking.java
import java.util.*;

public class Booking {
 private int bookingId;
 private int customerId;
 private int roomId;
 private Date startDate;
 Date endDate;
 String status; // "Confirmed" or "Cancelled"

 static Map<Integer, Booking> bookingDatabase = new HashMap<>(); // Simulates a database
 static int bookingCounter = 1;

 // Constructor
 public Booking(int bookingId, int customerId, int roomId, Date startDate, Date endDate, String status) {
     this.bookingId = bookingId;
     this.customerId = customerId;
     this.roomId = roomId;
     this.startDate = startDate;
     this.endDate = endDate;
     this.status = status;
 }

 // Creates a new booking
 public static void createBooking(int customerId, int roomId, Date startDate, Date endDate) {
     Booking newBooking = new Booking(bookingCounter++, customerId, roomId, startDate, endDate, "Confirmed");
     bookingDatabase.put(newBooking.bookingId, newBooking);
     System.out.println("Booking created successfully with ID: " + newBooking.bookingId);
 }

 // Cancels an existing booking
 public static void cancelBooking(int bookingId) {
     Booking booking = bookingDatabase.get(bookingId);
     if (booking != null) {
         booking.status = "Cancelled";
         System.out.println("Booking ID " + bookingId + " has been cancelled.");
     } else {
         System.out.println("Booking not found.");
     }
 }

 // Modifies booking details
 public static void modifyBooking(int bookingId, Date newStartDate, Date newEndDate) {
     Booking booking = bookingDatabase.get(bookingId);
     if (booking != null) {
         booking.startDate = newStartDate;
         booking.endDate = newEndDate;
         System.out.println("Booking ID " + bookingId + " has been updated.");
     } else {
         System.out.println("Booking not found.");
     }
 }

 // Gets booking details
 public static void getBookingDetails(int bookingId) {
     Booking booking = bookingDatabase.get(bookingId);
     if (booking != null) {
         System.out.println("Booking ID: " + booking.bookingId);
         System.out.println("Customer ID: " + booking.customerId);
         System.out.println("Room ID: " + booking.roomId);
         System.out.println("Start Date: " + booking.startDate);
         System.out.println("End Date: " + booking.endDate);
         System.out.println("Status: " + booking.status);
     } else {
         System.out.println("Booking not found.");
     }
 }

 // Checks the status of a booking
 public static void checkBookingStatus(int bookingId) {
     Booking booking = bookingDatabase.get(bookingId);
     if (booking != null) {
         System.out.println("Booking Status: " + booking.status);
     } else {
         System.out.println("Booking not found.");
     }
 }

 // Gets upcoming bookings
 public static void getUpcomingBookings() {
     Date today = new Date();
     for (Booking booking : bookingDatabase.values()) {
         if (booking.startDate.after(today) && booking.status.equals("Confirmed")) {
             System.out.println("Upcoming Booking ID: " + booking.bookingId);
         }
     }
 }

 // Gets past bookings
 public static void getPastBookings() {
     Date today = new Date();
     for (Booking booking : bookingDatabase.values()) {
         if (booking.endDate.before(today)) {
             System.out.println("Past Booking ID: " + booking.bookingId);
         }
     }
 }

 // Gets bookings by customer
 public static void getBookingByCustomer(int customerId) {
     for (Booking booking : bookingDatabase.values()) {
         if (booking.customerId == customerId) {
             System.out.println("Booking ID: " + booking.bookingId);
         }
     }
 }

 // Gets available rooms for a specific date range
 public static void getAvailableRoomsForDate(Date startDate, Date endDate) {
     List<Integer> unavailableRooms = new ArrayList<>();

     for (Booking booking : bookingDatabase.values()) {
         if ((startDate.before(booking.endDate) && endDate.after(booking.startDate)) && booking.status.equals("Confirmed")) {
             unavailableRooms.add(booking.roomId);
         }
     }

     System.out.println("Unavailable Rooms: " + unavailableRooms);
     // Assume room IDs from 1 to 100 for simplicity
     System.out.println("Available Rooms: ");
     for (int i = 1; i <= 100; i++) {
         if (!unavailableRooms.contains(i)) {
             System.out.print(i + " ");
         }
     }
     System.out.println();
 }

 // Validates booking details
 public static boolean validateBookingDetails(int bookingId) {
     return bookingDatabase.containsKey(bookingId);
 }

 public static void main(String[] args) {
     // Example usage
     Date today = new Date();
     Date tomorrow = new Date(today.getTime() + 86400000); // +1 day
     Date dayAfterTomorrow = new Date(today.getTime() + 172800000); // +2 days

     createBooking(1, 101, today, tomorrow);
     createBooking(2, 102, tomorrow, dayAfterTomorrow);

     getBookingDetails(1);
     modifyBooking(1, today, dayAfterTomorrow);
     getBookingDetails(1);

     checkBookingStatus(1);
     cancelBooking(1);
     checkBookingStatus(1);

     getUpcomingBookings();
     getPastBookings();

     getBookingByCustomer(2);
     getAvailableRoomsForDate(today, tomorrow);
 }
}
