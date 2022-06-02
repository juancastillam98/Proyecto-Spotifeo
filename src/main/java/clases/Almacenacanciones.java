package clases;

import java.sql.SQLException;
import java.sql.Statement;

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

}
