package hilos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import clases.Cancion;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**
 * Hilo que hace reproducir una canción
 * @author Juan Castilla
 */
public class MusicaReproducir extends Thread{
	/**
	 * Canción a reproducir
	 */
	private Cancion cancion;
/**
 * Constructor que recibe un objeto de tipo canción
 * @param nombre nombre del objeto de tipo Cancion
 */
	public MusicaReproducir(Cancion nombre) {
		super();
		this.cancion=nombre;
	}
	/**
	 * Método que hace ejecutar el hilo
	 */
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String ruta="./musica";
		try {
			Player player;
			FileInputStream cancionAReproducir = new FileInputStream(ruta+"/"+cancion.getNombre());
			BufferedInputStream bis = new BufferedInputStream(cancionAReproducir);
			player=new Player(bis);
			player.play();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	

}
