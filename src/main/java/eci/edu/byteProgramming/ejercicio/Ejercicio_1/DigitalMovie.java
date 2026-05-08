package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

public class DigitalMovie extends Movie {
    public DigitalMovie(String title, double price, boolean available) {
        super(title, price, available);
    }

    @Override
    public String getType() {
        return "Digital";
    }
}
