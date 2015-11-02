package individual;

import java.util.concurrent.Semaphore;

public class Orden{
    public static void main(String[] args) {

        //Declaramos el semaforo dandole un valor incial de 0.
        Semaphore letterFinish = new Semaphore(0);

        //Nuestro primer thread imprimira letras, cuando termine incrementara el valor del semaforo.
        Runnable letters = () -> {
            for (int i = 'a'; i <= 'j'; i++) {
                System.out.println(Character.toChars(i));
            }

            letterFinish.release();
        };


        //Nuestro segundo thread intentara restar uno al semaforo, como es cero no podra hasta que se terminen de
        //Imprimir las letras y se incremente en uno.
        Runnable numbers = () -> {
            letterFinish.acquireUninterruptibly();

            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
        };

        //Instanciamos los dos Threads.
        Thread t1 = new Thread(letters);
        Thread t2 = new Thread(numbers);

        //Iniciamos los threads.
        t1.start();
        t2.start();

    }
}