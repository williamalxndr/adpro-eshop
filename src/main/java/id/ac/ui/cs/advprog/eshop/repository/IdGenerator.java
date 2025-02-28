package id.ac.ui.cs.advprog.eshop.repository;

import java.util.UUID;

public class IdGenerator {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
