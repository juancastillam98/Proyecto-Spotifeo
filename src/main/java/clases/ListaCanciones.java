package clases;

import java.sql.SQLException;
import java.sql.Statement;

import utils.ConexionBD;

public class ListaCanciones {
	private Cancion cancion;
	private Usuario usuario;
	private PlayList playListId;
	
	public ListaCanciones() {
		
	}
	/***
	 * Constructor que inserta una cancion en una playlist
	 * @param cancion cancion a insertar en la lista
	 * @param usuario usuario que inserta la cancion
	 * @param playList id de la playlist
	 * @throws SQLException 
	 */
	public ListaCanciones(Cancion cancion, Artista usuario, PlayList playList) throws SQLException{
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into listacanciones values('"+cancion+"','"+usuario.getEmail()+"','"+playList+"')"
				)>0) {
			this.cancion=cancion;
			this.usuario=usuario;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la lista");
		}
		ConexionBD.desconectar();
	}

}
