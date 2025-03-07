package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private HashMap<String, String> paymentData = new HashMap<>();

    Payment(String id, String method) {
        this.id = id;
        this.method = method;
    }

    Payment(String id, String method, HashMap<String, String> paymentData) {
        this.id = id;
        this.method = method;
        setPaymentData(paymentData);
    }

    void setPaymentData(HashMap<String, String> paymentData) {
        this.paymentData = paymentData;
        checkStatus();
    }

    void checkStatus() {
        if (isValidVoucher(paymentData.get("voucherCode"))
                && !paymentData.get("bankName").isEmpty()
                && !paymentData.get("referenceCode").isEmpty()) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private boolean isValidVoucher(String voucherCode) {
        return voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.replaceAll("\\D", "").length() == 8;
    }
}
