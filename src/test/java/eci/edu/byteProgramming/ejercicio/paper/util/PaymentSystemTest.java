package eci.edu.byteProgramming.ejercicio.paper.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentSystemTest {
    private ECIPayment eciPayment;
    private Inventory inventory;
    private Facturation facturation;
    private Notification notification;
    private PaymentEventObserver observer;

    @BeforeEach
    void setUp() {
        eciPayment = new ECIPayment();
        inventory = new Inventory();
        facturation = new Facturation();
        notification = new Notification();
        observer = new PaymentEventObserver(inventory, facturation, notification);
        eciPayment.addObserver(observer);
    }

    @Test
    void testCreditCardPaymentSuccess() {
        PaymentFactory factory = new CreditCardPaymentFactory(
            "4111111111111111", "Juan Perez", "12/25", "123", "Calle 123"
        );
        
        boolean result = eciPayment.processPayment(
            factory, 1200.00, "CUST001", "Gaming Laptop Purchase", 
            "Juan Perez", "juan.perez@example.com", "LAPTOP001"
        );
        
        assertTrue(result);
        assertEquals(4, inventory.getStock("LAPTOP001"));
    }

    @Test
    void testCreditCardPaymentFailure() {
        // Invalid card number
        PaymentFactory factory = new CreditCardPaymentFactory(
            "123", "Juan Perez", "12/25", "123", "Calle 123"
        );
        
        boolean result = eciPayment.processPayment(
            factory, 1200.00, "CUST001", "Gaming Laptop Purchase", 
            "Juan Perez", "juan.perez@example.com", "LAPTOP001"
        );
        
        assertFalse(result);
        assertEquals(5, inventory.getStock("LAPTOP001"));
    }

    @Test
    void testPaypalPaymentSuccess() {
        PaymentFactory factory = new PaypalPaymentFactory(
            "test@example.com", "valid_auth_token_long_enough"
        );
        
        boolean result = eciPayment.processPayment(
            factory, 800.00, "CUST002", "Phone Purchase", 
            "Maria Lopez", "maria.lopez@example.com", "PHONE001"
        );
        
        assertTrue(result);
        assertEquals(9, inventory.getStock("PHONE001"));
    }

    @Test
    void testCryptoPaymentSuccess() {
        PaymentFactory factory = new CryptoPaymentFactory(
            "1BvBMSEYstWetqTFn5Au4m4GFg7xJaNVN2", "BTC", 1500.00
        );
        
        boolean result = eciPayment.processPayment(
            factory, 1200.00, "CUST003", "Laptop Purchase", 
            "Pedro Gomez", "pedro.gomez@example.com", "LAPTOP001"
        );
        
        assertTrue(result);
        // Note: LAPTOP001 stock might be shared across tests if not for @BeforeEach 
        // but here each test gets a new inventory instance.
    }
}
