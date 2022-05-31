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
	
	protected Artista artista;
	protected int duracion;
	protected Estilos estiloCancion;
	protected int cantidadReproduccion;
	
	public Cancion(String nombre, Artista artista, Blob foto, String ruta,  
			int duracion, Estilos estiloCancion, int cantidadReproduccion) throws SQLException {
		super(nombre, foto, ruta);
		
		ObjetoConSonido ocs = new ObjetoConSonido();
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into cancion values ('"+nombre+"','"+artista+"','"+estiloCancion+"',"+duracion+",'"+foto+"',"+cantidadReproduccion+")"				
				)>0) {
			ocs.setNombre(nombre);
			this.artista = artista;//quiero insertar el nombre del artista
			this.estiloCancion = estiloCancion;
			this.duracion = duracion;
			ocs.setFoto(foto);
			this.cantidadReproduccion=cantidadReproduccion;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar la cancion "+nombre);
		}
		ConexionBD.desconectar();
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

	public Estilos getestiloCancion() {
		return estiloCancion;
	}

	public void setestiloCancion(Estilos estiloCancion) throws SQLException {
		
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

	
	
	
}
