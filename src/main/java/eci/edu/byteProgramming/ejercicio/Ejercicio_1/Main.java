package eci.edu.byteProgramming.ejercicio.Ejercicio_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Peliculas Disponibles:");
        List<Movie> movies = rentalService.getAvailableMovies();
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            System.out.println((i + 1) + ". [" + m.getType() + "] " + m.getTitle() + " - $" + m.getPrice() + " - " + (m.isAvailable() ? "Disponible" : "No disponible"));
        }

        System.out.print("\nMembresia del cliente (1: Basica, 2: Premium): ");
        int membershipChoice = scanner.nextInt();
        MembershipStrategy membership = (membershipChoice == 2) ? new PremiumMembership() : new BasicMembership();

        System.out.print("Seleccione peliculas (numeros separados por coma, ej: 1,3): ");
        scanner.nextLine(); // consume newline
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        List<Integer> selectedIndexes = new ArrayList<>();
        for (String part : parts) {
            try {
                selectedIndexes.add(Integer.parseInt(part.trim()) - 1);
            } catch (NumberFormatException e) {
                // ignore invalid input
            }
        }

        rentalService.processRental(selectedIndexes, membership);
    }
}
