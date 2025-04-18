package hotelmanagement;

import static org.junit.Assert.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FeedbackTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream teeStream = new PrintStream(new PrintStream(outContent) {
        
    	@Override
        public void write(byte[] b, int off, int len) {
            originalOut.write(b, off, len); // Print to the original console
            super.write(b, off, len);       // Capture in outContent
        }
    });

    @Before
    public void setUpStreams() {
        System.setOut(teeStream);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void setUp() {
        // Clear static data before each test to avoid interference
        Feedback.feedbackDatabase.clear();
        Feedback.feedbackCounter = 1;
    }

    // Add your test methods here


    // Test addFeedback
    @Test
    public void testAddFeedback() {
        System.out.println("Running testAddFeedback...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.addFeedback(2, "Room was not clean.", 2);

        assertEquals(2, Feedback.feedbackDatabase.size());
        assertTrue(Feedback.feedbackDatabase.containsKey(1));
        assertTrue(Feedback.feedbackDatabase.containsKey(2));

        System.out.println(outContent.toString());
    }

    // Test viewFeedback
    @Test
    public void testViewFeedback() {
        System.out.println("Running testViewFeedback...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.viewFeedback();

        String output = outContent.toString();
        assertTrue(output.contains("Great service!"));
        System.out.println(output);
    }

    // Test updateFeedbackStatus
    @Test
    public void testUpdateFeedbackStatus() {
        System.out.println("Running testUpdateFeedbackStatus...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.updateFeedbackStatus(1, "Reviewed");

        assertEquals("Reviewed", Feedback.feedbackDatabase.get(1).status);
        System.out.println(outContent.toString());
    }

    @Test
    public void testUpdateFeedbackStatusInvalidId() {
        System.out.println("Running testUpdateFeedbackStatusInvalidId...");
        Feedback.updateFeedbackStatus(999, "Reviewed");

        String output = outContent.toString();
        assertTrue(output.contains("Feedback not found."));
        System.out.println(output);
    }

    // Test getFeedbackByCustomer
    @Test
    public void testGetFeedbackByCustomer() {
        System.out.println("Running testGetFeedbackByCustomer...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.addFeedback(1, "Average experience.", 3);
        Feedback.getFeedbackByCustomer(1);

        String output = outContent.toString();
        assertTrue(output.contains("Great service!"));
        assertTrue(output.contains("Average experience."));
        System.out.println(output);
    }

    // Test getFeedbackSummary
    @Test
    public void testGetFeedbackSummary() {
        System.out.println("Running testGetFeedbackSummary...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.addFeedback(2, "Room was not clean.", 2);
        Feedback.updateFeedbackStatus(2, "Reviewed");
        Feedback.getFeedbackSummary();

        String output = outContent.toString();
        assertTrue(output.contains("Total Feedback: 2"));
        assertTrue(output.contains("Pending: 1"));
        assertTrue(output.contains("Reviewed: 1"));
        System.out.println(output);
    }

    // Test analyzeFeedbackTrends
    @Test
    public void testAnalyzeFeedbackTrends() {
        System.out.println("Running testAnalyzeFeedbackTrends...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.addFeedback(2, "Room was not clean.", 2);
        Feedback.analyzeFeedbackTrends();

        String output = outContent.toString();
        assertTrue(output.contains("Rating: 5 | Count: 1"));
        assertTrue(output.contains("Rating: 2 | Count: 1"));
        System.out.println(output);
    }

    // Test validateFeedbackContent
    @Test
    public void testValidateFeedbackContent() {
        System.out.println("Running testValidateFeedbackContent...");
        Feedback.addFeedback(1, "Great service!", 5);
        assertTrue(Feedback.validateFeedbackContent(1));
        System.out.println(outContent.toString());
    }

    @Test
    public void testValidateFeedbackContentInvalidId() {
        System.out.println("Running testValidateFeedbackContentInvalidId...");
        assertFalse(Feedback.validateFeedbackContent(999));

        String output = outContent.toString();
        assertTrue(output.contains("Feedback not found."));
        System.out.println(output);
    }

    // Test respondToFeedback
    @Test
    public void testRespondToFeedback() {
        System.out.println("Running testRespondToFeedback...");
        Feedback.addFeedback(1, "Great service!", 5);
        Feedback.respondToFeedback(1, "Thank you for your feedback!");

        assertEquals("Reviewed", Feedback.feedbackDatabase.get(1).status);
        String output = outContent.toString();
        assertTrue(output.contains("Response to Feedback ID 1: Thank you for your feedback!"));
        System.out.println(output);
    }

    @Test
    public void testRespondToFeedbackInvalidId() {
        System.out.println("Running testRespondToFeedbackInvalidId...");
        Feedback.respondToFeedback(999, "Thank you for your feedback!");

        String output = outContent.toString();
        assertTrue(output.contains("Feedback not found."));
        System.out.println(output);
    }
}
