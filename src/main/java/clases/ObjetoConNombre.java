package clases;

import java.awt.image.BufferedImage;

public class ObjetoConNombre {
	private String nombre;
	//private BufferedImage foto;	
	private byte[] foto;
	
	public ObjetoConNombre() {}// constructor vacío,
	
	public ObjetoConNombre(String nombre, byte[] fotoArray) {
		this.nombre=nombre;
		this.foto = new byte[1];
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
}
