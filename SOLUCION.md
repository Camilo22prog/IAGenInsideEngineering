# Solución Problema #1: El Videoclub de Don Mario

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

---

# Solución Problema #2: Tienda Virtual

## 1. Identificación de Patrones
Se identificaron y aplicaron los siguientes patrones de diseño:

- **Factory Method / Abstract Factory**: Utilizado para la creación de diferentes métodos de pago (`CreditCardPayment`, `PaypalPayment`, `CryptoPayment`). El sistema utiliza una interfaz `PaymentFactory` que permite a la lógica de negocio (`ECIPayment`) crear objetos de pago sin conocer sus detalles de implementación o requisitos específicos de construcción.
- **Observer**: Utilizado para desacoplar el núcleo del procesamiento de pagos de las acciones posteriores (actualización de inventario, generación de facturas, notificaciones). `ECIPayment` actúa como el *Subject* que notifica a los *Observers* (como `PaymentEventObserver`) sobre el éxito o fracaso de la transacción.

## 2. Implementación Completa
Para que el sistema funcionara correctamente y siguiera los patrones, se realizaron las siguientes adiciones:

- **Interfaz `PaymentFactory`**: Define el método `createPaymentMethod`.
- **Fábricas Concretas**: `CreditCardPaymentFactory`, `PaypalPaymentFactory`, y `CryptoPaymentFactory`. Estas clases encapsulan los detalles necesarios para inicializar cada tipo de pago (como números de tarjeta, tokens de autenticación o direcciones de billetera).
- **Renombramiento de clases**: Se cambiaron los nombres de las clases originales (`CreditCardFactory`, etc.) a `CreditCardPayment`, ya que representaban el *producto* y no la *fábrica*.

## 3. Errores Identificados y Corregidos
El código inicial presentaba varios problemas que impedían su compilación y funcionamiento:

1.  **Falta de `PaymentFactory`**: `ECIPayment` dependía de esta interfaz, pero no estaba definida.
2.  **Importación Incorrecta**: En `PaymentEventObserver.java`, se importaba `javax.management.Notification` en lugar de la clase local `Notification.java`.
3.  **Bug en Constructor de `PaymentMethod`**: El constructor recibía un parámetro `transactionID` pero lo usaba incorrectamente para intentar asignar el `customerID`, dejando este último como nulo. Además, el `transactionID` era inmediatamente sobrescrito por un valor generado.
4.  **Confusión de Nombres**: Las clases que implementaban los métodos de pago tenían el sufijo `Factory`, lo cual violaba la semántica del patrón de diseño.

## 4. Validación y Pruebas
Se implementó una suite de pruebas unitarias en `PaymentSystemTest.java` que verifica:
- Procesamiento exitoso de pagos con Tarjeta de Crédito, PayPal y Criptomonedas.
- Validación de errores en métodos de pago (ej. número de tarjeta inválido).
- Notificación correcta a los observadores (verificación de descuento en stock de inventario).

### Evidencia de Pruebas
```text
[INFO] Running eci.edu.byteProgramming.ejercicio.paper.util.PaymentSystemTest
...
Processing PayPal payment...
Payment processed successfully!
Payment Observer: Processing successful payment events...
Inventory: Discounted 1 units of Smartphone
Facturation: Invoice generated
Notification: Sending confirmation email
...
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Estructura de Archivos (Problema #2)
- `src/main/java/eci/edu/byteProgramming/ejercicio/paper/util/`:
    - `PaymentMethod.java` (Clase base)
    - `CreditCardPayment.java`, `PaypalPayment.java`, `CryptoPayment.java` (Productos)
    - `PaymentFactory.java` (Interfaz de Fábrica)
    - `CreditCardPaymentFactory.java`, `PaypalPaymentFactory.java`, `CryptoPaymentFactory.java` (Fábricas Concretas)
    - `ECIPayment.java` (Subject / Contexto)
    - `PaymentObserver.java` (Interfaz Observer)
    - `PaymentEventObserver.java` (Concrete Observer)
    - `Inventory.java`, `Facturation.java`, `Notification.java` (Módulos de apoyo)
