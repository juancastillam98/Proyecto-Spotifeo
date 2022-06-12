package clases;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;

import javax.sound.sampled.Clip;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Representan todos aquellos objetos que reproducirán sonido
 * @author ASUS
 *
 */
public class ObjetoConSonido extends ObjetoConNombre{
	/**
	 * Representa el reproductor de la cancion
	 */
	protected Player reproducirCancion;
	/**
	 * Representa la ruta donde se encuntra la cancion
	 */
	private String ruta;
	
	//constructor vacío
	public ObjetoConSonido() {}
	/**
	 * Constructor de objeto con sonido con los campos de nombre y ruta
	 * @param nombre nombre de la cancion
	 * @param foto foto representativa de la cancion
	 */

	public ObjetoConSonido(Cancion nombre, String foto) {
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
		
	/**
	 * Getter de Objeto con sonido	
	 * @return reproduce la cancion
	 */
	public Player getReproducirCancion() {
		return reproducirCancion;
	}
	/**
	 * Setter de Objeto con sonido
	 * @param reproducirCancion nueva cancion a reproducir
	 */
	public void setReproducirCancion(Player reproducirCancion) {
		this.reproducirCancion = reproducirCancion;
	}
	
	


}
