package ejericicio2;

import java.util.concurrent.Semaphore;

/**
 * clase que gestiona los vehiculos
 * 
 * @author circe.gonzalez
 *
 */
public class Auto extends Thread {
	/*
	 * variables
	 */
	private Puesto[] arrayPuestos;
	private int id_vehiculo;
	private boolean revision = false;
	private Semaphore semaforo;

	/**
	 * constructor
	 * 
	 * @param id_vehiculo
	 * @param arrayPuestos
	 */
	public Auto(int id_vehiculo, Puesto[] arrayPuestos, Semaphore semaforo) {
		this.id_vehiculo = id_vehiculo;
		this.arrayPuestos = arrayPuestos;
		this.semaforo = semaforo;

	}

	/**
	 * código que ejecutará el hilo cuando sea creado
	 */
	public void run() {
		try {
			// restamos una acceso del semáforo
			semaforo.acquire();
			boolean blFin = false;
			// variable booleana para controlar la revisión
			int i = 0;
			while (!blFin && i < arrayPuestos.length) {
				// mientras no se haya completado la revision (false)
				if (arrayPuestos[i].vehiculoEnEspera == null) {
					// si no hay ningún vehiculo ocupando el puesto de
					// inspeccion
					arrayPuestos[i].soyCocheEntrando(this);
					// introduzco el vehiculo en el puesto de inspección
					blFin = true;
					// fin de la revisión
				}

				i++;

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Coche: " + id_vehiculo);

	}

	public int getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}

	public boolean isRevision() {
		return revision;
	}

	public void setRevision(boolean revision) {
		this.revision = revision;
	}

	public Puesto[] getArrayPuestos() {
		return arrayPuestos;
	}

	public void setArrayPuestos(Puesto[] arrayPuestos) {
		this.arrayPuestos = arrayPuestos;
	}

}
