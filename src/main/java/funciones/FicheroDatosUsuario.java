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

import clases.Usuario;
import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;
import utils.ConexionBD;

public class FicheroDatosUsuario {
	public static void leerDatos() {
		String[] contenidoFichero = new String[4];
		try {
			BufferedReader leer=new BufferedReader(new  FileReader("./datosUsuario.txt"));
			String linea = leer.readLine();
			String texto = "";
			while(linea!=null) {
				texto+=linea+"\n";
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
	}
	/**
	 * Función que recupera todos los datos del usuario pasados por parámetros
	 * @param email email del usuario del que se desea obtener información
	 * @param contraseña contraseña del usario del que se desea obtener información.
	 * @throws SQLException
	 * @throws ContraseñaIncorrectaException
	 * @throws UsuarioIncorrectoException
	 * @throws EmailInvalidoException
	 */
	public static void obtenerDatosUsuario(String email, String contraseña) throws SQLException, ContraseñaIncorrectaException, UsuarioIncorrectoException, EmailInvalidoException {
		File carpeta = new File("./");
		Statement cnx = ConexionBD.conectar();
		ResultSet consulta = cnx.executeQuery("select * from usuario where email='" + email + "'");
		if (consulta.next()) {
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
