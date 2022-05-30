package clases;

import java.awt.image.BufferedImage;
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
	
	public ObjetoConSonido(String nombre, Blob foto, String ruta) {
		super(nombre, foto);
		this.ruta=ruta;
		//reproducir
		try {
			FileInputStream cancionAReproducir = new FileInputStream(ruta);
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
	

	public Player getReproducirCancion() {
		return reproducirCancion;
	}

	public void setReproducirCancion(Player reproducirCancion) {
		this.reproducirCancion = reproducirCancion;
	}
	
	


}
