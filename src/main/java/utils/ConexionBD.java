package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Representa la conexi�n con la base de datos
 * @author Juan Castilla
 *
 */
public class ConexionBD {
	/**
	 * Cadena de conexi�n con la base de datos
	 */
	private final static String cadenaConexionBD="jdbc:mysql://127.0.0.1:3310/spotifeo";
	/**
	 * Usuario para conectarse a la bd
	 */
	private final static String usuarioBD = "root";
	/**
	 * Contrase�a para conectarse a la bd
	 */
	private final static String passwordBD = "";
	/**
	 * Inicio de conexi�n
	 */
	private static Connection conexion;
	/**
	 * M�todo para iniciar una conexi�n con la bd
	 * @return conexi�n con bd
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
	 * M�todo para desconectarse de la bd
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
