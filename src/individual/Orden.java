package individual;

public class Orden extends Thread {
    private char c;

    //Asignamos el char
    public Orden(char c) {
        this.c = c;
    }
    public void run() {
        //Recorremos 10 iteracciones en un bucle.
        for (int i = 0; i < 10; i++) {
            System.out.println(Character.toChars(c + i)); //Incrementamos e imprimirmos el valor.
        }
    }
    public static void main(String[] args) {
        //Instanciamos los dos Threads.
        Thread t1 = new Orden('A');
        Thread t2 = new Orden('0');

        //Definimos sus prioridades.
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        //Iniciamos los threads.
        t1.start();
        t2.start();
    }
}