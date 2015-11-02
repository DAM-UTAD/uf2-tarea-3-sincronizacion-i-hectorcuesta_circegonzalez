package ejercicioA;

//El programa crea dos threads, uno que abrira la puerta y otro que lo cerrara.
//Se intentara abrir o cerrar la puerta respectivamente, en caso de que ya este cerrada o abierta, incrementaremos el
//contador de "colisiones", esto basicamente nos indicara las veces que se ha adelantado un thread respecto a el otro.

//Como se puede ver planteamos el arreglo del programa mediante el uso de dos Semaphore, nos seguira indicando, que
//el resultado final es 1 por el simple motivo de que la puerta empieza como abierta y primero lanzamos el thread abrir.
//Indicar que esta solucion no la considero la mas correcta por que el uso de multiples semaphores implica el riesgo
//de existir DeadLocks, en su lugar yo usaria CyclicBarrier.


import java.util.concurrent.Semaphore;

class PuertaA {
    //Declaramos ambos semaphores.
    public Semaphore wasJustClosed = new Semaphore(1);
    public Semaphore wasJustOpened = new Semaphore(1);

    public boolean abierta;
    public int contador;
}

/**
 * Metodo encargado de Abrir la puerta 1000 veces
 *
 * ---->Esta la puerta cerrado?
 *    |-->Si: Se abre la puerta.
 *    |-->No: Decrementamos el contador de iteracciones e incrementamor el contador de la puerta.
 */
class AbrirA extends Thread {

    private PuertaA puerta;

    public AbrirA(PuertaA puerta) {
        this.puerta = puerta;
    }

    public void run() {
        for (int i = 0; i < 1000; i++){

            try {
                puerta.wasJustClosed.acquire(); //Miramos si el semaforo de puerta cerrada esta en 1.

                if (!puerta.abierta) {
                    puerta.abierta = true;
                } else {
                    i--;
                    puerta.contador++;
                }

                puerta.wasJustOpened.release(); //Aumentamos el semaforo de puerta abierta.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Abrir terminando");
    }
}

/**
 * Metodo encargado de Cerrar la puerta 1000 veces
 *
 * ---->Esta la puerta abierta?
 *    |-->Si: Se cierra la puerta.
 *    |-->No: Decrementamos el contador de iteracciones e incrementamor el contador de la puerta.
 */
class CerrarA extends Thread {

    private PuertaA puerta;

    public CerrarA(PuertaA puerta) {
        this.puerta = puerta;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {

            try {
                puerta.wasJustOpened.acquire(); //Miramos si el semaforo de puerta abierta esta en 1.

                if (puerta.abierta) {
                    puerta.abierta = false;
                }else {
                    i--;
                    puerta.contador++;
                }

                puerta.wasJustClosed.release(); //Aumentamos el semaforo de puerta cerrada.

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Cerrar terminando");
    }
}

public class CorrerAlternativo {
    public static void main(String[] args) throws InterruptedException {
        PuertaA puerta = new PuertaA();
        puerta.wasJustOpened.acquire();
        puerta.abierta = true;

        //Instanciamos ambos thread.
        Thread a = new AbrirA(puerta);
        Thread c = new CerrarA(puerta);

        //Iniciamos ambos threads.
        a.start();
        c.start();

        //Mediante el uso del metodo join pausamos la ejecucion del thread principal hasta que el trhead al cual
        //aplicamos el join termine su ejecucion.
        a.join();
        c.join();

        //Imprimimos el resultado final.
        System.out.println("El resultado final es: " + puerta.contador);
    }

}