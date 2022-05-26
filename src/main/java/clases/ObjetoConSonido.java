package clases;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

public class ObjetoConSonido extends ObjetoConNombre{

	private Clip reproducirCancion;

	public ObjetoConSonido(String nombre, BufferedImage foto, Clip reproducirCancion) {
		super(nombre, foto);
		this.reproducirCancion = reproducirCancion;
	}

	public Clip getReproducirCancion() {
		return reproducirCancion;
	}

	public void setReproducirCancion(Clip reproducirCancion) {
		this.reproducirCancion = reproducirCancion;
	}
	
	


}
