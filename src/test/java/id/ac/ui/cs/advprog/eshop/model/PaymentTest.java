package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
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
    }

    @Test
    void testCreatePaymentBankTransfer() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment = new Payment("8b290f4f-26d0-4eb2-b78b-dd83474fe451", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertEquals("8b290f4f-26d0-4eb2-b78b-dd83474fe451", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("BCA", payment.getPaymentData().get("bankName"));
        assertEquals("620ccd71-d06b-4554-ba35-35e28eb9caed", payment.getPaymentData().get("referenceCode"));
    }

    @Test
    void testCreatePaymentVoucherCode() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        payment = new Payment("8b290f4f-26d0-4eb2-b78b-dd83474fe451", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        assertEquals("8b290f4f-26d0-4eb2-b78b-dd83474fe451", payment.getId());
        assertEquals("ESHOP19238FFF331", payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testStatusVoucherCodeSuccess() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.VOUCHER_CODE.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF331");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNot16Characters() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.VOUCHER_CODE.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP19238FFF3311jkd");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNotStartWithEshop() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.VOUCHER_CODE.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP19238FFF331");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testStatusVoucherCodeNot8Numerical() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.VOUCHER_CODE.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP19238FFF33d");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testBankTransferSuccess() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.BANK_TRANSFER.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testBankNameEmpty() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.BANK_TRANSFER.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testReferenceCodeEmpty() {
        payment = new Payment("958079b6-04ca-49d1-a2a0-f3ecf767bf90", PaymentMethod.BANK_TRANSFER.getValue());

        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");
        payment.setPaymentData(paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
