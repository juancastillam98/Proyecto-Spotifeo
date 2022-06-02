package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConexionBD;

public class Almacenacanciones {
	private Cancion cancion;
	private PlayList playlist;
	private Usuario usuario;
	
	public Almacenacanciones() {
		
	}
	/***
	 * Constructor que inserta una cancion en una playlist
	 * @param cancion cancion a insertar en la lista
	 * @param usuario usuario que inserta la cancion
	 * @param playList id de la playlist
	 * @throws SQLException 
	 */
	public Almacenacanciones(Cancion cancion, Artista usuario, PlayList playList) throws SQLException{
		//insert into almacenacanciones values('cancion1', 'miplaylist', 'juan@juan');
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into listacanciones values('"+cancion+"','"+playList+"','"+usuario.getEmail()+"')"
				)>0) {
			this.playlist=playList;
			this.cancion=cancion;
			this.usuario=usuario;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la lista");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Método que devuelve todas las canciones de una playlist
	 * @return canciones de una playlist
	 */
	public ArrayList<Almacenacanciones> getCanciones(PlayList listaPlayList){
		/*select almacenacanciones.cancion_nombre 
		from almacenacanciones join playlist on almacenacanciones.playlist_nombre = playlist.nombre
		where almacenacanciones.playlist_nombre ='miplaylist';

		 * */
		ArrayList<Almacenacanciones> canciones = new ArrayList<Almacenacanciones>();
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
				cancion.setFoto(cursor.getBlob("cancion.foto"));
				cancion.setNombre(cursor.getString("cancion.nombre"));
				artista.setNombre(cursor.getString("artista.nombre"));
				cancion.estiloCancion = new Estilos(cursor.getString("cancion.estilocancion"));
				cancion 
				//cancion.setestiloCancion = new Estilos(cursor.getString("cancion.estilocancion"));
				//cancion.getArtista()
				//cancion.setNombre(cursor.getString(""));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return canciones;
		
	}
	

}
