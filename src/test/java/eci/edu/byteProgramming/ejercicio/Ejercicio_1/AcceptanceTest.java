package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcceptanceTest {

    @Test
    public void testPremiumRental() {
        RentalService rentalService = new RentalService();
        MembershipStrategy premium = new PremiumMembership();
        
        // Selected: 1 (Interestellar - $8000), 3 (Inception - $5000)
        List<Integer> selectedIndexes = Arrays.asList(0, 2);
        
        double total = rentalService.calculateTotal(selectedIndexes, premium);
        
        // Subtotal: 13000, Discount 20%: 2600, Total: 10400
        assertEquals(10400.0, total, 0.01);
    }

    @Test
    public void testBasicRental() {
        RentalService rentalService = new RentalService();
        MembershipStrategy basic = new BasicMembership();
        
        // Selected: 1 (Interestellar - $8000), 4 (Matrix - $6000)
        List<Integer> selectedIndexes = Arrays.asList(0, 3);
        
        double total = rentalService.calculateTotal(selectedIndexes, basic);
        
        // Subtotal: 14000, Total: 14000
        assertEquals(14000.0, total, 0.01);
    }

    @Test
    public void testUnavailableMovie() {
        RentalService rentalService = new RentalService();
        MembershipStrategy basic = new BasicMembership();
        
        // Selected: 2 (El Padrino - Not available)
        List<Integer> selectedIndexes = Arrays.asList(1);
        
        double total = rentalService.calculateTotal(selectedIndexes, basic);
        
        // Total should be 0 since it's not available
        assertEquals(0.0, total, 0.01);
    }
}
