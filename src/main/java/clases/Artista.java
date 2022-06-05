package clases;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConexionBD;

public class Artista extends Usuario{
	private ArrayList<PlayList> discografía;
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor que devuelve toda la información de un artista
	 * @param nombre nombre del artista
	 * @throws SQLException
	 */
	public Artista(String nombre) throws SQLException { 
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from usuario where nombre = '"+nombre+"'");
		if(consulta.next()) {
			this.setEmail(consulta.getString("email"));
			this.setNombre(consulta.getString("nombre"));
			this.setFoto(consulta.getString("foto"));
			this.setContraseña(consulta.getString("contraseña"));
			this.setesPremium(consulta.getBoolean("espremium"));
			this.setDiscografía((ArrayList<PlayList>) consulta.getArray("discografica"));
		}else {
			ConexionBD.desconectar();
			//throw new UsuarioNoExisteException("No existe el mail en la BD");
			throw new SQLException("no se ha podido devolver la información del usuario");
		}
		ConexionBD.desconectar();
	}

	public Artista(String email, String nombre,  String foto, String contraseña, Boolean esPremium,  ArrayList<PlayList> discografía) 
			throws SQLException {
		super(email, nombre, foto, contraseña, esPremium);
		this.discografía=new ArrayList<PlayList>();
		
		String ret="";
		for (PlayList lc : discografía) {
			ret+=lc+"\n";
		}
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into artista values ('"+email+"','"+nombre+"','"+foto+"','"
						+contraseña+"', "+esPremium+", '"+ret+"')"
				)>0) {
			this.setEmail(email);
			this.setNombre(nombre);
			this.setFoto(foto);
			this.setContraseña(contraseña);
			this.setesPremium(esPremium);
			this.discografía=discografía;
			
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido conectar con la BD");
		}
		ConexionBD.desconectar();
		
		this.discografía = new ArrayList<PlayList>();
	}
	
	public ArrayList<PlayList> getDiscografía() throws SQLException {
		ArrayList<PlayList> discografia=new ArrayList<PlayList>();
		 //Aqui lo que hay que hacer es un select de playslist where usuario = this.email
		Statement smt = ConexionBD.conectar();
			
			ResultSet consulta = smt.executeQuery("select * from playlist where artista_email = '"+this.getEmail()+"'");
			while(consulta.next()) {
				PlayList listas=new PlayList();
				listas.setFoto(consulta.getString("foto"));
				listas.fechaCreacion=consulta.getTimestamp("fechaincorporacion").toLocalDateTime();
				listas.setNombre(consulta.getString("nombre"));
				listas.usuario=new Usuario(consulta.getString("usuario_email"));
				discografia.add(listas);
			}
			ConexionBD.desconectar();

			return discografia;
	}
	
	

	public void setDiscografía(ArrayList<PlayList> discografía) {
		this.discografía = discografía;
	}
	
	
}
