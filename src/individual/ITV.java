package individual;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hector Cuesta on 03/11/2015.
 */
public class ITV {


    public static void main(String[] args) {
        //Creamos las variables puestos y coches asignandoles un valor random comprendido entre los valores indicados.
        int workers = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        int cars = ThreadLocalRandom.current().nextInt(20, 50 + 1);

        //Creamos un Array para almacenar llos threads.
        Thread[] threads = new Thread[cars];

        //Creamos un semaforo con el numero de trabajadores.
        Semaphore maxWorkers = new Semaphore(workers);

        System.out.println("Hay "+cars+" vehiculos que seran atendidos en "+workers+" puestos de inspeccion");

        Runnable revision = () -> {
            long tiempoRevision = ThreadLocalRandom.current().nextLong(10, 100 + 1); //Calculamos el tiempo de revision.
            try {
                maxWorkers.acquireUninterruptibly(); //Restamos un trabajador.
                TimeUnit.MILLISECONDS.sleep(tiempoRevision); //Esperamos los milisegundos de la revision.
                maxWorkers.release(); //Sumamos un trabajador.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //Inicializamos los thread.
        for (int i = 0; i < cars ; i++) {
            threads[i] = new Thread(revision);
            threads[i].start();
        }

        //Usamos Join para esperar que terminen.
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Se cierra la ITV");
    }
}
