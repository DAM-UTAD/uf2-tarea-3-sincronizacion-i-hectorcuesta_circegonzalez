package individual;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hector Cuesta on 03/11/2015.
 */

class GlobalSettings{
    //Creamos las variables puestos y coches asignandoles un valor random comprendido entre los valores indicados.
    static int workers = ThreadLocalRandom.current().nextInt(1, 5 + 1);
    static int cars = ThreadLocalRandom.current().nextInt(20, 50 + 1);

    //Creamos un semaforo con el numero de trabajadores.
    static Semaphore usingCarsArray = new Semaphore(1);

    //Creamos un Array para almacenar los threads.
    static LinkedList<Thread> carThreads = new LinkedList<>();

    //Creamos un Array para almacenar los threads.
    static Thread[] workerThreads = new Thread[workers];
}

class CarThread implements Runnable {
    @Override
    public void run() {
        long tiempoRevision = ThreadLocalRandom.current().nextLong(10, 100 + 1); //Calculamos el tiempo de revision.
        try {
            TimeUnit.MILLISECONDS.sleep(tiempoRevision); //Esperamos los milisegundos de la revision.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WorkerThread implements Runnable {

    int workerName;
    String carName;

    public WorkerThread(int pWorkerName){
        workerName = pWorkerName;
    }
    @Override
    public void run() {

        //Mientras existan coches repetimos.
        while (GlobalSettings.carThreads.size() > 0){
            Thread revision;

            //Indicamos que estamos usando el array de coches para evitar RaceConditions.
            GlobalSettings.usingCarsArray.acquireUninterruptibly();
            revision = GlobalSettings.carThreads.pop();
            carName = revision.getName();
            revision.start();
            GlobalSettings.usingCarsArray.release(); //Liberamos el uso de el Array.
            try {
                revision.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("El puesto numero " + workerName + " ha atendido el vehiculo "+ carName);
        }
    }
}

public class ITV {
    public static void main(String[] args) {

        System.out.println("Hay "+GlobalSettings.cars+" vehiculos que seran atendidos en "+GlobalSettings.workers+" puestos de inspeccion");

        //Inicializamos los thread coche.
        for (int i = 0; i < GlobalSettings.cars ; i++) {
            Thread car =  new Thread(new CarThread());
            car.setName(String.valueOf(i+1));
            GlobalSettings.carThreads.add(car);
        }

        //Inicializamos los thread de los trabajadores.
        for (int i = 0; i < GlobalSettings.workers ; i++) {
            GlobalSettings.workerThreads[i] = new Thread(new WorkerThread(i+1));
            GlobalSettings.workerThreads[i].start();
        }

        for (Thread worker : GlobalSettings.workerThreads){
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.print("Se cierra la ITV");
    }
}
