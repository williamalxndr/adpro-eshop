package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    @Setter
    private HashMap<String, String> paymentData = new HashMap<>();

    Payment(String id, String method) {
    }

    Payment(String id, String method, HashMap<String, String> paymentData) {
    }
}
