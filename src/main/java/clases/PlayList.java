package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;

import excepciones.ContraseñaIncorrectaException;
import excepciones.UsuarioIncorrectoException;
import utils.ConexionBD;

public class PlayList extends ObjetoConNombre{

	protected Usuario usuario; //usuario que lo crea, el email
	protected LocalDateTime fechaCreacion;
	//en el dao, usuario es el nombre de usuario
	public PlayList() {	}
	
	public PlayList(String foto, String nombre, Usuario usuario, LocalDateTime fechaCreacion) throws SQLException {
		super();		
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into playlist values('"+nombre+"','"+foto+"','"+usuario.getEmail()+"','"+fechaCreacion+"')"
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
	public void añadirCancion(Cancion cancion, PlayList playList, Artista usuario) throws SQLException, UsuarioIncorrectoException{
		//insert into almacenacanciones values('cancion1', 'miplaylist', 'juan@juan');
		Statement smt = ConexionBD.conectar();
		Cancion c = new Cancion();
		if(smt.executeUpdate(
				"insert into almacenacanciones values('"+cancion+"','"+playList+"','"+usuario.getEmail()+"')"
				)>0) {
			c.setNombre(cancion.getNombre());
			this.setNombre(playList.getNombre());
			this.usuario=new Usuario(usuario.getEmail());
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la lista");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Método que devuelve todas las canciones de una playlist
	 * @param listaPlayList nombre de la playlis
	 * @return canciones de una playlist
	 */
	public ArrayList<Cancion> getCancionesPlayList(PlayList listaPlayList){
		ArrayList<Cancion> canciones= new ArrayList<Cancion>();
		Statement smt = ConexionBD.conectar();
		try {
			ResultSet cursor = smt.executeQuery("select cancion.foto, cancion.nombre, artista.nombre, "
					+ "cancion.estilocancion, cancion.fechaincorporacion"
					+ "from almacenacanciones "
					+ "join playlist on almacenacanciones.playlist_nombre = playlist.nombre"
					+ "join cancion on almacenacanciones.cancion_nombre = cancion.nombre"
					+ "join artista on cancion.artista_email = artista.email"
					+ "where almacenacanciones.playlist_nombre ='"+listaPlayList+"'");
			while(cursor.next()) {
				Cancion cancion = new Cancion();
				Artista artista = new Artista();
				cancion.setFoto(cursor.getString("cancion.foto"));
				cancion.setNombre(cursor.getString("cancion.nombre"));
				cancion.artista=new Artista(cursor.getString("artista.nombre"));
				//cancion.estiloCancion = new Estilos(cursor.getString("cancion.estilocancion"));
				cancion.estiloCancion = Estilos.valueOf(cursor.getString("cancion.estilocancion"));
				cancion.fechaIncorporacion=cursor.getTimestamp("cancion.fechaincorporacion").toLocalDateTime();
				canciones.add(cancion);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseñaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return canciones;
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Override
	public String toString() {
		return "Playlist | foto=" + this.getFoto()+" -  nombre=" + this.getNombre()+
				"- usuario " + this.getUsuario().email+"- fecha creacion" + this.getFechaCreacion();
	}

	
	
	

}
