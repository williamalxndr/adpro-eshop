package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        this.payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", "BANK_TRANSFER");
    }

    @Test
    void testCreatePayment() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        Payment payment1 = new Payment("8b290f4f-26d0-4eb2-b78b-dd83474fe451", "BANK_TRANSFER", paymentData);

        assertEquals("8b290f4f-26d0-4eb2-b78b-dd83474fe451", payment1.getId());
        assertEquals("BANK_TRANSFER", payment1.getMethod());
        assertEquals("ESHOP19238FFF331", payment1.getPaymentData().get("voucherCode"));
        assertEquals("BCA", payment1.getPaymentData().get("bankName"));
        assertEquals("620ccd71-d06b-4554-ba35-35e28eb9caed", payment1.getPaymentData().get("referenceCode"));
    }

    @Test
    void testStatusVoucherCodeSuccess() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNot16Characters() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF3311jkd");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNotStartWithEshop() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP19238FFF331");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNot8Numerical() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP19238FFF33d");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testBankNameEmpty() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testReferenceCodeEmpty() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
