package individual;

import java.util.concurrent.Semaphore;

public class Orden{
    public static void main(String[] args) {

        //Declaramos el semaforo dandole un valor incial de 0.
        Semaphore numberFinish = new Semaphore(0);

        //Nuestro primer thread intentara restar uno al semaforo, como es cero no podra hasta que se terminen de
        //Imprimir los numeros y se incremente en uno.
        Runnable letters = () -> {
            numberFinish.acquireUninterruptibly();
            for (int i = 'a'; i <= 'j'; i++) {
                System.out.println(Character.toChars(i));
            }
        };

        //Nuestro segundo thread imprimira numeros, cuando termine incrementara el valor del semaforo.
        Runnable numbers = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
            numberFinish.release();
        };

        //Instanciamos los dos Threads.
        Thread t1 = new Thread(letters);
        Thread t2 = new Thread(numbers);

        //Iniciamos los threads.
        t1.start();
        t2.start();

    }
}