package ejercicioA;


//El programa crea dos threads, uno que abrira la puerta y otro que lo cerrara.
//Se intentara abrir o cerrar la puerta respectivamente, en caso de que ya este cerrada o abierta, incrementaremos el
//contador de "colisiones", esto basicamente nos indicara las veces que se ha adelantado un thread respecto a el otro.


class Puerta {
    public static boolean abierta;
    public static int contador;
}

/**
 * Metodo encargado de Abrir la puerta 1000 veces
 *
 * ---->Esta la puerta cerrado?
 *    |-->Si: Se abre la puerta.
 *    |-->No: Decrementamos el contador de iteracciones e incrementamor el contador de la puerta.
 */
class Abrir extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++)
            if (!Puerta.abierta)
                Puerta.abierta = true;
            else {
                i--;
                Puerta.contador++;
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
class Cerrar extends Thread {
    public void run() {
        for (int i = 0; i < 1000; i++)
            if (Puerta.abierta)
                Puerta.abierta = false;
            else {
                i--;
                Puerta.contador++;
            }
        System.out.println("Cerrar terminando");
    }
}

public class Correr {
    public static void main(String[] args) throws InterruptedException {
        Puerta.abierta = true; //Hace la variable global abierta = true

        //Instanciamos ambos thread.
        Thread a = new Abrir();
        Thread c = new Cerrar();

        //Iniciamos ambos threads.
        a.start();
        c.start();

        //Mediante el uso del metodo join pausamos la ejecucion del thread principal hasta que el trhead al cual
        //aplicamos el join termine su ejecucion.
        a.join();
        c.join();

        //Imprimimos el resultado final.
        System.out.println("El resultado final es: " + Puerta.contador);
    }

}

