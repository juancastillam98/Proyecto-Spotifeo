package clases;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.Clip;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ObjetoConSonido extends ObjetoConNombre{

	private Player reproducirCancion;

	public ObjetoConSonido(String nombre, BufferedImage foto, Player reproducirCancion) {
		super(nombre, foto);
		
		//reproducir
		try {
			FileInputStream cancionAReproducir = new FileInputStream("./musica");
			this.reproducirCancion = new Player(cancionAReproducir);
			this.reproducirCancion.play();
			
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
