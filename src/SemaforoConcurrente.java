import java.util.concurrent.Semaphore;

public class SemaforoConcurrente {
    private final Semaphore semaforo;
    private final int numeroHilosPermitidos;

    public SemaforoConcurrente(int numeroHilosPermitidos) {
        this.numeroHilosPermitidos = numeroHilosPermitidos;
        this.semaforo = new Semaphore(numeroHilosPermitidos);
    }

    public void ejecutarBloqueControlado() throws InterruptedException {
        semaforo.acquire();  // Adquiere un permiso
        try {
            System.out.println(Thread.currentThread().getName() + " ha entrado al bloque controlado.");
            Thread.sleep(1000);  // Simulamos trabajo con un tiempo de espera de 1 segundo
        } finally {
            semaforo.release();  // Libera el permiso
            System.out.println(Thread.currentThread().getName() + " ha salido del bloque controlado.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numeroHilos = 10; // Número de hilos a crear
        int hilosSimultaneosPermitidos = 3; // Número de hilos permitidos simultáneamente
        SemaforoConcurrente semaforoConcurrente = new SemaforoConcurrente(hilosSimultaneosPermitidos);

        // Creamos y arrancamos los hilos
        for (int i = 0; i < numeroHilos; i++) {
            final int hiloNum = i;
            new Thread(() -> {
                try {
                    semaforoConcurrente.ejecutarBloqueControlado();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo " + hiloNum + " interrumpido.");
                }
            }, "Hilo-" + hiloNum).start();
        }
    }
}