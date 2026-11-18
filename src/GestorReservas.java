public class GestorReservas {
    private static GestorReservas instancia;
    private String[] socios;
    private int lugaresDisponibles;

    private GestorReservas() {
        this.socios = new String[10];
        this.lugaresDisponibles = 10;
    }

    public static synchronized GestorReservas getInstancia() {
        if (instancia == null) {
            instancia = new GestorReservas();
        }
        return instancia;
    }

    public synchronized void reservar(String socio) {
        if (socio == null || socio.isEmpty()) {
            return;
        }

        // Verificar duplicados
        for (int i = 0; i < 10; i++) {
            if (socios[i] != null && socios[i].equals(socio)) {
                System.out.println("Rechazado (Duplicado): " + socio + " ya tiene reserva.");
                return;
            }
        }

        // Verificar disponibilidad y registrar de forma atómica
        if (lugaresDisponibles > 0) {
            for (int i = 0; i < 10; i++) {
                if (socios[i] == null) {
                    socios[i] = socio;
                    lugaresDisponibles--;
                    System.out.println("Reserva exitosa: " + socio + " (Restantes: " + lugaresDisponibles + ")");
                    return;
                }
            }
        } else {
            System.out.println("Rechazado (Sin cupo): Sala llena para " + socio);
        }
    }

    public synchronized void cancelar(String socio) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (socios[i] != null && socios[i].equals(socio)) {
                socios[i] = null;
                lugaresDisponibles++;
                encontrado = true;
                System.out.println("Cancelación exitosa: " + socio + " (Disponibles: " + lugaresDisponibles + ")");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Error: No existe reserva para " + socio + ". No se modificó la disponibilidad.");
        }
    }

    public synchronized void consultar() {
        System.out.println("Lugares disponibles: " + lugaresDisponibles);
        System.out.println("Reservas activas:");
        boolean hayReservas = false;
        for (int i = 0; i < 10; i++) {
            if (socios[i] != null) {
                System.out.println("- " + socios[i]);
                hayReservas = true;
            }
        }
        if (!hayReservas) {
            System.out.println("(Ninguna)");
        }
    }
}