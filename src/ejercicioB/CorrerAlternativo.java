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
		try{
			while(Puertecita.contador <= 10){
				//while (!Puertecita.CerrojoB);
				Puertecita.CerrojoA = true;
				semaforoA.acquire();
				System.out.println("LlaveA terminando");
				Puertecita.contador++;
				semaforoA.release();
				Puertecita.CerrojoA = false;
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
			
	
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
		try{
			while(Puertecita.contador <= 10){
				//while (!Puertecita.CerrojoA);
				Puertecita.CerrojoB = true;
				semaforoB.acquire();//deja disponible la puerta
				System.out.println("LlaveB terminando");
				Puertecita.contador++;
				semaforoB.release();
				Puertecita.CerrojoB = false;
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}

/**
 * Created by H3ku on 30/10/15. hilo principal
 */
public class CorrerAlternativo {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(1);
		//creamos un semáforo binario
		Puertecita.CerrojoA = false;
		Puertecita.CerrojoB = false;
		//establecemos los cerrojos en cerrado
		Puertecita.contador =0;
		//creamos un contador general
		Llave_A llaveA = new Llave_A(semaforo);
		Llave_B llaveB = new Llave_B(semaforo);
		//creamos los hilos de ambas llaves
		System.out.println("comienzo del hilo principal");
		llaveA.start();
		llaveB.start();
		//iniciamos los hilos de ambas llaves
		System.out.println("fin del hilo principal");
		llaveA.join();
		llaveB.join();
		System.out.println(Puertecita.contador);
	}
}
