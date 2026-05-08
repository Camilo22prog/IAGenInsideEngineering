package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

public class BasicMembership implements MembershipStrategy {
    @Override
    public double applyDiscount(double total) {
        return total;
    }

    @Override
    public String getName() {
        return "Basica";
    }
}
