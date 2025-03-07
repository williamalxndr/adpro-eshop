package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testCreateAndSave() {
        Payment payment = new Payment("3afcf56e-0872-445d-ba76-2da6f3470105", PaymentMethod.BANK_TRANSFER.getValue());
        paymentRepository.save(payment);

        List<Payment> payments = paymentRepository.findAll();
        assertNotNull(payments);
    }

    @Test
    void testFindById() {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "00ae1bb5-c636-4f59-965e-1cbaf0349371");
        Payment payment = new Payment("3afcf56e-0872-445d-ba76-2da6f3470105", PaymentMethod.BANK_TRANSFER.getValue(), paymentData);
        paymentRepository.save(payment);

        Payment target = paymentRepository.findById("3afcf56e-0872-445d-ba76-2da6f3470105");
        assertNotNull(target);
        assertEquals("3afcf56e-0872-445d-ba76-2da6f3470105", target.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), target.getMethod());
        assertEquals("BCA", target.getPaymentData().get("bankName"));
        assertEquals("00ae1bb5-c636-4f59-965e-1cbaf0349371", target.getPaymentData().get("referenceCode"));
    }

    @Test
    void testFindAllEmpty() {
        List<Payment> payments = paymentRepository.findAll();
        assertTrue(payments.isEmpty());
    }

    @Test
    void testFindAll() {
        HashMap<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("bankName", "BCA");
        paymentData1.put("referenceCode", "620ccd71-d06b-4554-ba35-35e28eb9caed");
        Payment payment1 = new Payment("8b290f4f-26d0-4eb2-b78b-dd83474fe451", PaymentMethod.BANK_TRANSFER.getValue(), paymentData1);
        paymentRepository.save(payment1);

        HashMap<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP19238FFF331");
        Payment payment2 = new Payment("d706d686-2597-4a82-b107-6564785c675b", PaymentMethod.VOUCHER_CODE.getValue(), paymentData2);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }
}
