package ejercicio1;

import java.util.concurrent.Semaphore;

/**
 * hilo que escribe letras
 * @author Circe
 *
 */
class Letras extends Thread{
	/*
	 * variables
	 */
	private char c;
	private Semaphore semaforo;
	/**
	 * constructor
	 * @param c
	 * @param semaforo
	 */
	public Letras(char c, Semaphore semaforo){
		this.c = c;
		this.semaforo = semaforo;
	}
	/**
	 * código que ejecutará el hilo cuando sea creado e iniciado
	 */
	public void run(){
		try{
			semaforo.acquire();
			//cerramos el semáforo
			for (int i= 0; i < 10; i++) {
				System.out.println((char)(c + i));
			}
			semaforo.release();
			//abrimos el semaforo
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
/**
 * hilo que escribe números
 * @author Circe
 *
 */
class Numeros extends Thread{
	/*
	 * variables
	 */
	private char c;
	private Semaphore semaforo;
	
	/**
	 * constructor
	 * @param c
	 * @param semaforo
	 */
	public Numeros(char c, Semaphore semaforo){
		this.c = c;
		this.semaforo = semaforo;
	}
	/**
	 * código que ejecutará el hilo cuando sea creado e iniciado
	 */
	public void run(){
			for (int i= 0; i < 10; i++) {
				System.out.println((char)(c + i));
			}
			semaforo.release();
			//abrimos el semáforo
	
	}
}
/**
 * hilo principal
 * @author circe.gonzalez
 *
 */
public class Orden {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(0);
		//creamos el semáforo que controlará a los hilos
		Letras hilo_letras = new Letras('a', semaforo);
		Numeros hilo_numeros = new Numeros('0', semaforo);
		//creamos los dos hilos de ejecución
		hilo_letras.start();
		hilo_numeros.start();
		//iniciamos ambos hilos en el orden que fueron creados

	}

}
