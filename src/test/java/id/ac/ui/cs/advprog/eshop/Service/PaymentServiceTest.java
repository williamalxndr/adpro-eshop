package id.ac.ui.cs.advprog.eshop.Service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    Order order;

    @BeforeEach
    void setUp() {
        this.payments = new ArrayList<>();

        Product product = new Product();
        product.setProductId("d339c63a-48b0-4426-9bad-ffaff47f4593");
        product.setProductName("Sabun cap paima");
        product.setProductQuantity(20);

        List<Product> products = new ArrayList<>();
        products.add(product);

        this.order = new Order("838fd714-d420-4db6-9ff7-a2038ec287fb", products, 1708560000L, "Safira Sudrajat");

        Payment paymentVoucher = new Payment("34fbfc51-3255-492a-a2b5-0e78bb739e31", PaymentMethod.VOUCHER_CODE.getValue());
        Payment paymentBank = new Payment("44915284-2f29-4c31-8ce2-2a441f775857", PaymentMethod.BANK_TRANSFER.getValue());

        payments.add(paymentVoucher);
        payments.add(paymentBank);
    }

    @Test
    void testAddPaymentVoucher() {
        Payment paymentVoucher = payments.get(0);
        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, paymentVoucher.getMethod(), paymentVoucher.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(paymentVoucher.getMethod(), result.getMethod());
        assertEquals(paymentVoucher.getStatus(), result.getStatus());
    }

    @Test
    void testAddPaymentBank() {
        Payment paymentBank = payments.get(1);
        doReturn(paymentBank).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, paymentBank.getMethod(), paymentBank.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(paymentBank.getMethod(), result.getMethod());
        assertEquals(paymentBank.getStatus(), result.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        Payment paymentVoucher = payments.get(0);

        doReturn(paymentVoucher).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).findById(paymentVoucher.getId());
        doReturn(order).when(orderRepository).save(order);
        Payment result = paymentService.setStatus(paymentVoucher, PaymentStatus.SUCCESS.getValue());

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(paymentRepository, times(1)).save(paymentVoucher);
    }

    @Test
    void testSetStatusRejected() {
        Payment paymentBank = payments.get(1);
        doReturn(paymentBank).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).findById(paymentBank.getId());
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.setStatus(paymentBank, PaymentStatus.REJECTED.getValue());
        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(1)).save(paymentBank);
    }

    @Test
    void testGetPaymentById() {
        Payment paymentVoucher = payments.get(0);
        doReturn(paymentVoucher).when(paymentRepository).findById(paymentVoucher.getId());

        Payment result = paymentService.getPayment(paymentVoucher.getId());
        assertEquals(paymentVoucher.getId(), result.getId());
        assertEquals(paymentVoucher.getMethod(), result.getMethod());
        assertEquals(paymentVoucher.getStatus(), result.getStatus());
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(2, results.size());
    }

}
