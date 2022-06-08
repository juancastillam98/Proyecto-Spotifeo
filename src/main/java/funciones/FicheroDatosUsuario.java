package funciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import clases.Usuario;
import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;
import utils.ConexionBD;

public class FicheroDatosUsuario {
	public static void leerDatos() {
		
	}
	/**
	 * Función que recupera todos los datos del usuario pasados por parámetros, y los devuelve en orden de inserción
	 * @param email email del usuario del que se desea obtener información
	 * @param contraseña contraseña del usario del que se desea obtener información.
	 * @throws SQLException
	 * @throws ContraseñaIncorrectaException
	 * @throws UsuarioIncorrectoException
	 * @throws EmailInvalidoException
	 */
	public static Queue<String> obtenerDatosUsuario(String email) throws SQLException, ContraseñaIncorrectaException, UsuarioIncorrectoException, EmailInvalidoException {
		Queue<String> datos = new LinkedList<>();
		//Vector<String> datos = new Vector<>();
		//LinkedHashMap<Byte, String> datos = new LinkedHashMap<>();
		try {
			BufferedReader leer=new BufferedReader(new  FileReader("./datosUsuario.txt"));
			String linea = leer.readLine();
			String texto = "";
			while(linea!=null) {
				texto=linea+"\n";
				datos.add(texto);
				linea=leer.readLine();
				
			}
			leer.close();
			System.out.println(texto);
		} catch (FileNotFoundException e) {
			System.err.println("No se ha podido leer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
		
	}
	/***
	 * Función que recoge el email y contraseña de usuario y llama a una función que los inserta en un fichero
	 * @param email email del usuario
	 * @param contraseña contraseña del usuario
	 * @throws SQLException
	 * @throws ContraseñaIncorrectaException
	 * @throws UsuarioIncorrectoException
	 * @throws EmailInvalidoException
	 */
	public static void añadirDatosFicheros(String email) throws SQLException, ContraseñaIncorrectaException, UsuarioIncorrectoException, EmailInvalidoException {
		File carpeta = new File("./");
		Statement cnx = ConexionBD.conectar();
		ResultSet consulta = cnx.executeQuery("select * from usuario where email='" + email + "'");
		if (consulta.next()) {
			//String contraseña=consulta.getString("contraseña");// la contraseña del usuario es privada, luego no tiene sentido almacenarla, si no se va mostrar en ningún sitio
			String nombreUsr = consulta.getString("nombre");
			String foto = consulta.getString("foto");
			Boolean esPremium = consulta.getBoolean("esPremium");
			insertarDatosEnFichero(carpeta, email, nombreUsr, foto, esPremium);
		} else {
			ConexionBD.desconectar();
			throw new UsuarioIncorrectoException("No existe el email "+email);
		}
		ConexionBD.desconectar();
		
	}
		

	public static void insertarDatosEnFichero(File carpeta, String email, String nombre, String foto, Boolean esPremium) {
		try {
			FileWriter escribir = new FileWriter("./datosUsuario.txt");
			escribir.write(email+"\n");
			escribir.write(nombre+"\n");
			escribir.write(foto+"\n");
			escribir.write(esPremium+"\n");
			escribir.flush();
			escribir.close();
		} catch (IOException e) {
			System.err.println("No se encuentra el .class");
		}
				
	}
}
