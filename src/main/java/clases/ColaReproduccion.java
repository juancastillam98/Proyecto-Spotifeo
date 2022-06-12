package clases;

import java.util.ArrayList;
/**
 * Representa una cola de las pr�ximas canciones que pasar�n a reproducirse
 * @author Juan Castilla
 */
public class ColaReproduccion {
	/**
	 * Almacena las canciones que se han reproducido
	 */
	private ArrayList<Cancion> cancionReproducida;
	/**
	 * Almacena las pr�ximas canciones que pasar�n a reproducirse
	 */
	private ArrayList<Cancion> cancionesAReproducir;
	/**
	 * Indica la canci�n actual
	 */
	private Cancion cancionActual;
	/**
	 * Constructor que crea una cola de reproducci�n
	 * @param cancionReproducida Canci�n que se ya ha reproducido
	 * @param cancionesAReproducir Pr�xima canci�n a reproducir
	 * @param cancionActual canci�n actual
	 */
	public ColaReproduccion(ArrayList<Cancion> cancionReproducida, ArrayList<Cancion> cancionesAReproducir,
			Cancion cancionActual) {
		super();
		this.cancionReproducida = cancionReproducida;
		this.cancionesAReproducir = cancionesAReproducir;
		this.cancionActual = cancionActual;
	}
	/**
	 * Getter de cancionReproducida
	 * @return listado de canciones que se han reproducido
	 */
	public ArrayList<Cancion> getCancionReproducida() {
		return cancionReproducida;
	}
	/**
	 * Setter de cancionReproducida
	 * @param cancionReproducida nueva canci�n que se ha reproducido
	 */
	public void setCancionReproducida(ArrayList<Cancion> cancionReproducida) {
		this.cancionReproducida = cancionReproducida;
	}
	/**
	 * Getter de cancionesAReproducir
	 * @return listado de las pr�ximas canciones que pasar�n a reproducirse.
	 */
	public ArrayList<Cancion> getCancionesAReproducir() {
		return cancionesAReproducir;
	}
	/**
	 * Setter de cancionesAReproducir
	 * @return nuevo listado de canci�n que pasar�n a reproducirse
	 */
	public void setCancionesAReproducir(ArrayList<Cancion> cancionesAReproducir) {
		this.cancionesAReproducir = cancionesAReproducir;
	}
	/**
	 * getter de cancionActual
	 * @return canci�n actual en reproducci�n.
	 */
	public Cancion getCancionActual() {
		return cancionActual;
	}
	/**
	 * setter de cancionActual
	 * @param cancionActual nueva canci�n actual en el reproductor
	 */
	public void setCancionActual(Cancion cancionActual) {
		this.cancionActual = cancionActual;
	}
	
	
	
}
