package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Representa la conexión con la base de datos
 * @author Juan Castilla
 *
 */
public class ConexionBD {
	/**
	 * Cadena de conexión con la base de datos
	 */
	private final static String cadenaConexionBD="jdbc:mysql://127.0.0.1:3310/spotifeo";
	/**
	 * Usuario para conectarse a la bd
	 */
	private final static String usuarioBD = "root";
	/**
	 * Contraseña para conectarse a la bd
	 */
	private final static String passwordBD = "";
	/**
	 * Inicio de conexión
	 */
	private static Connection conexion;
	/**
	 * Método para iniciar una conexión con la bd
	 * @return conexión con bd
	 */
	public static Statement conectar() {
		try {
			if(conexion == null) {
				conexion = DriverManager.getConnection(cadenaConexionBD, usuarioBD, passwordBD);
			}
			 return conexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Método para desconectarse de la bd
	 */
	public static void desconectar() {
		if(conexion!=null) {
			try {
				conexion.close();
				conexion=null;
				}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
