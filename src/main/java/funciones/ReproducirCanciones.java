package funciones;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import clases.Cancion;
import hilos.MusicaReproducir;
import interfaces.Reproductor;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * Clase encargada de hacer reproducir las canciones
 * @author ASUS
 *
 */
public class ReproducirCanciones implements Reproductor{
	/**
	 * método que hace reproducir una cancion
	 * @param reproducir 
	 * @param Objeto cancion a reproducir
	 */
	public void reproducir(Cancion cancion) {
		MusicaReproducir reproducirCancion = new MusicaReproducir(cancion);
		reproducirCancion.start();
		
	}

	/**
	 * método que hace pausar una cancion
	 * @param Objeto cancion a pausar
	 */
	public void pausar(Cancion cancion) {
		MusicaReproducir reproducirCancion = new MusicaReproducir(cancion);
		if (reproducirCancion.isAlive()) {
			System.out.println("cancion reproduciendo");
		}else {
			System.out.println("Cancin no reproducicendo");
		}
		try {
			reproducirCancion.sleep(100000000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String ruta="./musica";
		System.out.println("HOla desde la funcion");
		try {
			Player player;
			FileInputStream cancionAReproducir = new FileInputStream(ruta+"/"+cancion.getNombre());
			BufferedInputStream bis = new BufferedInputStream(cancionAReproducir);
			player=new Player(bis);
			player.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
