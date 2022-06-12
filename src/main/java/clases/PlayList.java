package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;

import excepciones.ContraseñaIncorrectaException;
import excepciones.UsuarioIncorrectoException;
import utils.ConexionBD;
/**
 * Representa una playlist, o sea una lista de canciones
 * @author Juan Castilla
 *
 */
public class PlayList extends ObjetoConNombre{
	/**
	 * Indica el usuario a quien pertenece la playlist
	 */
	protected Usuario usuario; //usuario que lo crea, el email
	/**
	 * Fecha de creación de la playlist
	 */
	protected LocalDateTime fechaCreacion;

	/**
	 * Constructor vacío
	 */
	public PlayList() {	}
	
	/**
	 * Formato de inserción de fechas en la base de datos
	 */
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
	/**
	 * Constructor que inserta una nueva playlist en la base de datos
	 * @param foto foto de la playlist
	 * @param nombre nombre de la playlist
	 * @param usuario email del usuario
	 * @param fechaCreacion fecha de creación de la playlist
	 * @throws SQLException
	 */
	public PlayList(String foto, String nombre, Usuario usuario, LocalDateTime fechaCreacion) throws SQLException {
		super();
		if (foto==null) {//compruebo si el campo foto está null
			foto="./fotos/notaMusical.png";
		}
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into playlist values('"+foto.replace((char) 92, '/')+"','"+nombre+"','"+usuario.getEmail()+"','"+fechaCreacion.format(formatoFechaHora)+"')"
				)>0) {
		}
		this.setNombre(nombre);//nombre de la lista
		this.setFoto(foto);
		this.usuario=usuario;//el usuario
		this.fechaCreacion=fechaCreacion;
		
		ConexionBD.desconectar();
	}
	 
	/***
	 * Metodo que inserta una cancion en una playlist
	 * @param cancion cancion a insertar en la lista
	 * @param usuario usuario que inserta la cancion
	 * @param playList id de la playlist
	 * @return 
	 * @throws SQLException 
	 * @throws UsuarioIncorrectoException 
	 */
	public void añadirCancion(Cancion cancion, Artista usuario) throws SQLException, UsuarioIncorrectoException{
		//insert into almacenacanciones values('cancion1', 'miplaylist', 'juan@juan');
		Statement smt = ConexionBD.conectar();
		Cancion c = new Cancion();
		if(smt.executeUpdate(
				"insert into almacenacanciones values('"+cancion+"','"+this.getNombre()+"','"+usuario.getEmail()+"')"
				)>0) {
			c.setNombre(cancion.getNombre());
			this.setNombre(this.getNombre());
			this.usuario=new Usuario(usuario.getEmail());
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la lista");
		}
		ConexionBD.desconectar();
	}
	/**
	 * Constructor que devuelve toda la información de todas las playlist de un usuario
	 * @param usuario_email
	 * @throws SQLException
	 * @throws UsuarioIncorrectoException
	 */
	public PlayList(String usuario_email) throws SQLException, UsuarioIncorrectoException{
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from playlist where usuario_email = '"+usuario_email+"'");
		if(consulta.next()) {
			this.setFoto(consulta.getString("foto"));
			this.setNombre(consulta.getString("nombre"));
			this.usuario=new Usuario(consulta.getString("usuario_email"));
			this.setFechaCreacion(consulta.getTimestamp("fechaincorporacion").toLocalDateTime());
		}else {
			ConexionBD.desconectar();
			throw new UsuarioIncorrectoException("No existe el email "+usuario_email);
		}
		ConexionBD.desconectar();
	}
	

	/**
	 * Método que devuelve todas las canciones de una playlist
	 * @param listaPlayList nombre de la playlis
	 * @return canciones de una playlist
	 */
	public ArrayList<Cancion> getCancionesPlayList(String nombre){
		ArrayList<Cancion> canciones= new ArrayList<Cancion>();
		Statement smt = ConexionBD.conectar();
		try {
			/*ResultSet cursor = smt.executeQuery("select cancion.foto, cancion.nombre, artista.nombre, "
					+ "cancion.estilocancion, cancion.fechaincorporacion"
					+ "from almacenacanciones "
					+ "join playlist on almacenacanciones.playlist_nombre = playlist.nombre"
					+ "join cancion on almacenacanciones.cancion_nombre = cancion.nombre"
					+ "join artista on cancion.artista_email = artista.email"
					+ "where almacenacanciones.playlist_nombre ='"+nombre+"'");*/
			ResultSet cursor=smt.executeQuery("select cancion.foto, almacenacanciones.cancion_nombre, usuario.nombre, "
					+ "cancion.estilocancion, cancion.fechaincorporacion, cancion.duracion, cancion.cantidadreproducciones\r\n"
					+ "from almacenacanciones \r\n"
					+ "join cancion on almacenacanciones.cancion_nombre = cancion.nombre\r\n"
					+ "join usuario on cancion.artista_email = usuario.email\r\n"
					+ "where almacenacanciones.playlist_nombre ='"+nombre+"'");
			while(cursor.next()) {
				Cancion cancion = new Cancion();
				cancion.setFoto(cursor.getString("foto"));
				cancion.setNombre(cursor.getString("cancion_nombre"));
				
				//System.out.println("contenido artista....... "+cursor.getString("nombre"));
				
				//cancion.estiloCancion = new Estilos(cursor.getString("cancion.estilocancion"));
				//cancion.estiloCancion = Estilos.valueOf(cursor.getString("estilocancion"));
				cancion.fechaIncorporacion=cursor.getTimestamp("fechaincorporacion").toLocalDateTime();
				cancion.duracion=cursor.getInt("duracion");
				cancion.cantidadReproduccion=cursor.getInt("cantidadreproducciones");
				//cancion.artista=new Artista(cursor.getString("nombre"));
				cancion.artista=(Artista) this.usuario;
				canciones.add(cancion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return canciones;
		
	}
	/**
	 * Getter que devuelve el usuario
	 * @return usuario a quien pertenece
	 */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
	 * Setter de usuario
	 * @param usuario, nuevo Usuario a quien pertence
	 */

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * Getter de fecha,
	 * @return fecha de incorporación de la cancion.
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * Setter de cancion
	 * @param fechaCreacion nueva Fecha de incorporación de la cancion
	 */
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * función toString, que muestra la información de Playlist
	 * @return String con la información del objeto cancion en una sola línea
	 */
	@Override
	public String toString() {
		return "Playlist | foto=" + this.getFoto()+" -  nombre=" + this.getNombre()+
				"- usuario " + this.getUsuario()+"- fecha creacion" + this.fechaCreacion;
	}

	
	
	

}
