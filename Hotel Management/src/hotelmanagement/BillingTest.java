package hotelmanagement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillingTest {

    @Before
    public void setUp() {
        // Initialize the test environment by creating some bills
        Billing.createBill(1, 1000, 50);
        Billing.createBill(2, 500, 20);
    }

    @After
    public void tearDown() {
        // Clear the simulated database (manual cleanup for consistent tests)
        Billing.billingDatabase.clear();
    }



    @Test
    public void testAddPaymentDetailsBillNotFound() {
        Billing.addPaymentDetails(99, "Cash");
        // Test output should indicate "Bill not found."
    }

    @Test
    public void testAddPaymentDetailsAlreadyPaid() {
        Billing.addPaymentDetails(1, "Credit Card");
        Billing.addPaymentDetails(1, "Cash"); // Trying to pay again
        Billing bill = Billing.billingDatabase.get(1);
        assertEquals("Credit Card", bill.paymentMethod); // Should remain unchanged
    }

    @Test
    public void testGenerateInvoiceSuccess() {
        Billing.generateInvoice(1); // Ensure invoice is printed without exceptions
    }

    @Test
    public void testGenerateInvoiceBillNotFound() {
        Billing.generateInvoice(99); // Ensure output indicates "Bill not found."
    }

    @Test
    public void testApplyDiscountsBillNotFound() {
        Billing.applyDiscounts(99, 20); // Test output should indicate "Bill not found."
    }

    @Test
    public void testProcessRefundSuccess() {
        Billing.addPaymentDetails(1, "Credit Card"); // Make the bill "Paid"
        Billing.processRefund(1, 100);
        // Output should indicate refund processed successfully
    }

    @Test
    public void testProcessRefundBillNotPaid() {
        Billing.processRefund(2, 50); // Bill is still "Pending"
        // Output should indicate bill not paid yet
    }

    @Test
    public void testValidatePaymentMethodValid() {
        assertTrue(Billing.validatePaymentMethod("Credit Card"));
        assertTrue(Billing.validatePaymentMethod("Cash"));
    }

    @Test
    public void testValidatePaymentMethodInvalid() {
        assertFalse(Billing.validatePaymentMethod("Bitcoin"));
    }

    @Test
    public void testTrackPendingPayments() {
        Billing.trackPendingPayments();
        // Ensure that pending payments (e.g., bill ID 2) are listed
    }

    @Test
    public void testGeneratePaymentReport() {
        Billing.generatePaymentReport();
        // Ensure all payment details are listed correctly
    }

    @Test
    public void testGetPaymentHistory() {
        Billing.getPaymentHistory(1);
        // Ensure payment history for customer ID 1 is printed correctly
    }

    @Test
    public void testGetBillDetailsSuccess() {
        Billing.getBillDetails(1); // Ensure bill details are printed without exceptions
    }

    @Test
    public void testGetBillDetailsBillNotFound() {
        Billing.getBillDetails(99); // Ensure output indicates "Bill not found."
    }
}
