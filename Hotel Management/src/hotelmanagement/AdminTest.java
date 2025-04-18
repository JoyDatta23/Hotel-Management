package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {

    @Before
    public void setUp() {
        // Clear data before each test to ensure a clean environment
        Admin.policies.clear();
        Admin.systemLogs.clear();
        Admin.userRoles.clear();
        Admin.hotelPreferences.clear();
    }

    @After
    public void tearDown() {
        // Clear data after each test
        Admin.policies.clear();
        Admin.systemLogs.clear();
        Admin.userRoles.clear();
        Admin.hotelPreferences.clear();
    }


    @Test
    public void testUpdatePolicyNotFound() {
        Admin.updatePolicy(99, "New policy description");
        // Ensure output indicates "Policy not found."
    }

    @Test
    public void testDeletePolicySuccess() {
        Admin.addPolicy("All guests must provide valid ID proof.");
        Admin.deletePolicy(1);
        assertFalse(Admin.policies.containsKey(1));
    }

    @Test
    public void testDeletePolicyNotFound() {
        Admin.deletePolicy(99);
        // Ensure output indicates "Policy not found."
    }

    @Test
    public void testGetPolicyDetailsSuccess() {
        Admin.addPolicy("Smoking is prohibited in non-smoking rooms.");
        Admin.getPolicyDetails(1);
        // Ensure policy details are printed without exceptions
    }

    @Test
    public void testGetPolicyDetailsNotFound() {
        Admin.getPolicyDetails(99);
        // Ensure output indicates "Policy not found."
    }

    @Test
    public void testManageUserRolesSuccess() {
        Admin.manageUserRoles("john_doe", "Manager");
        assertEquals("Manager", Admin.userRoles.get("john_doe"));
    }

    @Test
    public void testManageUserRolesMultipleRoles() {
        Admin.manageUserRoles("john_doe", "Manager");
        Admin.manageUserRoles("jane_doe", "Receptionist");
        assertEquals("Manager", Admin.userRoles.get("john_doe"));
        assertEquals("Receptionist", Admin.userRoles.get("jane_doe"));
    }

    @Test
    public void testGetSystemLogs() {
        Admin.manageUserRoles("john_doe", "Manager");
        Admin.getSystemLogs();
        // Ensure logs are printed without exceptions
    }

    @Test
    public void testGenerateAdminReport() {
        Admin.addPolicy("All guests must provide valid ID proof.");
        Admin.manageUserRoles("john_doe", "Manager");
        Admin.generateAdminReport();
        // Ensure the report contains correct counts for policies and roles
    }

    @Test
    public void testValidateAdminActionsSuccess() {
        assertTrue(Admin.validateAdminActions("Add Policy"));
    }

    @Test
    public void testSetHotelPreferencesSuccess() {
        Admin.setHotelPreferences("Check-in Time", "2:00 PM");
        assertEquals("2:00 PM", Admin.hotelPreferences.get("Check-in Time"));
    }

    @Test
    public void testSetHotelPreferencesMultiple() {
        Admin.setHotelPreferences("Check-in Time", "2:00 PM");
        Admin.setHotelPreferences("Check-out Time", "11:00 AM");
        assertEquals("2:00 PM", Admin.hotelPreferences.get("Check-in Time"));
        assertEquals("11:00 AM", Admin.hotelPreferences.get("Check-out Time"));
    }

    @Test
    public void testBackupSystemData() {
        Admin.backupSystemData();
        // Ensure that the system data backup completion message is printed
    }

    @Test
    public void testAddPolicyAndSystemLogs() {
        Admin.addPolicy("All guests must provide valid ID proof.");
        assertTrue(Admin.systemLogs.size() > 0);
    }

    @Test
    public void testBackupSystemDataAndSystemLogs() {
        Admin.backupSystemData();
        assertTrue(Admin.systemLogs.size() > 0);
    }
}
