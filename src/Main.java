public class Main {
    public static void main(String[] args) throws InterruptedException {
        GestorReservas g1 = GestorReservas.getInstancia();
        GestorReservas g2 = GestorReservas.getInstancia();

        System.out.println("¿Es la misma instancia?: " + (g1 == g2));
        System.out.println("Simulando 30 solicitudes concurrentes...\n");

        Thread[] hilos = new Thread[30];

        for (int i = 0; i < 30; i++) {
            final int numeroSocio = i + 1;
            hilos[i] = new Thread(new Runnable() {
                public void run() {
                    GestorReservas.getInstancia().reservar("Socio" + numeroSocio);
                }
            });
        }

        // Iniciar hilos concurrentes
        for (int i = 0; i < 30; i++) {
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < 30; i++) {
            hilos[i].join();
        }

        System.out.println("\n--- Estado Final tras Concurrencia ---");
        g1.consultar();

        System.out.println("\nCancelando reserva de 'Socio1' y probando socio inexistente:");
        g1.cancelar("Socio1");
        g1.cancelar("SocioInexistente");

        System.out.println("\n--- Estado Final tras Cancelación ---");
        g1.consultar();
    }
}