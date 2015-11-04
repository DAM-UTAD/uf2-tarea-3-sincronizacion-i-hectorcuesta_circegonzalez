package ejericicio2;

import java.util.concurrent.Semaphore;

/**
 * programa para gestionar una itv de vehiculos
 * 
 * @author circe.gonzalez
 *
 */
public class ITV {
	/*
	 * variables
	 */
	private int numero_vehiculos, numero_puestos;
	private Auto[] arrayAuto;
	private Puesto[] arrayPuesto;
	private Semaphore semaforo;

	/**
	 * constructor
	 */
	public ITV() {
		numero_puestos = cantidadPuesto();// creamos los hilos por puesto de
											// vehiculo
		numero_vehiculos = cantidadVehiculos();// creamos los hilos por vehiculo
												// cliente
		System.out.println("Hay " + numero_vehiculos + " de vehículos que serán atendidos en " + numero_puestos
				+ " puestos de inspección.");
		// creo el semáforo
		semaforo = new Semaphore(numero_puestos);
		// creamos un semáforo con tantos accesos como puestos de inspencción
		// existen
		arrayAuto = new Auto[numero_vehiculos];
		// creamos un array de los hilos de vehiculos
		arrayPuesto = new Puesto[numero_puestos];
		// creamos un array de los hilos de puestos
		puestos();
		vehiculos();
	}

	/**
	 * método creamos los hilos para m vehiculos
	 */
	public void vehiculos() {
		for (int i = 0; i < arrayAuto.length; i++) {
			Auto vehiculo = new Auto(i, arrayPuesto, semaforo);
			arrayAuto[i] = vehiculo;
			vehiculo.start();
		}
	}

	/**
	 * método creamos los hilos para n puestos de inspecciçon
	 */
	public void puestos() {
		for (int i = 0; i < arrayPuesto.length; i++) {
			Puesto puesto = new Puesto(i, semaforo, numero_vehiculos, numero_puestos);
			arrayPuesto[i] = puesto;
			puesto.start();
		}
		
	}

	/**
	 * método generar la cantidad de puestos de inspección que tendrá la ITV
	 * 
	 * @return
	 */
	public int cantidadPuesto() {
		return (int) Math.floor(Math.random() * (1 - 5 + 1) + 5);
	}

	/**
	 * método generar la cantidad de vehiculos que serán atendidos
	 * 
	 * @return
	 */
	public int cantidadVehiculos() {
		return (int) Math.floor(Math.random() * (20 - 50 + 1) + 50);
	}

	// ----------------------set y get------------

	public int getNumero_vehiculos() {
		return numero_vehiculos;
	}

	public void setNumero_vehiculos(int numero_vehiculos) {
		this.numero_vehiculos = numero_vehiculos;
	}

	public int getNumero_puestos() {
		return numero_puestos;
	}

	public void setNumero_puestos(int numero_puestos) {
		this.numero_puestos = numero_puestos;
	}

	/**
	 * ejecutable del programa
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new ITV();

	}

}
