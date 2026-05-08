# SOLUCION.MD - Problema #1: El Videoclub de Don

## Información de la Herramienta
*   **Herramienta de IA:** Gemini CLI (Modelo Gemini).
*   **Tiempo de ejecución:** Aproximadamente 5 minutos (desde la lectura del README hasta la verificación de pruebas).

## Prompt Utilizado
> "En el archivo del README.MD tengo un ejercicio el cual es: Problema #1: El Videoclub de Don, revisa el contexto y resuelve el ejercicio teniendo en cuenta los objetivos especificos y dame un resumende de como lo hiciste y porque tomaste esa deceision, crea una carpeta en el scr que se llame Ejercicio_1 y desarrolla la solucion en esa carpeta, crea test de aceptacion evidenciando la solucion del ejercicio."

## Explicación del Desarrollo

### 1. Análisis del Problema
El requerimiento solicitaba un sistema para un videoclub que manejara películas físicas y digitales, con disponibilidad en tiempo real y descuentos basados en membresías (Básica y Premium). Se enfatizó el uso de patrones de diseño, principios SOLID y polimorfismo.

### 2. Decisiones Arquitectónicas y Patrones
*   **Patrón Strategy (Estrategia):** Implementado para el cálculo de descuentos. La interfaz `MembershipStrategy` define el contrato, y `BasicMembership` junto con `PremiumMembership` proveen las implementaciones concretas. Esto permite cambiar el comportamiento de los precios en tiempo de ejecución sin modificar la clase `RentalService`.
*   **Polimorfismo:** Se utilizó una clase base `Movie` y especializaciones `PhysicalMovie` y `DigitalMovie`. Esto permite que el sistema maneje cualquier tipo de película de forma genérica mientras respeta sus atributos específicos (como el formato).
*   **Encapsulamiento:** Se utilizaron modificadores de acceso `private` para los atributos de estado y `public` para el comportamiento necesario, protegiendo la integridad de los datos de las películas.

### 3. Principios SOLID Aplicados
*   **Single Responsibility (SRP):** `RentalService` solo se encarga de la lógica del proceso de alquiler, no de cómo se calcula un descuento específico.
*   **Open/Closed (OCP):** Es posible añadir una nueva membresía "Familiar" simplemente creando una nueva clase que implemente `MembershipStrategy`, sin alterar el código existente.
*   **Dependency Inversion (DIP):** Las clases de alto nivel (`RentalService`) dependen de abstracciones (`MembershipStrategy`, `Movie`), no de detalles de implementación.

### 4. Proceso de Validación
*   Se desarrolló una clase `Main` para permitir la interacción manual y demostrar el cumplimiento del flujo solicitado.
*   Se crearon **Tests de Aceptación** usando JUnit 5 (`AcceptanceTest.java`) para validar automatizadamente que los cálculos de subtotales, descuentos de membresía Premium y validaciones de disponibilidad funcionen correctamente.
