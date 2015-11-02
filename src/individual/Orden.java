package individual;

public class Orden{
    public static void main(String[] args) {

        Runnable letters = () -> {
            for (int i = 'a'; i <= 'j'; i++) {
                System.out.println(Character.toChars(i));
            }
        };

        Runnable numbers = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
        };

        //Instanciamos los dos Threads.
        Thread t1 = new Thread(letters);
        Thread t2 = new Thread(numbers);

        //Definimos sus prioridades.
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        //Iniciamos los threads.
        t1.start();
        t2.start();

    }
}