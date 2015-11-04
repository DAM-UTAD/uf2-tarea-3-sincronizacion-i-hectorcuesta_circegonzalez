package ejercicioB;

import java.util.concurrent.Semaphore;

class Puertecita {
	/**
	 * variables est�ticas que contienen los datos que usar�n en su ejecuci�n
	 * los hilos del programa Una puerta que tiene dos cerrojos
	 */
	public static boolean CerrojoA;
	public static boolean CerrojoB;
	public static int  tiempo, contadorA, contadorB;
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
		for (int i = 0; i < 100; i++) {
			try {
				if (!Puertecita.CerrojoB) {
					// mientras el cerrojo B est� cerrado, se podr� abrir el cerrojo
					// A
					semaforoA.acquire();
					// el sem�foro nos permite abrir la puerta
					Puertecita.CerrojoA = true;
					System.out.println("LlaveA abriendo la puerta");
					Puertecita.contadorA++;
					// sumamos uno al contador que se encuentra al otro lado de la
					// puerta
					Puertecita.tiempo ++;
					i++;
					semaforoA.release();
					// salimos y liberamos el sem�foro
					Puertecita.CerrojoA = false;
					// cerramos el cerrojo A
				
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
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
		for (int i = 0; i < 100; i++) {
			try {
				if (!Puertecita.CerrojoA) {
					// mientras el cerrojo A est� cerrado, se podr� abrir el cerrojo
					// B
						//si el hilo se encuentra que ambos cerrojos est�n
						semaforoB.acquire();
						// el sem�foro nos permite abrir la puerta
						Puertecita.CerrojoB = true;
						// abrimos el cerrojo B
						System.out.println("LlaveB abriendo la puerta");
						Puertecita.contadorB++;
						Puertecita.tiempo ++;
						i++;
						// sumamos uno al contador que est� al otro lado de la puerta
						semaforoB.release();
						// liberamos el sem�foro
						Puertecita.CerrojoB = false;
						// cerramos el cerrojo B
					
					
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
		
}


/**
 * Created by H3ku on 30/10/15. hilo principal
 */
public class CorrerAlternativo {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(1);
		// creamos un sem�foro que compartir�n los hilos para gestionar su
		// acceso al otro lado de la puerta
		Puertecita.CerrojoA = false;
		Puertecita.CerrojoB = false;
		//el programa comienza con ambos cerrojos cerrados
		
		//creamos los hilos
		int i = 0;
		while(i <100){
			Llave_A llaveA = new Llave_A(semaforo);
			Llave_B llaveB = new Llave_B(semaforo);
			llaveA.start();
			llaveB.start();
			//iniciamos los hilos
			llaveA.join();
			llaveB.join();
			i++;
		}
		
	/*
	 * comparando ambos contadores podremos ver qu� hilos van m�s r�pido.
	 */
		System.out.println("Contador A: "+ Puertecita.contadorA);
		System.out.println("Contador B: "+ Puertecita.contadorB);
	}
}
