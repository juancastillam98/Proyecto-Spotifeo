package clases;

import java.awt.image.BufferedImage;

public class ObjetoConNombre {
	private String nombre;
	private BufferedImage foto;	

	public ObjetoConNombre(String nombre, BufferedImage foto) {
		this.nombre=nombre;
		this.foto = foto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BufferedImage getFoto() {
		return foto;
	}
	public void setFoto(BufferedImage foto) {
		this.foto = foto;
	}

	
}
