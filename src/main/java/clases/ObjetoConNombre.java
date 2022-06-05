package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;

public class ObjetoConNombre {
	private String nombre;
	//private BufferedImage foto;	
	private String foto;
	
	public ObjetoConNombre() {}// constructor vacío,
	
	public ObjetoConNombre(String nombre, String foto) {
		this.setNombre(nombre);
		this.setFoto(foto);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String string) {
		this.foto = string;
	}

	
}
