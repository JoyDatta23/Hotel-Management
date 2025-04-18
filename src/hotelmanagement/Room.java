package hotelmanagement;

import java.util.HashMap;
import java.util.Map;

public class Room {
 private int roomId;
 private String roomType;
 private double roomPrice;
 private boolean isAvailable;
 private static Map<Integer, Room> roomDatabase = new HashMap<>(); // Simulates a database

 // Constructor
 public Room(int roomId, String roomType, double roomPrice, boolean isAvailable) {
     this.roomId = roomId;
     this.roomType = roomType;
     this.roomPrice = roomPrice;
     this.isAvailable = isAvailable;
 }

 // Adds a new room
 public static void addRoom(int roomId, String roomType, double roomPrice) {
     if (roomDatabase.containsKey(roomId)) {
         System.out.println("Room already exists.");
     } else {
         Room newRoom = new Room(roomId, roomType, roomPrice, true);
         roomDatabase.put(roomId, newRoom);
         System.out.println("Room added successfully.");
     }
 }

 // Deletes an existing room
 public static void deleteRoom(int roomId) {
     if (roomDatabase.remove(roomId) != null) {
         System.out.println("Room deleted successfully.");
     } else {
         System.out.println("Room not found.");
     }
 }

 // Updates room details
 public static void updateRoomDetails(int roomId, String newRoomType, double newRoomPrice) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         room.roomType = newRoomType;
         room.roomPrice = newRoomPrice;
         System.out.println("Room details updated successfully.");
     } else {
         System.out.println("Room not found.");
     }
 }

 // Checks room availability
 public static boolean checkAvailability(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         return room.isAvailable;
     } else {
         System.out.println("Room not found.");
         return false;
     }
 }

 // Gets room details
 public static void getRoomDetails(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         System.out.println("Room ID: " + room.roomId);
         System.out.println("Room Type: " + room.roomType);
         System.out.println("Room Price: " + room.roomPrice);
         System.out.println("Availability: " + (room.isAvailable ? "Available" : "Occupied"));
     } else {
         System.out.println("Room not found.");
     }
 }

 // Books a room
 public static void bookRoom(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         if (room.isAvailable) {
             room.isAvailable = false;
             System.out.println("Room booked successfully.");
         } else {
             System.out.println("Room is already occupied.");
         }
     } else {
         System.out.println("Room not found.");
     }
 }

 // Cancels a room booking
 public static void cancelBooking(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         if (!room.isAvailable) {
             room.isAvailable = true;
             System.out.println("Booking cancelled successfully.");
         } else {
             System.out.println("Room is already available.");
         }
     } else {
         System.out.println("Room not found.");
     }
 }

 // Gets room status
 public static void getRoomStatus(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         System.out.println("Room " + roomId + " is " + (room.isAvailable ? "Available" : "Occupied"));
     } else {
         System.out.println("Room not found.");
     }
 }

 // Gets room type
 public static String getRoomType(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         return room.roomType;
     } else {
         System.out.println("Room not found.");
         return null;
     }
 }

 // Gets room price
 public static double getRoomPrice(int roomId) {
     Room room = roomDatabase.get(roomId);
     if (room != null) {
         return room.roomPrice;
     } else {
         System.out.println("Room not found.");
         return -1;
     }
 }

 public static void main(String[] args) {
     // Example usage
     addRoom(101, "Deluxe", 200.0);
     addRoom(102, "Suite", 300.0);
     getRoomDetails(101);
     bookRoom(101);
     getRoomStatus(101);
     cancelBooking(101);
     getRoomStatus(101);
     updateRoomDetails(102, "Executive Suite", 350.0);
     getRoomDetails(102);
     deleteRoom(101);
 }
}
