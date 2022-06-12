package interfaces;

import clases.Cancion;
import hilos.MusicaReproducir;
/**
 * Interface para a�adir a todas las canciones un reproductor
 * @author Juan
 */
public interface Reproductor {
	/**
	 * Funci�n que hace reproducir una cancion
	 * @param cancion
	 */
	public void reproducir(Cancion cancion);
}
