# Semáforo Concurrente en Java

Este proyecto es una implementación de un semáforo concurrente en Java, que permite controlar el acceso simultáneo de un número limitado de hilos a un bloque de código.

## Descripción

El código utiliza la clase Semaphore de la biblioteca java.util.concurrent para restringir el acceso concurrente al bloque controlado. Solo un número definido de hilos pueden ejecutar el bloque de código al mismo tiempo, mientras que los demás hilos deben esperar a que se liberen los permisos.

## Características

- Controla el acceso concurrente con un número parametrizable de hilos simultáneos.

- Simula trabajo dentro del bloque controlado con un tiempo de espera de 1 segundo por hilo.

- Imprime en consola los eventos de entrada y salida de cada hilo en el bloque controlado.

## Uso

### Ejecución

**Parámetros:**

- **numeroHilos:** Define el número total de hilos que se crearán.

- **hilosSimultaneosPermitidos:** Define el número máximo de hilos que pueden acceder simultáneamente al bloque controlado.

### Clase Principal:

- La clase principal crea un semáforo con los parámetros especificados.

- Se crean y arrancan los hilos, los cuales intentan acceder al bloque controlado.

### Ejemplo de Ejecución

En el método main, se configuran los siguientes parámetros:

```Java
int numeroHilos = 10; // Número total de hilos
int hilosSimultaneosPermitidos = 3; // Número máximo de hilos simultáneos
SemaforoConcurrente semaforoConcurrente = new SemaforoConcurrente(hilosSimultaneosPermitidos);

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
```

### Salida de Consola

Cuando se ejecuta el programa, muestra en consola el momento en que cada hilo entra y sale del bloque controlado. Ejemplo:

```bash
Hilo-0 ha entrado al bloque controlado.
Hilo-1 ha entrado al bloque controlado.
Hilo-2 ha entrado al bloque controlado.
Hilo-0 ha salido del bloque controlado.
Hilo-3 ha entrado al bloque controlado.
```

Requisitos

- Java Development Kit (JDK) 8 o superior.

- Editor o entorno de desarrollo compatible con Java (por ejemplo, IntelliJ IDEA, Eclipse, NetBeans, etc.).

- Clase SemaforoConcurrente

- import java.util.concurrent.Semaphore;

```Java
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
}
```

## Explicación del Código

**Semaphore:**

Permite controlar el número de permisos disponibles para acceder al bloque controlado.

Método ejecutarBloqueControlado:

Adquiere un permiso antes de acceder al bloque controlado.

Simula trabajo durante 1 segundo con Thread.sleep(1000).

Libera el permiso después de salir del bloque controlado.

**Método main:**

Configura el número de hilos totales y simultáneos.

Crea y ejecuta los hilos para simular acceso concurrente al bloque controlado.

**Personalización**

Cambia el valor de numeroHilos para crear más o menos hilos.

Ajusta hilosSimultaneosPermitidos para modificar el límite de acceso simultáneo.

Modifica el tiempo de trabajo dentro del bloque controlado en el método ejecutarBloqueControlado.
