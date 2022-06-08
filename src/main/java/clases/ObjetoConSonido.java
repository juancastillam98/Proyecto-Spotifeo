package clases;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;

import javax.sound.sampled.Clip;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ObjetoConSonido extends ObjetoConNombre{

	private Player reproducirCancion;
	private String ruta;
	
	//constructor vacío
	public ObjetoConSonido() {}
	/**
	 * Constructor que accede a las canciones
	 * @param nombre
	 * @param foto
	 * @param ruta
	 */
	//antes era String nombre
	
	/*
	public ObjetoConSonido(Cancion nombre, String foto, String ruta) {
		super();
		this.ruta=ruta;
		//reproducir
		try {
			ruta="./musica";
			FileInputStream cancionAReproducir = new FileInputStream(ruta+"/"+nombre.getNombre());
			this.reproducirCancion = new Player(cancionAReproducir);
			this.reproducirCancion.play();//si le pongo el play aqui se va a reproducir de manera automática
			
		} catch (FileNotFoundException e) {//no se encuentra la ruta
			e.printStackTrace();
			System.err.println("no se encuentra la canción en la ruta");
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		};
*/
	public ObjetoConSonido(Cancion nombre, String foto, String ruta) {
		super();
		ruta="./musica";
		
		try {
			Player player;
			FileInputStream cancionAReproducir = new FileInputStream(ruta+"/"+nombre.getNombre());
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
		
		};
	public Player getReproducirCancion() {
		return reproducirCancion;
	}

	public void setReproducirCancion(Player reproducirCancion) {
		this.reproducirCancion = reproducirCancion;
	}
	
	


}
