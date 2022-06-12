package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
/**
 * Hace referencia a todos aquellos objetos que tendr�n un nombre y una foto
 * @author Juan Castilla
 */
public class ObjetoConNombre {
	/**
	 * Hace referencia al nombre que tendr� ese objeto
	 */
	private String nombre;
	/**
	 * Referencia a la foto que tendr� ese objeto
	 */
	private String foto;
	/**
	 * Constructor vac�o
	 */
	public ObjetoConNombre() {}// constructor vac�o,
	/**
	 * Constructor que crea un nuevo objeto que tendr� nombre y foto
	 * @param nombre del objeto
	 * @param foto foto representativa del objeto
	 */
	public ObjetoConNombre(String nombre, String foto) {
		this.setNombre(nombre);
		this.setFoto(foto);
	}
	/**
	 * Getter de nombre
	 * @return nombre del objeto
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Setter de nombre
	 * @param nombre nuevo nombre del objeto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter de fot
	 * @return foto del objeto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * Setter de fot
	 * @param string nueva direcci�pn de la foto
	 */
	public void setFoto(String string) {
		this.foto = string;
	}

	
}
