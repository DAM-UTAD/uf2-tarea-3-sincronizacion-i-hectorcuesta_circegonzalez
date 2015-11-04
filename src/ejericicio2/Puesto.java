package ejericicio2;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
/**
 * clase que gestiona los puestos de inspección
 * @author circe.gonzalez
 *
 */
public class Puesto extends Thread {
	/*
	 * variables
	 */
	private int id_puesto, numero_vehiculo;
	private Semaphore semaforo;
	private long millis;
	public boolean bol;
	private int cantidad_coches, cantidad_puestos;
	public Auto vehiculoEnEspera=null;

	/**
	 * constructor
	 * 
	 * @param id_puesto
	 */
	public Puesto(int id_puesto, Semaphore semaforo, int cantidad_coches, int cantidad_puestos) {
		this.id_puesto = id_puesto;
		this.semaforo = semaforo;
		this.cantidad_coches = cantidad_coches;
		this.cantidad_puestos = cantidad_puestos;
		bol=true;
	}

	/**
	 * código que ejecutará el hilo cuando sea creado
	 */
	public void run() {
		
		// mientras queden coches que revisar
		while (cantidad_coches >= cantidad_puestos) {
			if(vehiculoEnEspera!=null){
				//si hay algún coche dentro del puesto
				millis = (long) Math.floor(Math.random() * (10 - 100) + 100);
				// tiempo aleatorio que tarda cada revisión
				try {
					System.out.println("El puesto " + id_puesto + " ha atendido el vehículo número " + vehiculoEnEspera.getId_vehiculo());
					sleep(millis);
					//dormimos el hilo el tiempo que tarda la revisión
					semaforo.release();
					//liberamos el acceso del semáforo que ocupó el vehículo
					vehiculoEnEspera=null;
					//dejamos vacío el puesto de inspección
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void soyCocheEntrando(Auto vehiculoEnEspera){
		this.vehiculoEnEspera=vehiculoEnEspera;
	}

	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public int getId_puesto() {
		return id_puesto;
	}

	public void setId_puesto(int id_puesto) {
		this.id_puesto = id_puesto;
	}

	public int getNumero_vehiculo() {
		return numero_vehiculo;
	}

	public void setNumero_vehiculo(int numero_vehiculo) {
		this.numero_vehiculo = numero_vehiculo;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public int getCantidad_coches() {
		return cantidad_coches;
	}

	public void setCantidad_coches(int cantidad_coches) {
		this.cantidad_coches = cantidad_coches;
	}

}
