package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;

public class ObjetoConNombre {
	private String nombre;
	//private BufferedImage foto;	
	private Blob foto;
	
	public ObjetoConNombre() {}// constructor vacío,
	
	public ObjetoConNombre(String nombre, Blob fotoArray) {
		this.nombre=nombre;
		this.foto = foto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Blob getFoto() {
		return foto;
	}
	public void setFoto(Blob blob) {
		this.foto = blob;
	}

	
}
