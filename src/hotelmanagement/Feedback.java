package hotelmanagement;

//Feedback.java
import java.util.*;

public class Feedback {
 private int feedbackId;
 private int customerId;
 private String feedbackContent;
 String status; // e.g., "Pending", "Reviewed", "Resolved"
 private int rating;

 static Map<Integer, Feedback> feedbackDatabase = new HashMap<>(); // Simulates a database
 static int feedbackCounter = 1;

 // Constructor
 public Feedback(int feedbackId, int customerId, String feedbackContent, String status, int rating) {
     this.feedbackId = feedbackId;
     this.customerId = customerId;
     this.feedbackContent = feedbackContent;
     this.status = status;
     this.rating = rating;
 }

 // Add new feedback
 public static void addFeedback(int customerId, String feedbackContent, int rating) {
     Feedback newFeedback = new Feedback(feedbackCounter++, customerId, feedbackContent, "Pending", rating);
     feedbackDatabase.put(newFeedback.feedbackId, newFeedback);
     System.out.println("Feedback added successfully with ID: " + newFeedback.feedbackId);
 }

 // View all feedback
 public static void viewFeedback() {
     System.out.println("--- Feedback List ---");
     for (Feedback feedback : feedbackDatabase.values()) {
         System.out.println("Feedback ID: " + feedback.feedbackId + " | Customer ID: " + feedback.customerId + " | Status: " + feedback.status);
         System.out.println("Content: " + feedback.feedbackContent);
         System.out.println("Rating: " + feedback.rating);
         System.out.println("---------------------------");
     }
 }

 // Update feedback status
 public static void updateFeedbackStatus(int feedbackId, String newStatus) {
     Feedback feedback = feedbackDatabase.get(feedbackId);
     if (feedback != null) {
         feedback.status = newStatus;
         System.out.println("Feedback ID " + feedbackId + " status updated to " + newStatus);
     } else {
         System.out.println("Feedback not found.");
     }
 }

 // Get feedback by customer
 public static void getFeedbackByCustomer(int customerId) {
     System.out.println("--- Feedback from Customer ID: " + customerId + " ---");
     for (Feedback feedback : feedbackDatabase.values()) {
         if (feedback.customerId == customerId) {
             System.out.println("Feedback ID: " + feedback.feedbackId + " | Status: " + feedback.status);
             System.out.println("Content: " + feedback.feedbackContent);
             System.out.println("Rating: " + feedback.rating);
             System.out.println("---------------------------");
         }
     }
 }

 // Get feedback summary
 public static void getFeedbackSummary() {
     System.out.println("--- Feedback Summary ---");
     int totalFeedback = feedbackDatabase.size();
     int pending = 0, reviewed = 0, resolved = 0;

     for (Feedback feedback : feedbackDatabase.values()) {
         switch (feedback.status) {
             case "Pending":
                 pending++;
                 break;
             case "Reviewed":
                 reviewed++;
                 break;
             case "Resolved":
                 resolved++;
                 break;
         }
     }

     System.out.println("Total Feedback: " + totalFeedback);
     System.out.println("Pending: " + pending);
     System.out.println("Reviewed: " + reviewed);
     System.out.println("Resolved: " + resolved);
 }

 // Analyze feedback trends
 public static void analyzeFeedbackTrends() {
     System.out.println("--- Feedback Trends ---");
     Map<Integer, Integer> ratingCount = new HashMap<>();

     for (Feedback feedback : feedbackDatabase.values()) {
         ratingCount.put(feedback.rating, ratingCount.getOrDefault(feedback.rating, 0) + 1);
     }

     for (Map.Entry<Integer, Integer> entry : ratingCount.entrySet()) {
         System.out.println("Rating: " + entry.getKey() + " | Count: " + entry.getValue());
     }
 }

 // Get feedback ratings
 public static void getFeedbackRatings() {
     System.out.println("--- Feedback Ratings ---");
     for (Feedback feedback : feedbackDatabase.values()) {
         System.out.println("Feedback ID: " + feedback.feedbackId + " | Rating: " + feedback.rating);
     }
 }

 // Generate feedback report
 public static void generateFeedbackReport() {
     System.out.println("--- Feedback Report ---");
     for (Feedback feedback : feedbackDatabase.values()) {
         System.out.println("Feedback ID: " + feedback.feedbackId + " | Customer ID: " + feedback.customerId);
         System.out.println("Content: " + feedback.feedbackContent);
         System.out.println("Rating: " + feedback.rating + " | Status: " + feedback.status);
         System.out.println("---------------------------");
     }
 }

 // Validate feedback content
 public static boolean validateFeedbackContent(int feedbackId) {
     Feedback feedback = feedbackDatabase.get(feedbackId);
     if (feedback != null) {
         boolean isValid = feedback.feedbackContent != null && !feedback.feedbackContent.isEmpty();
         System.out.println("Feedback ID " + feedbackId + " validation: " + isValid);
         return isValid;
     } else {
         System.out.println("Feedback not found.");
         return false;
     }
 }

 // Respond to feedback
 public static void respondToFeedback(int feedbackId, String response) {
     Feedback feedback = feedbackDatabase.get(feedbackId);
     if (feedback != null) {
         System.out.println("Response to Feedback ID " + feedbackId + ": " + response);
         updateFeedbackStatus(feedbackId, "Reviewed");
     } else {
         System.out.println("Feedback not found.");
     }
 }

 public static void main(String[] args) {
     // Example usage
     addFeedback(1, "Great service!", 5);
     addFeedback(2, "Room was not clean.", 2);
     
     viewFeedback();
     
     updateFeedbackStatus(2, "Resolved");
     getFeedbackByCustomer(2);

     getFeedbackSummary();
     analyzeFeedbackTrends();

     generateFeedbackReport();
     validateFeedbackContent(1);

     respondToFeedback(1, "Thank you for your feedback!");
 }
}

