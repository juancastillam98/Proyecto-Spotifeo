package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	private final static String cadenaConexionBD="jdbc:mysql://127.0.0.1:3310/spotifeo";
	private final static String usuarioBD = "root";
	private final static String passwordBD = "";
	private static Connection conexion;
	
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
