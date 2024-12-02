public class Principal {

    public static void main(String[] args) throws InterruptedException {
        // Definir el número de hilos y el número de hilos simultáneos permitidos
        int numeroHilos = 10; // Número de hilos a crear
        int hilosSimultaneosPermitidos = 3; // Número de hilos permitidos simultáneamente

        // Crear una instancia de SemaforoConcurrente con los hilos simultáneos permitidos
        SemaforoConcurrente semaforoConcurrente = new SemaforoConcurrente(hilosSimultaneosPermitidos);

        // Crear y arrancar los hilos
        for (int i = 0; i < numeroHilos; i++) {
            final int hiloNum = i;
            new Thread(() -> {
                try {
                    // Llamamos al método que ejecuta el bloque controlado
                    semaforoConcurrente.ejecutarBloqueControlado();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo " + hiloNum + " interrumpido.");
                }
            }, "Hilo-" + hiloNum).start();
        }
    }
}