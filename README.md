Ejercicio 15 - Gestor Concurrente de Reservas de una Sala de Lectura

Explicación del programa

En este ejercicio se creó una clase llamada GestorReservas que aplica el patrón Singleton para administrar de forma centralizada y segura ante concurrencia los diez lugares de la sala de lectura, sin utilizar librerías externas ni imports.
La clase cuenta con:
- Una variable estática privada llamada instancia.
- Un arreglo privado de socios y un contador de lugares disponibles.
- Un constructor privado y un método estático sincronizado (getInstancia()) para garantizar una creación segura en entornos multihilo.
- Métodos públicos sincronizados para reservar, cancelar y consultar el estado de la sala garantizando atomicidad.

Luego, dentro del método main, se simulan 30 solicitudes concurrentes provenientes de diferentes hilos (socios):
GestorReservas g1 = GestorReservas.getInstancia();
GestorReservas g2 = GestorReservas.getInstancia();

Finalmente, se utilizó System.out.println() para comprobar que ambas referencias son iguales, verificar que las reservas nunca superen el límite de diez ni la disponibilidad sea negativa, y probar la cancelación segura de reservas.

Lógica utilizada

La lógica del programa consiste en:
- Proteger la instanciación y los métodos de modificación mediante la palabra clave synchronized para evitar condiciones de carrera.
- Validar que no existan reservas duplicadas por el mismo socio y controlar estrictamente los cupos disponibles.
- Rechazar solicitudes cuando la sala está llena y evitar alterar la disponibilidad al intentar cancelar reservas inexistentes.

Resultado de la ejecución

La ejecución del programa muestra en la consola lo siguiente:
¿Es la misma instancia?: true
Simulando 30 solicitudes concurrentes...

Reserva exitosa: Socio1 (Restantes: 9)
Reserva exitosa: Socio2 (Restantes: 8)
...
Reserva exitosa: Socio10 (Restantes: 0)
Rechazado (Sin cupo): Sala llena para Socio11
...
Rechazado (Sin cupo): Sala llena para Socio30

--- Estado Final tras Concurrencia ---
Lugares disponibles: 0
Reservas activas:
- Socio1
- Socio2
...
- Socio10

Cancelando reserva de 'Socio1' y probando socio inexistente:
Cancelación exitosa: Socio1 (Disponibles: 1)
Error: No existe reserva para SocioInexistente. No se modificó la disponibilidad.

--- Estado Final tras Cancelación ---
Lugares disponibles: 1
Reservas activas:
- Socio2
...
- Socio10

Captura de pantalla

<img width="604" height="728" alt="image" src="https://github.com/user-attachments/assets/fbd8246c-8e58-4a4c-8d47-702eb6023248" />
<img width="539" height="619" alt="image" src="https://github.com/user-attachments/assets/5d79988f-f214-4273-a208-0a791e2d835b" />
<img width="716" height="531" alt="image" src="https://github.com/user-attachments/assets/10acb542-feb7-4ec9-a041-4cd52f14e4cc" />


