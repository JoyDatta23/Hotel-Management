package hotelmanagement;


import static org.junit.Assert.*;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MaintenanceTest {

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
        Maintenance.maintenanceDatabase.clear();
        Maintenance.requestCounter = 1;
    }

    // Test logMaintenanceRequest
    @Test
    public void testLogMaintenanceRequest() {
        System.out.println("Running testLogMaintenanceRequest...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);

        assertEquals(2, Maintenance.maintenanceDatabase.size());
        assertTrue(Maintenance.maintenanceDatabase.containsKey(1));
        assertTrue(Maintenance.maintenanceDatabase.containsKey(2));

        System.out.println(outContent.toString());
    }

    // Test updateMaintenanceStatus
    @Test
    public void testUpdateMaintenanceStatus() {
        System.out.println("Running testUpdateMaintenanceStatus...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.updateMaintenanceStatus(1, "In Progress");

        assertEquals("In Progress", Maintenance.maintenanceDatabase.get(1).status);
        System.out.println(outContent.toString());
    }

    @Test
    public void testUpdateMaintenanceStatusInvalidId() {
        System.out.println("Running testUpdateMaintenanceStatusInvalidId...");
        Maintenance.updateMaintenanceStatus(999, "In Progress");

        String output = outContent.toString();
        assertTrue(output.contains("Maintenance request not found."));
        System.out.println(output);
    }

    // Test getMaintenanceDetails
    @Test
    public void testGetMaintenanceDetails() {
        System.out.println("Running testGetMaintenanceDetails...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.getMaintenanceDetails(1);

        String output = outContent.toString();
        assertTrue(output.contains("Fix AC"));
        System.out.println(output);
    }

    @Test
    public void testGetMaintenanceDetailsInvalidId() {
        System.out.println("Running testGetMaintenanceDetailsInvalidId...");
        Maintenance.getMaintenanceDetails(999);

        String output = outContent.toString();
        assertTrue(output.contains("Maintenance request not found."));
        System.out.println(output);
    }

    // Test scheduleMaintenance
    @Test
    public void testScheduleMaintenance() {
        System.out.println("Running testScheduleMaintenance...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.scheduleMaintenance(1, "2024-12-29");

        assertEquals("2024-12-29", Maintenance.maintenanceDatabase.get(1).scheduledDate);
        System.out.println(outContent.toString());
    }

    @Test
    public void testScheduleMaintenanceInvalidId() {
        System.out.println("Running testScheduleMaintenanceInvalidId...");
        Maintenance.scheduleMaintenance(999, "2024-12-29");

        String output = outContent.toString();
        assertTrue(output.contains("Maintenance request not found."));
        System.out.println(output);
    }

    // Test trackMaintenanceHistory
    @Test
    public void testTrackMaintenanceHistory() {
        System.out.println("Running testTrackMaintenanceHistory...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);
        Maintenance.trackMaintenanceHistory();

        String output = outContent.toString();
        assertTrue(output.contains("Fix AC"));
        assertTrue(output.contains("Repair plumbing"));
        System.out.println(output);
    }

    // Test getUpcomingMaintenance
    @Test
    public void testGetUpcomingMaintenance() {
        System.out.println("Running testGetUpcomingMaintenance...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);
        Maintenance.updateMaintenanceStatus(1, "In Progress");
        Maintenance.getUpcomingMaintenance();

        String output = outContent.toString();
        assertTrue(output.contains("2024-12-31"));
        assertFalse(output.contains("2024-12-30"));
        System.out.println(output);
    }

    // Test generateMaintenanceReport
    @Test
    public void testGenerateMaintenanceReport() {
        System.out.println("Running testGenerateMaintenanceReport...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);
        Maintenance.generateMaintenanceReport();

        String output = outContent.toString();
        assertTrue(output.contains("Fix AC"));
        assertTrue(output.contains("Repair plumbing"));
        System.out.println(output);
    }

    // Test assignMaintenanceTask
    @Test
    public void testAssignMaintenanceTask() {
        System.out.println("Running testAssignMaintenanceTask...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.assignMaintenanceTask(1, "John Doe");

        assertEquals("John Doe", Maintenance.maintenanceDatabase.get(1).assignedStaff);
        System.out.println(outContent.toString());
    }

    @Test
    public void testAssignMaintenanceTaskInvalidId() {
        System.out.println("Running testAssignMaintenanceTaskInvalidId...");
        Maintenance.assignMaintenanceTask(999, "John Doe");

        String output = outContent.toString();
        assertTrue(output.contains("Maintenance request not found."));
        System.out.println(output);
    }

    // Test validateMaintenanceData
    @Test
    public void testValidateMaintenanceData() {
        System.out.println("Running testValidateMaintenanceData...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        assertTrue(Maintenance.validateMaintenanceData(1));
        assertFalse(Maintenance.validateMaintenanceData(999));

        System.out.println(outContent.toString());
    }

    // Test getMaintenanceCosts
    @Test
    public void testGetMaintenanceCosts() {
        System.out.println("Running testGetMaintenanceCosts...");
        Maintenance.logMaintenanceRequest("Fix AC", "2024-12-30", 200.00);
        Maintenance.logMaintenanceRequest("Repair plumbing", "2024-12-31", 150.00);
        Maintenance.getMaintenanceCosts();

        String output = outContent.toString();
        assertTrue(output.contains("Total Maintenance Costs: 350.0"));
        System.out.println(output);
    }
}

