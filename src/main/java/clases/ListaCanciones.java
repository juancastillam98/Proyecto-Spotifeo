package clases;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class ListaCanciones extends ObjetoConNombre{
	//tengo que hacerle el dao de cancion y listaCanciones
	private ArrayList<Cancion> canciones;
	private Usuario usuario;
	private LocalDateTime fechaIncorporacion;
	
	
	public ListaCanciones(String nombre, BufferedImage foto, ArrayList<Cancion> canciones, Usuario usuario, LocalDateTime fechaIncorporacion) {
		super(nombre, foto);
		this.canciones = new ArrayList<Cancion>();
		this.usuario=usuario;
		this.fechaIncorporacion=fechaIncorporacion;
	}

	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	

}
