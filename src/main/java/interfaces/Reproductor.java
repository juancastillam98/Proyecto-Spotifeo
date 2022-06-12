package interfaces;

import clases.Cancion;
import hilos.MusicaReproducir;
/**
 * Interface para añadir a todas las canciones un reproductor
 * @author Juan
 */
public interface Reproductor {
	/**
	 * Función que hace reproducir una cancion
	 * @param cancion
	 */
	public void reproducir(Cancion cancion);
	/**
	 * Función que hace pausar una cancion
	 * @param cancion
	 */
	public void pausar(Cancion cancion);
}
