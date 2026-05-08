package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

public class PremiumMembership implements MembershipStrategy {
    @Override
    public double applyDiscount(double total) {
        return total * 0.8; // 20% discount
    }

    @Override
    public String getName() {
        return "Premium";
    }
}
