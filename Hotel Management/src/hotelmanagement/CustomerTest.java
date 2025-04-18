package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Before
    public void setUp() {
        // Initialize test environment by adding some customers
        Customer.addCustomer(1, "John Doe", "john@example.com", "1234567890", true);
        Customer.addCustomer(2, "Jane Smith", "jane@example.com", "0987654321", false);
    }

    @After
    public void tearDown() {
        // Clear the simulated database for consistent tests
        Customer.customerDatabase.clear();
    }

    @Test
    public void testAddCustomerSuccess() {
        Customer.addCustomer(3, "Alice Johnson", "alice@example.com", "5556667777", true);
        assertTrue(Customer.customerDatabase.containsKey(3));
    }

    @Test
    public void testAddCustomerAlreadyExists() {
        Customer.addCustomer(1, "John Doe", "john@example.com", "1234567890", true);
        // Test output should indicate "Customer already exists."
    }

    @Test
    public void testDeleteCustomerSuccess() {
        Customer.deleteCustomer(2);
        assertFalse(Customer.customerDatabase.containsKey(2));
    }

    @Test
    public void testDeleteCustomerNotFound() {
        Customer.deleteCustomer(99);
        // Test output should indicate "Customer not found."
    }

    @Test
    public void testUpdateCustomerDetailsSuccess() {
        Customer.updateCustomerDetails(1, "John Updated", "john_updated@example.com", "1112223333");
        Customer customer = Customer.customerDatabase.get(1);
        assertEquals("John Updated", customer.name);
        assertEquals("john_updated@example.com", customer.email);
        assertEquals("1112223333", customer.phone);
    }

    @Test
    public void testUpdateCustomerDetailsNotFound() {
        Customer.updateCustomerDetails(99, "New Name", "new_email@example.com", "0000000000");
        // Test output should indicate "Customer not found."
    }

    @Test
    public void testGetCustomerDetailsSuccess() {
        Customer.getCustomerDetails(1); // Ensure customer details are printed without exceptions
    }

    @Test
    public void testGetCustomerDetailsNotFound() {
        Customer.getCustomerDetails(99); // Ensure output indicates "Customer not found."
    }

    @Test
    public void testSearchCustomerFound() {
        Customer.searchCustomer("Jane Smith");
        // Ensure output indicates "Found Customer ID: 2"
    }

    @Test
    public void testSearchCustomerNotFound() {
        Customer.searchCustomer("Nonexistent Name");
        // Ensure output indicates "Customer not found."
    }

    @Test
    public void testGetBookingHistorySuccess() {
        Customer.getBookingHistory(1);
        // Ensure booking history for customer ID 1 is printed without exceptions
    }

    @Test
    public void testGetBookingHistoryNotFound() {
        Customer.getBookingHistory(99);
        // Test output should indicate "Customer not found."
    }

    @Test
    public void testCheckCustomerMembershipIsMember() {
        assertTrue(Customer.checkCustomerMembership(1));
    }

    @Test
    public void testCheckCustomerMembershipNotMember() {
        assertFalse(Customer.checkCustomerMembership(2));
    }

    @Test
    public void testCheckCustomerMembershipNotFound() {
        assertFalse(Customer.checkCustomerMembership(99));
        // Ensure output indicates "Customer not found."
    }

    @Test
    public void testApplyDiscountForMember() {
        Customer.applyDiscount(1, 100.0);
        // Ensure output indicates "Discounted amount: 90.0"
    }

    @Test
    public void testApplyDiscountForNonMember() {
        Customer.applyDiscount(2, 100.0);
        // Ensure output indicates "No discount applicable. Amount: 100.0"
    }

    @Test
    public void testApplyDiscountCustomerNotFound() {
        Customer.applyDiscount(99, 100.0);
        // Test output should indicate "Customer not found."
    }

    @Test
    public void testValidateCustomerIdentitySuccess() {
        assertTrue(Customer.validateCustomerIdentity("john@example.com", "1234567890"));
    }

    @Test
    public void testValidateCustomerIdentityFail() {
        assertFalse(Customer.validateCustomerIdentity("invalid@example.com", "0000000000"));
        // Ensure output indicates "Customer identity could not be validated."
    }

    @Test
    public void testGetFeedbackFromCustomerSuccess() {
        Customer.getFeedbackFromCustomer(1);
        // Ensure feedback for customer ID 1 is printed without exceptions
    }

    @Test
    public void testGetFeedbackFromCustomerNotFound() {
        Customer.getFeedbackFromCustomer(99);
        // Ensure output indicates "Customer not found."
    }
}
