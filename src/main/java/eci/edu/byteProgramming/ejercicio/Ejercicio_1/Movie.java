package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

public abstract class Movie {
    private String title;
    private double price;
    private boolean available;

    public Movie(String title, double price, boolean available) {
        this.title = title;
        this.price = price;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return " - " + title + " (" + getType() + ") - $" + price;
    }
}
