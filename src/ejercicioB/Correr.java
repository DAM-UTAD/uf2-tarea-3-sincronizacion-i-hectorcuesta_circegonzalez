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
	/**
	 * método que ejecuta el hilo
	 */
	public void run() {
		while (!Puerta.CerrojoB);
		//si el cerrojo es distinto de B (es decir, es A)
		Puerta.CerrojoA = true;
		//abrir el cerrojo A
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
			//si el cerrojo es distinto de A (es decir, es B)
			;
		Puerta.CerrojoB = true;
		//abrir cerrojo B
		System.out.println("LlaveB terminando");
	}
}

/**
 * Created by H3ku on 30/10/15.
 * hilo principal de ejecución 
 */
public class Correr {
	public static void main(String[] args) throws InterruptedException {
		Puerta.CerrojoA = false;
		//cerrar el cerrojo A
		Puerta.CerrojoB = false;
		//cerrar el cerrojo B
		Thread a = new LlaveA();
		//crear el hilo de la llave A
		Thread b = new LlaveB();
		//crear el hilo de la llave B
		a.start();
		//iniciar el hilo de la llave A
		b.start();
		//iniciar el hilo de la llave B
		System.out.println("Comienzo del hilo principal");
		a.join();
		//el hilo llave A debe esperar a que termine el hilo llave B
		b.join();
		//el hiilo llave B debe esperar a que termine el hilo llave A
		System.out.println("Fin del hilo principal");
	}
/*
 * el programa no funciona porque se produce una situación de interbloqueo
 * ya que ambos hilos estan esperándose mutuamente a que el otro termine su ejecución
 */
}

