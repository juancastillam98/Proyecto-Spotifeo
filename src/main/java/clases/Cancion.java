package clases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;//preguntar si pertenece a esta clase
import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;

import javazoom.jl.player.Player;

public class Cancion extends ObjetoConSonido{
	
	private Artista artista;
	private int duracion;
	private ArrayList<Estilos> estilosCancion;
	private int cantidadReproduccion;
	
	public Cancion(String nombre, BufferedImage foto, Player reproducirCancion, Artista artista, 
			int duracion, ArrayList<Estilos> estilosCancion, int cantidadReproduccion) {
		super(nombre, foto, reproducirCancion);
		this.artista = artista;
		this.duracion = duracion;
		this.estilosCancion = estilosCancion;
		this.cantidadReproduccion=cantidadReproduccion;

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
