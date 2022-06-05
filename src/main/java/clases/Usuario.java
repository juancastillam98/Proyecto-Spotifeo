package clases;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.ConexionBD;

public class Usuario extends ObjetoConNombre {
	private String email;
	private Boolean esPremium;
	private String contrase�a;

	public Usuario() {// constructor para recorrer valores de del
		super();
	}

	/**
	 * Constructor que inserta datos del usuario en la bd
	 * 
	 * @param nombre     del usuario
	 * @param fotoArray  foto de perfil del usuario
	 * @param email      email del usuario
	 * @param esPremium  true si es usuario Premium, false en caso contrario
	 * @param contrase�a password del usuario
	 * @throws SQLException
	 */
	public Usuario(String email, String nombre, String fotoArray,  String contrase�a, Boolean esPremium)
			throws SQLException {
		super(); // no se como representar el dao, cuando hereda
		
		Statement cnx = ConexionBD.conectar();

		if (cnx.executeUpdate("insert into usuario values ('"+email+"','"+nombre+"','"+fotoArray+"','"
				+contrase�a+"', "+esPremium+")"
				) > 0) {
			this.email = email;
			this.setNombre(nombre);
			this.setFoto(fotoArray);
			this.contrase�a = contrase�a;
			this.esPremium = esPremium;
		} else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la bd");
		}
		
		ConexionBD.desconectar();
	}

	/**
	 * Constructor que que nos devulve informaci�n del usuario de la bd
	 * 
	 * @param email      email del usuario
	 * @param contrase�a password del usuario
	 * @throws SQLException
	 */
	public Usuario(String email, String contrase�a) throws SQLException {
		super();

		Statement cnx = ConexionBD.conectar();
		ResultSet consulta = cnx.executeQuery("select * from usuario where email='" + email + "'");
		if (consulta.next()) {
			this.email = consulta.getString("email");
			this.setNombre(consulta.getString("nombre"));
			this.setFoto(consulta.getString("foto"));
			this.contrase�a = consulta.getString("contrase�a");
			this.esPremium = consulta.getBoolean("esPremium");
		} else {
			ConexionBD.desconectar();
		}
		ConexionBD.desconectar();
	}
	/**
	 * Constructor que devuelve toda la informaci�n de un usuario concreto
	 */
	public Usuario(String email) throws SQLException {
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from usuario where email = '"+email+"'");
		if(consulta.next()) {
			this.email=consulta.getString("email");
			this.setNombre(consulta.getString("nombre"));
			this.esPremium=consulta.getBoolean("espremium");
			this.contrase�a=consulta.getString("contrase�a");
		}else {
			ConexionBD.desconectar();
			//throw new UsuarioNoExisteException("No existe el mail en la BD");
			throw new SQLException("no se ha podido devolver la informaci�n del usuario");
		}
		ConexionBD.desconectar();
		
	}

	/**
	 * m�todo que devuelve las listas de canciones de un usuario
	 * @return ArrayList de playslist de un usuario
	 * @throws SQLException 
	 */
	public ArrayList<PlayList> getBiblioteca() throws SQLException {//m�todo que devuelve un arraylist de arraylist
		 ArrayList<PlayList> biblioteca=new ArrayList<PlayList>();
		 //Aqui lo que hay que hacer es un select de playslist where usuario = this.email
		Statement smt = ConexionBD.conectar();
			
			ResultSet consulta = smt.executeQuery("select * from playlist where usuario_email = '"+this.email+"'");
			while(consulta.next()) {
				PlayList listas=new PlayList();
				listas.setFoto(consulta.getString("foto"));
				listas.fechaCreacion=consulta.getTimestamp("fechaincorporacion").toLocalDateTime();
				listas.setNombre(consulta.getString("nombre"));
				listas.usuario=new Usuario(consulta.getString("usuario_email"));
				biblioteca.add(listas);
			}
			ConexionBD.desconectar();

			return biblioteca;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws SQLException {
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set email ='"+email+"' where email = '"+this.email+"'"
				)>0) {
			this.email=email;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el email");
		}
		ConexionBD.desconectar();
	}
	
	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) throws SQLException {


		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set contrase�a ='"+contrase�a+"' where email = '"+this.email+"'"
				)>0) {
			this.contrase�a=contrase�a;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el la contrase�a");
		}
		ConexionBD.desconectar();
		
	}

	public Boolean getesPremium() {
		return esPremium;
	}

	public void setesPremium(Boolean esPremium) throws SQLException {
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set espremium ="+esPremium+" where email = '"+this.email+"'"
				)>0) {
			this.esPremium=esPremium;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el estado de Premium");
		}
		ConexionBD.desconectar();
		
	}
	/**
	 * Metodo para cambiar el nombre de usuario
	 * @param nombre Nuevo nombre del usuario
	 * @throws SQLException
	 */
	public void cambiaNombre(String nombre) throws SQLException {
		Statement smt= ConexionBD.conectar();

		if(smt.executeUpdate(
				"update usuario set nombre = '"+nombre+"' where email='"+this.email+"'"
				)>0) {
			this.setNombre(nombre);
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el nombre");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Metodo para cambiar la foto de usuario
	 * @param nombre Nuevo nombre del usuario
	 * @throws SQLException
	 */
	public void cambiaFoto(String foto) throws SQLException {
		Statement smt= ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set foto = '"+foto+"' where email='"+this.email+"'"
				)>0) {
			this.setFoto(foto);
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el nombre");
		}
		ConexionBD.desconectar();
	}
	

	@Override
	public String toString() {
		return "Usuario"
				+ "\n email=" + this.getEmail()
				+ "\n nombre=" + this.getNombre()
				+ "\n foto=" + this.getFoto()
				+ "\n pass=" + "********"
				+ "\n activo=" + this.esPremium;
	}

	

}
