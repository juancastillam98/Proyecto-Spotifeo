package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConexionBD;

public class Artista extends Usuario{
	private ArrayList<PlayList> discografía;
	//artista de pruebe
	public Artista(String email, String nombre,  Blob foto, String contraseña, Boolean esPremium) throws SQLException { 
		super(email, nombre, foto, contraseña, esPremium);
	}

	public Artista(String email, String nombre,  Blob foto, String contraseña, Boolean esPremium,  ArrayList<PlayList> discografía) 
			throws SQLException {
		super(email, nombre, foto, contraseña, esPremium);
		this.discografía=new ArrayList<PlayList>();
		Usuario usr = new Usuario();
		
		String ret="";
		for (PlayList lc : discografía) {
			ret+=lc+"\n";
		}
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into artista values ('"+email+"','"+nombre+"','"+foto+"','"
						+contraseña+"', "+esPremium+", '"+ret+"')"
				)>0) {
			usr.setEmail(email);
			usr.setNombre(nombre);
			usr.setFoto(foto);
			usr.setContraseña(contraseña);
			usr.setesPremium(esPremium);
			this.discografía=discografía;
			
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido conectar con la BD");
		}
		ConexionBD.desconectar();
		
		this.discografía = new ArrayList<PlayList>();
	}
	
	public ArrayList<PlayList> getDiscografía() {
		return discografía;
		//TODO mirar metodo que devuelve todas las mascotas de un usario
	}

	public void setDiscografía(ArrayList<PlayList> discografía) {
		this.discografía = discografía;
	}
	
	
}
