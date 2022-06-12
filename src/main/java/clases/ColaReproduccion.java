package clases;

import java.util.ArrayList;
/**
 * Representa una cola de las próximas canciones que pasarán a reproducirse
 * @author Juan Castilla
 */
public class ColaReproduccion {
	/**
	 * Almacena las canciones que se han reproducido
	 */
	private ArrayList<Cancion> cancionReproducida;
	/**
	 * Almacena las próximas canciones que pasarán a reproducirse
	 */
	private ArrayList<Cancion> cancionesAReproducir;
	/**
	 * Indica la canción actual
	 */
	private Cancion cancionActual;
	/**
	 * Constructor que crea una cola de reproducción
	 * @param cancionReproducida Canción que se ya ha reproducido
	 * @param cancionesAReproducir Próxima canción a reproducir
	 * @param cancionActual canción actual
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
	 * @param cancionReproducida nueva canción que se ha reproducido
	 */
	public void setCancionReproducida(ArrayList<Cancion> cancionReproducida) {
		this.cancionReproducida = cancionReproducida;
	}
	/**
	 * Getter de cancionesAReproducir
	 * @return listado de las próximas canciones que pasarán a reproducirse.
	 */
	public ArrayList<Cancion> getCancionesAReproducir() {
		return cancionesAReproducir;
	}
	/**
	 * Setter de cancionesAReproducir
	 * @return nuevo listado de canción que pasarán a reproducirse
	 */
	public void setCancionesAReproducir(ArrayList<Cancion> cancionesAReproducir) {
		this.cancionesAReproducir = cancionesAReproducir;
	}
	/**
	 * getter de cancionActual
	 * @return canción actual en reproducción.
	 */
	public Cancion getCancionActual() {
		return cancionActual;
	}
	/**
	 * setter de cancionActual
	 * @param cancionActual nueva canción actual en el reproductor
	 */
	public void setCancionActual(Cancion cancionActual) {
		this.cancionActual = cancionActual;
	}
	
	
	
}
