package clases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;//preguntar si pertenece a esta clase
import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;

import excepciones.UsuarioIncorrectoException;
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
	
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
	/**
	 * Constructor que inserta una nueva cancion en la base de datos
	 * @param foto de indicactiva de la cancion
	 * @param nombre nombre de la cancion
	 * @param artista artista de la cancion
	 * @param estiloCancion estilo al que pertenece la cancion
	 * @param fechaIncorporacion fecha en la que se inserta en la base de datos
	 * @param duracion duracion de la canción
	 * @param cantidadReproduccion cantidad de reproducciones que tiene
	 * @throws SQLException
	 */
	public Cancion(String foto, String nombre, Artista artista, Estilos estiloCancion, 
			LocalDateTime fechaIncorporacion, int duracion, int cantidadReproduccion) throws SQLException {
		super();
		if (foto==null) {//compruebo si el campo foto está null
			foto="./fotos/notaMusical.png";
		}
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into cancion values ('"+foto.replace((char) 92, '/')+"','"+nombre+"','"+artista.getEmail()+"','"+estiloCancion+"',"
						+ "'"+fechaIncorporacion.format(formatoFechaHora)+"',"+duracion+","+cantidadReproduccion+")"				
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
	/**
	 * Método que añade una canción a una playlist
	 * @param cancion
	 * @param playList
	 * @param usuario
	 * @throws SQLException
	 * @throws UsuarioIncorrectoException
	 */
	public void añadirCancionAPlaylist(Cancion cancion, PlayList playList, Artista usuario) throws SQLException, UsuarioIncorrectoException{
		//insert into almacenacanciones values('cancion1', 'miplaylist', 'juan@juan');
		Statement smt = ConexionBD.conectar();
		PlayList p = new PlayList();
		if(smt.executeUpdate(
				"insert into almacenacanciones values('"+cancion+"','"+playList+"','"+usuario.getEmail()+"')"
				)>0) {
			this.setNombre(cancion.getNombre());
			p.setNombre(playList.getNombre());
			p.usuario=new Usuario(usuario.getEmail());
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la lista");
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
