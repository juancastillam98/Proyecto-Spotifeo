package clases;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;

import utils.ConexionBD;

public class Usuario extends ObjetoConNombre{
	private String email;
	private Boolean esPrimium;
	private String contraseña;
	
	private Usuario(String nombre, Blob foto) {//constructor para recorrer valores de del
		super(nombre, foto);
	}
	/**
	 * Constructor que inserta datos del usuario en la bd
	 * @param nombre del usuario
	 * @param fotoArray foto de perfil del usuario
	 * @param email email del usuario
	 * @param esPrimium true si es usuario Premium, false en caso contrario
	 * @param contraseña password del usuario
	 * @throws SQLException 
	 */
	public Usuario(String email, String nombre, Blob fotoArray,  String contraseña, Boolean esPrimium) throws SQLException {
		//super(nombre, fotoArray); no se como representar el dao, cuando hereda
		ObjetoConNombre objNombre = new ObjetoConNombre();
		
		Statement cnx = ConexionBD.conectar();
		if (cnx.executeUpdate(
				"insert into usuario values ('"+email+"','"+nombre+"','"+fotoArray+"','"+contraseña+"', "+esPrimium+")"
				)>0) {
			objNombre.setNombre(nombre);
			objNombre.setFoto(fotoArray);
			this.email = email;
			this.contraseña = contraseña;
			this.esPrimium = esPrimium;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la bd");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Constructor que que nos devulve información del usuario de la bd
	 * @param email email del usuario
	 * @param contraseña password del usuario
	 * @throws SQLException 
	 */
	public Usuario(String email, String contraseña) throws SQLException {
		super();
		ObjetoConNombre objNombre = new ObjetoConNombre();
		
		Statement cnx = ConexionBD.conectar();
		ResultSet consulta= cnx.executeQuery("select * from usuario where email='"+email+"'");
		if(consulta.next()) {
			objNombre.setNombre(consulta.getString("nombre"));
			objNombre.setFoto(consulta.getBlob("foto"));
			this.email=consulta.getString("email");
			this.esPrimium=consulta.getBoolean("esPrimium");
			this.contraseña=consulta.getString("contraseña");
		}else {
			ConexionBD.desconectar();
		}
		ConexionBD.desconectar();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<ListaCanciones> getBiblioteca() {//método que devuelve un arraylist de arraylist
		 ArrayList<ListaCanciones> listaCanciones=new ArrayList<ListaCanciones>();
		 //Aqui lo que hay que hacer es un select de listaCanciones where usuario = this.email
		 return listaCanciones;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Boolean getEsPrimium() {
		return esPrimium;
	}
	public void setEsPrimium(Boolean esPrimium) {
		this.esPrimium = esPrimium;
	}
	
}
