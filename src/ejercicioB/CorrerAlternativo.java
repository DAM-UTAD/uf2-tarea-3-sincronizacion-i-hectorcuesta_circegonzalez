package ejercicioB;

import java.util.concurrent.Semaphore;

/**
 * clase para gestionar los datos compartidos a los que acceden los distintos
 * hilos
 * 
 * @author circe.gonzalez
 *
 */
class Puertecita {
	/**
	 * variables estáticas que contienen los datos que usarán en su ejecución
	 * los hilos del programa Una puerta que tiene dos cerrojos
	 */
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int contador;
}

/**
 * hilo tipo llave A para abrir el cerrojo B de la puerta
 * 
 * @author circe.gonzalez
 *
 */
class Llave_A extends Thread {
	/**
	 * variables
	 */
	Semaphore semaforoA;

	/**
	 * constructor
	 * 
	 * @param s
	 */
	public Llave_A(Semaphore s) {
		semaforoA = s;
	}

	public void run() {

	}
}

/**
 * hilo tipo llave B para abrir el cerrojo B de la puerta
 * 
 * @author circe.gonzalez
 *
 */
class Llave_B extends Thread {
	/**
	 * variables
	 */
	Semaphore semaforoB;

	/**
	 * constructor
	 * 
	 * @param s
	 */
	public Llave_B(Semaphore s) {
		semaforoB = s;
	}

	public void run() {

	}
}

/**
 * Created by H3ku on 30/10/15. hilo principal
 */
public class CorrerAlternativo {
	public static void main(String[] args) throws InterruptedException {

	}
}
