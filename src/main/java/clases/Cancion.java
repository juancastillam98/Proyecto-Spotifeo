package clases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
	
	protected Artista artista;
	protected int duracion;
	protected Estilos estiloCancion;
	protected LocalDateTime fechaIncorporacion;
	protected int cantidadReproduccion;
	public Cancion() {
		// TODO Auto-generated constructor stub
	}
	
	public Cancion(String foto, String nombre, Artista artista, Estilos estiloCancion, 
			LocalDateTime fechaIncorporacion, int duracion, int cantidadReproduccion) throws SQLException {
		super();
				
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into cancion values ('"+foto+"','"+nombre+"','"+artista.getEmail()+"','"+estiloCancion+"',"
						+ "'"+fechaIncorporacion+"',"+duracion+","+cantidadReproduccion+")"				
				)>0) {
			this.setFoto(foto);
			this.setNombre(nombre);
			this.artista = artista;//quiero insertar el nombre del artista
			this.estiloCancion = estiloCancion;
			this.fechaIncorporacion=fechaIncorporacion;
			this.duracion = duracion;
			this.cantidadReproduccion=cantidadReproduccion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar la cancion "+nombre);
		}
		ConexionBD.desconectar();
	}

	public Estilos getEstiloCancion() {
		return estiloCancion;
	}

	public void setEstiloCancion(Estilos estiloCancion) {
		this.estiloCancion = estiloCancion;
	}

	public LocalDateTime getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	public void setFechaIncorporacion(LocalDateTime fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) throws SQLException {
		ObjetoConSonido ocs = new ObjetoConSonido();
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update cancion set artista = '"+artista+"' where nombre='"+ocs.getNombre()+"'"
				)>0) {
			this.artista = artista;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido cambiar el artista de la cancion");
		}
		ConexionBD.desconectar();
		
	}
	
	
	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) throws SQLException{
		
		ObjetoConSonido ocs = new ObjetoConSonido();
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update cancion set duracion = '"+duracion+"' where nombre='"+ocs.getNombre()+"'"
				)>0) {
			this.duracion = duracion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido cambiar la duracion de la cancion");
		}
		ConexionBD.desconectar();	
		
	}

	public Estilos getEstilos() {
		return estiloCancion;
	}

	public void setEstilos(Estilos estiloCancion) throws SQLException {
		
		ObjetoConSonido ocs = new ObjetoConSonido();
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update cancion set estilocancion = '"+estiloCancion+"' where nombre='"+ocs.getNombre()+"'"
				)>0) {
			this.estiloCancion = estiloCancion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido actualizar el estilo de la cancion");
		}
		ConexionBD.desconectar();
	
	}

	public int getCantidadReproduccion() {
		return cantidadReproduccion;
	}

	public void setCantidadReproduccion(int cantidadReproduccion) throws SQLException {
		
		ObjetoConSonido ocs = new ObjetoConSonido();
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update cancion set cantidadreproducciones = '"+cantidadReproduccion+"' where nombre='"+ocs.getNombre()+"'"
				)>0) {
			this.cantidadReproduccion = cantidadReproduccion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido actualizar la cantidad de reproducciones de la cancion");
		}
		ConexionBD.desconectar();
		
		
	}

	@Override
	public String toString() {
		return "Cancion"
				+ "\n nombre=" + this.getNombre() 
				+ "\n artista=" + this.getArtista().getNombre() 
				+ "\n cantidad Reproducciones=" + this.cantidadReproduccion
				+ "\n duracion=" + this.getDuracion();
	}
	
	
	
}
