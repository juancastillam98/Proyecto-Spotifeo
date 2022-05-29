package clases;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;

import utils.ConexionBD;

public class Usuario extends ObjetoConNombre{
	private String email;
	private Boolean esPrimium;
	private String contrase�a;
	
	private Usuario(String nombre, byte[] foto) {//constructor para recorrer valores de del
		super(nombre, foto);
	}
	/**
	 * Constructor que inserta datos del usuario en la bd
	 * @param nombre del usuario
	 * @param fotoArray foto de perfil del usuario
	 * @param email email del usuario
	 * @param esPrimium true si es usuario Premium, false en caso contrario
	 * @param contrase�a password del usuario
	 * @throws SQLException 
	 */
	public Usuario(String email, String nombre, byte[] fotoArray,  String contrase�a, Boolean esPrimium) throws SQLException {
		super(nombre, fotoArray);
	
		
		Statement cnx = ConexionBD.conectar();
		if (cnx.executeUpdate(
				"insert into usuario values ('"+email+"','"+nombre+"','"+fotoArray+"','"+contrase�a+"', "+esPrimium+")"
				)>0) {
			nombre=nombre;
			fotoArray=fotoArray;
			this.email = email;
			this.contrase�a = contrase�a;
			this.esPrimium = esPrimium;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la bd");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Constructor que que nos devulve informaci�n del usuario de la bd
	 * @param email email del usuario
	 * @param contrase�a password del usuario
	 * @throws SQLException 
	 */
	public Usuario(String email, String contrase�a) throws SQLException {
		super();
		
		Statement cnx = ConexionBD.conectar();
		ResultSet consulta= cnx.executeQuery("select * from usuario where email='"+email+"'");
		if(consulta.next()) {
			this.email=consulta.getString("email");
			this.esPrimium=consulta.getBoolean("esPrimium");
			this.contrase�a=consulta.getString("contrase�a");
		}else {
			ConexionBD.desconectar();
		}
		ConexionBD.desconectar();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<ListaCanciones> getBiblioteca() {//m�todo que devuelve un arraylist de arraylist
		 ArrayList<ListaCanciones> listaCanciones=new ArrayList<ListaCanciones>();
		 //Aqui lo que hay que hacer es un select de listaCanciones where usuario = this.email
		 return listaCanciones;
	}
	
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public Boolean getEsPrimium() {
		return esPrimium;
	}
	public void setEsPrimium(Boolean esPrimium) {
		this.esPrimium = esPrimium;
	}
	
}
