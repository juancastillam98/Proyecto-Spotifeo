package principal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import clases.Artista;
import clases.Cancion;
import clases.Estilos;
import clases.ObjetoConSonido;
import clases.Usuario;
import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) throws java.sql.SQLIntegrityConstraintViolationException, SQLException {
		Blob imagenBlob=null;
		BufferedImage foto=null;
		byte[] fotoArray = null;
		try {
			foto = ImageIO.read(new File("./fotos/UsuarioHombreDefault.jpg"));
			ByteArrayOutputStream fotoEnByte = new ByteArrayOutputStream();//convierto la imagen a un arrayde bytes
			ImageIO.write(foto, "jpg", fotoEnByte);
			fotoEnByte.flush();
			fotoArray = fotoEnByte.toByteArray();
			imagenBlob=new SerialBlob(fotoArray);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.err.println("No se encuentra la imagen");
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		//Prueba de inserción en la bd, usuario
		try {
			Usuario prueba = new Usuario("prueba@gmail.com", "pepe", imagenBlob, "juan", false);
			System.out.println("Usuario Insertado correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("error, no se ha podido insertar el usuario ");
		}
		
		
		
		//Artista lostFrecuencies = new Artista("losfrecuencies@gmail.com", "lostfrecuencies", imagenBlob, "juan", true, null);
		Artista pruebaArtista = new Artista("probando@gmail.com", "probando", imagenBlob, "juan", true);
		System.out.println("Artista insertado correctamente");
		
		Cancion pruebaCancion = new Cancion("cancionPrueba", pruebaArtista, imagenBlob,
				"./musica/Angèle - Flou (Lost Frequencies Remix).mp3", 110, Estilos.POP, 100);
		
		
		
		ObjetoConSonido objSonido=new ObjetoConSonido(
				"prueba", null, "./musica/Angèle - Flou (Lost Frequencies Remix).mp3");
		
	}

}
