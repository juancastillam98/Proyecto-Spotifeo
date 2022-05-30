package clases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
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
import utils.ConexionBD;

public class Cancion extends ObjetoConSonido{
	
	private Artista artista;
	private int duracion;
	private ArrayList<Estilos> estilosCancion;
	private int cantidadReproduccion;
	
	public Cancion(String nombre, Blob foto, String ruta, Artista artista, 
			int duracion, ArrayList<Estilos> estilosCancion, int cantidadReproduccion) throws SQLException {
		super(nombre, foto, ruta);
		ObjetoConSonido ocs = new ObjetoConSonido();
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into usuario values ('"+nombre+"','"+artista+"','"+estilosCancion+"',"
						+ " '"+artista.getEmail()+"',"+duracion+",'"+foto+"',"+cantidadReproduccion+")"				
				)>0) {
			ocs.setNombre(nombre);
			this.artista = artista;
			
			this.estilosCancion = estilosCancion;
			//artista email
			this.duracion = duracion;
			ocs.setFoto(foto);
			this.cantidadReproduccion=cantidadReproduccion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar la cancion "+nombre);
		}
		

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
