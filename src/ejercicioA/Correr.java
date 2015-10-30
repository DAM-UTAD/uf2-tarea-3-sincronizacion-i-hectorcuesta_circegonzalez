package ejercicioA;

class Puerta {
    public static boolean abierta;
    public static int contador;
}
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
        Puerta.abierta = true;
        Thread a = new Abrir();
        Thread c = new Cerrar();
        a.start();
        c.start();
        a.join();
        c.join();
        System.out.println("El resultado final es: " + Puerta.contador);
    }

}

