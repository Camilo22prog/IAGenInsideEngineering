# Solución Problema #1: El Videoclub de Don

## Resumen de la Solución
Se ha implementado un sistema de alquiler de películas siguiendo principios de diseño orientado a objetos y patrones de diseño para asegurar que el sistema sea escalable y fácil de mantener.

### Decisiones de Diseño y Patrones Utilizados

1.  **Patrón Strategy (Estrategia):**
    *   **Por qué:** El cálculo del descuento depende del tipo de membresía. Usar el patrón Strategy permite encapsular la lógica de descuento en clases separadas (`BasicMembership`, `PremiumMembership`) que implementan una interfaz común `MembershipStrategy`. Esto facilita agregar nuevos niveles de membresía en el futuro sin tocar el código del servicio de alquiler.
    *   **Beneficio:** Cumple con el principio de Abierto/Cerrado (OCP).

2.  **Polimorfismo y Abstracción:**
    *   **Por qué:** Se definió una clase abstracta `Movie` con implementaciones concretas `PhysicalMovie` y `DigitalMovie`.
    *   **Beneficio:** Permite tratar a todas las películas de manera uniforme en la lógica de negocio, mientras se permite que cada tipo de película tenga su propio comportamiento o metadatos (como el tipo "Física" o "Digital").

3.  **Encapsulamiento:**
    *   Se utilizaron campos privados y métodos de acceso (getters) para proteger el estado de los objetos y controlar cómo se accede a la información.

### Principios SOLID Aplicados

*   **Single Responsibility Principle (SRP):** Cada clase tiene una única responsabilidad. `RentalService` coordina el proceso, `Movie` representa los datos, y las estrategias de membresía manejan los cálculos.
*   **Open/Closed Principle (OCP):** El sistema es extensible para nuevas películas o membresías sin modificar las clases existentes.
*   **Liskov Substitution Principle (LSP):** Cualquier tipo de película o membresía puede ser sustituida por su base sin afectar el funcionamiento.
*   **Dependency Inversion Principle (DIP):** El `RentalService` depende de abstracciones (`Movie`, `MembershipStrategy`), no de implementaciones concretas.

## Evidencia de Ejecución

### Caso Membresía Premium (1, 3)
```text
--- RECIBO DE ALQUILER ---
Cliente: Premium
Peliculas:
 - Interestellar (Fisica) - $8000.0
 - Inception (Digital) - $5000.0
Subtotal: $13000
Descuento: $2600
Total a pagar: $10400
--------------------------
¡Disfrute su pelicula!
```

### Caso Membresía Básica (1, 4)
```text
--- RECIBO DE ALQUILER ---
Cliente: Basica
Peliculas:
 - Interestellar (Fisica) - $8000.0
 - Matrix (Digital) - $6000.0
Subtotal: $14000
Total a pagar: $14000
--------------------------
¡Disfrute su pelicula!
```

## Estructura de Archivos
- `src/main/java/eci/edu/byteProgramming/ejercicio/Ejercicio_1/`:
    - `Movie.java` (Abstracta)
    - `PhysicalMovie.java`
    - `DigitalMovie.java`
    - `MembershipStrategy.java` (Interfaz)
    - `BasicMembership.java`
    - `PremiumMembership.java`
    - `RentalService.java`
    - `Main.java`
- `src/test/java/eci/edu/byteProgramming/ejercicio/Ejercicio_1/`:
    - `AcceptanceTest.java`
