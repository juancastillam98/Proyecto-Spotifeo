package funciones;

import clases.Cancion;
import hilos.MusicaReproducir;
import interfaces.Reproductor;
/**
 * Clase encargada de hacer reproducir las canciones
 * @author ASUS
 *
 */
public class ReproducirCanciones implements Reproductor{

	public void reproducir(Cancion cancion) {
		MusicaReproducir reproducirCancion = new MusicaReproducir(cancion);
		reproducirCancion.start();
		
	}

}
