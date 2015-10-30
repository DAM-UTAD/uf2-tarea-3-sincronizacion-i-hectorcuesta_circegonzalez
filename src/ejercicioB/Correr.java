package ejercicioB;

/**
 * clase para gestionar los datos compartidos a los que acceden los distintos
 * hilos
 * 
 * @author circe.gonzalez
 *
 */
class Puerta {
	/**
	 * variables estáticas que contienen los datos que usarán en su ejecución
	 * los hilos del programa
	 * Una puerta que tiene dos cerrojos
	 */
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

/**
 * hilo tipo llave A para abrir el cerrojo B de la puerta
 * @author circe.gonzalez
 *
 */
class LlaveA extends Thread {
	public void run() {
		while (!Puerta.CerrojoB)
			;
		Puerta.CerrojoA = true;
		System.out.println("LlaveA terminando");
	}
}

/**
 * hilo tipo llave B para abrir el cerrojo B de la puerta
 * @author circe.gonzalez
 *
 */
class LlaveB extends Thread {
	public void run() {
		while (!Puerta.CerrojoA)
			;
		Puerta.CerrojoB = true;
		System.out.println("LlaveB terminando");
	}
}

/**
 * Created by H3ku on 30/10/15.
 */
public class Correr {
	public static void main(String[] args) throws InterruptedException {
		Puerta.CerrojoA = false;
		Puerta.CerrojoB = false;
		Thread a = new LlaveA();
		Thread b = new LlaveB();
		a.start();
		b.start();
		System.out.println("Comienzo del hilo principal");
		a.join();
		b.join();
		System.out.println("Fin del hilo principal");
	}

}
