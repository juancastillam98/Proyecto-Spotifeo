package clases;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.sound.sampled.Clip;//preguntar si pertenece a esta clase

public class Cancion extends ObjetoConSonido{
	
	private Artista artista;
	private int duracion;
	private ArrayList<Estilos> estilosCancion;
	private int cantidadReproduccion;
	
	public Cancion(String nombre, BufferedImage foto, Clip reproducirCancion, Artista artista, 
			int duracion, ArrayList<Estilos> estilosCancion, int cantidadReproduccion, Clip reproducir) {
		super(nombre, foto, reproducirCancion);
		this.artista = artista;
		this.duracion = duracion;
		this.estilosCancion = estilosCancion;
		this.cantidadReproduccion = cantidadReproduccion;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public ArrayList<Estilos> getEstilosCancion() {
		return estilosCancion;
	}

	public void setEstilosCancion(ArrayList<Estilos> estilosCancion) {
		this.estilosCancion = estilosCancion;
	}

	public int getCantidadReproduccion() {
		return cantidadReproduccion;
	}

	public void setCantidadReproduccion(int cantidadReproduccion) {
		this.cantidadReproduccion = cantidadReproduccion;
	}

	
	
	
}
