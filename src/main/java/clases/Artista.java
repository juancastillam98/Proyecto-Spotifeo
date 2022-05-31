package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConexionBD;

public class Artista extends Usuario{
	private ArrayList<PlayList> discograf�a;
	//artista de pruebe
	public Artista(String email, String nombre,  Blob foto, String contrase�a, Boolean esPremium) throws SQLException { 
		super(email, nombre, foto, contrase�a, esPremium);
	}

	public Artista(String email, String nombre,  Blob foto, String contrase�a, Boolean esPremium,  ArrayList<PlayList> discograf�a) 
			throws SQLException {
		super(email, nombre, foto, contrase�a, esPremium);
		this.discograf�a=new ArrayList<PlayList>();
		Usuario usr = new Usuario();
		
		String ret="";
		for (PlayList lc : discograf�a) {
			ret+=lc+"\n";
		}
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into artista values ('"+email+"','"+nombre+"','"+foto+"','"
						+contrase�a+"', "+esPremium+", '"+ret+"')"
				)>0) {
			usr.setEmail(email);
			usr.setNombre(nombre);
			usr.setFoto(foto);
			usr.setContrase�a(contrase�a);
			usr.setesPremium(esPremium);
			this.discograf�a=discograf�a;
			
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido conectar con la BD");
		}
		ConexionBD.desconectar();
		
		this.discograf�a = new ArrayList<PlayList>();
	}
	
	public ArrayList<PlayList> getDiscograf�a() {
		return discograf�a;
		//TODO mirar metodo que devuelve todas las mascotas de un usario
	}

	public void setDiscograf�a(ArrayList<PlayList> discograf�a) {
		this.discograf�a = discograf�a;
	}
	
	
}
