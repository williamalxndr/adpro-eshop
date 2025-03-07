package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE"),
    BANK_TRANSFER("BANK_TRANSFER"),
    CASH_ON_DELIVERY("CASH_ON_DELIVERY");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }
}
