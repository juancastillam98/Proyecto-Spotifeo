package clases;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
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

/**
 * Representa la cancion
 * @author Juan Castilla
 *
 */
public class Cancion extends ObjetoConSonido{
	/**
	 * Almacena el autor de la cancion
	 */
	protected Artista artista;
	/**
	 * Representa la duración de la canción
	 */
	protected int duracion;
	/**
	 * Almacena el estilo al que pertenece la canción
	 */
	protected Estilos estiloCancion;
	/**
	 * Representa la fecha a la que se incorporó la canción
	 */
	protected LocalDateTime fechaIncorporacion;
	/**
	 * Indica la cantidad de reproducciones que tiene la canción.
	 */
	protected int cantidadReproduccion;
	/**
	 * Constructor vacío
	 */
	public Cancion() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Establece el formato predeterminado con el que se insertarán las fechas en la bd.
	 */
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
	 * Constructor que devuelve el nombre de la cancion
	 * @param nombre nombre de la cancion
	 * @throws SQLException
	 */
	public Cancion(String nombre) throws SQLException {
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select nombre from cancion where cancion.nombre = '"+nombre+"'");
		if(consulta.next()) {
			this.setNombre(consulta.getString("nombre"));

		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se encuentra la cancion");
		}
		ConexionBD.desconectar();
	}
	
	public Cancion (Cancion c) {
		
	}
	
	/**
	 * Método que añade una canción a una playlist
	 * @param cancion objeto de canción que se quiera insertar
	 * @param playList objeto de la Playlist donde se quiera insertar
	 * @param usuario objeto de Usuario que inserta la canción
	 * @throws SQLException
	 * @throws UsuarioIncorrectoException
	 */
	public void añadirCancionAPlaylist(Cancion cancion, PlayList playList, Usuario usuario) throws SQLException, UsuarioIncorrectoException{
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
	/**
	 * Getter de Fecha,
	 * @return fecha a la que se insertó en la canción en la base de datos
	 */
	public LocalDateTime getFechaIncorporacion() {
		return fechaIncorporacion;
	}
	/**
	 * Setter de Fecha
	 * @param fechaIncorporacion nueva fecha de incorporación de la canción
	 */
	public void setFechaIncorporacion(LocalDateTime fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	/**
	 * Getter de Artista.
	 * @return Artista al quien pertenece la canción.
	 */
	public Artista getArtista() {
		return artista;
	}
	/**
	 * Método que actuliza el artista, tanto en la clase como en la base de datos
	 * @param artista Nuevo artista
	 * @throws SQLException
	 */
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
	
	/**
	 * Getter de duración de la cancion
	 * @return duración de la canción.
	 */
	public int getDuracion() {
		return duracion;
	}
	/**
	 * Setter de duración. Actuliza tanto en el Objeto Canción como en la base de datos.
	 * @param duracion nueva duración de la canción.
	 * @throws SQLException
	 */
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
	/**
	 * Getter de estilo
	 * @return estilo de la canción
	 */ 
	public Estilos getEstilos() {
		return estiloCancion;
	}
	/**
	 * Setter de estilos. Actuliza también el estilo de la canción en la base de datos
	 * @param estiloCancion nuevo estilo de la canción.
	 * @throws SQLException
	 */
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
	/**
	 * Getter de cantidadDeReproducción
	 * @return la cantidad de reproducciones que tiene la canción
	 */
	public int getCantidadReproduccion() {
		return cantidadReproduccion;
	}
	/**
	 * Setter de cantidad de reproducciones. Actuliza además en la bd
	 * @param cantidadReproduccion nueva cantidad de reproducciones.
	 * @throws SQLException
	 */
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
	/**
	 * función toString, que muestra la información del objeto Cancion
	 * @return String con la información de la canción en varias líneas
	 */
	@Override
	public String toString() {
		return "Cancion"
				+ "\n " + this.getFoto()//foto
				+ "\n " + this.getNombre() 
				+ "\n artista=" + this.artista
				+ "\n fecha=" + this.fechaIncorporacion
				+ "\n duracion=" + this.getDuracion()
				+ "\n Reproducciones=" + this.cantidadReproduccion;
				
	}
	
	
	
}
