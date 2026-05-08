package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private List<Movie> inventory;

    public RentalService() {
        this.inventory = new ArrayList<>();
        initializeInventory();
    }

    private void initializeInventory() {
        inventory.add(new PhysicalMovie("Interestellar", 8000, true));
        inventory.add(new PhysicalMovie("El Padrino", 7000, false));
        inventory.add(new DigitalMovie("Inception", 5000, true));
        inventory.add(new DigitalMovie("Matrix", 6000, true));
    }

    public List<Movie> getAvailableMovies() {
        return inventory;
    }

    public double calculateTotal(List<Integer> selectedIndexes, MembershipStrategy membership) {
        double subtotal = 0;
        for (int index : selectedIndexes) {
            if (index >= 0 && index < inventory.size()) {
                Movie movie = inventory.get(index);
                if (movie.isAvailable()) {
                    subtotal += movie.getPrice();
                }
            }
        }
        return membership.applyDiscount(subtotal);
    }

    public void processRental(List<Integer> selectedIndexes, MembershipStrategy membership) {
        double subtotal = 0;
        List<Movie> selectedMovies = new ArrayList<>();

        for (int index : selectedIndexes) {
            if (index >= 0 && index < inventory.size()) {
                Movie movie = inventory.get(index);
                if (movie.isAvailable()) {
                    selectedMovies.add(movie);
                    subtotal += movie.getPrice();
                } else {
                    System.out.println("La película '" + movie.getTitle() + "' no está disponible.");
                }
            }
        }

        if (selectedMovies.isEmpty()) {
            System.out.println("No se seleccionaron películas válidas o disponibles.");
            return;
        }

        double total = membership.applyDiscount(subtotal);
        double discountAmount = subtotal - total;

        printReceipt(membership.getName(), selectedMovies, subtotal, discountAmount, total);
    }

    private void printReceipt(String clientType, List<Movie> movies, double subtotal, double discount, double total) {
        System.out.println("\n--- RECIBO DE ALQUILER ---");
        System.out.println("Cliente: " + clientType);
        System.out.println("Peliculas:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("Subtotal: $" + String.format("%.0f", subtotal));
        if (discount > 0) {
            System.out.println("Descuento: $" + String.format("%.0f", discount));
        }
        System.out.println("Total a pagar: $" + String.format("%.0f", total));
        System.out.println("--------------------------");
        System.out.println("¡Disfrute su pelicula!");
    }
}
