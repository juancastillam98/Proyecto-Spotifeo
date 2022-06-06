package principal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import clases.Artista;
import clases.Cancion;
import clases.Estilos;
import clases.ObjetoConSonido;
import clases.PlayList;
import clases.Usuario;
import excepciones.ContraseñaIncorrectaException;
import excepciones.NombreInvalidoException;
import interfacesGraficas.Ventana;
import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) throws java.sql.SQLIntegrityConstraintViolationException, SQLException {
		Ventana ventana = new Ventana();
		
		
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
		Estilos pruebaEstilos = null;
		String fotoString="./fotos/UsuarioHombreDefault.jpg";

		//Prueba de inserción en la bd, usuario
		Usuario prueba=null;
		try {
			prueba = new Usuario("prueba@gmail.com", "pepe", fotoString, "juan", false);
			System.out.println("Usuario Insertado correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("error, no se ha podido insertar el usuario ");
		} catch (NombreInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContraseñaIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<PlayList> pruebArrayList=new ArrayList<PlayList>();
		
		//Artista lostFrecuencies = new Artista("losfrecuencies@gmail.com", "lostfrecuencies", imagenBlob, "juan", true, null);
		Artista pruebaArtista = new Artista("probando@gmail.com", "probando", fotoString, "juan", false, pruebArrayList);
		System.out.println("Artista insertado correctamente");
		
		Cancion pruebaCancion = new Cancion(fotoString, "cancionPrueba", pruebaArtista, pruebaEstilos.EDM, LocalDateTime.now(), 110, 100);
		System.out.println(pruebaCancion.getNombre() +" esto es");
		PlayList pruePlayList=new PlayList("playlist1", fotoString,  prueba, LocalDateTime.now());
		pruePlayList.añadirCancion(pruebaCancion, pruePlayList, pruebaArtista);
		
		ArrayList<Cancion> cancionesDePlayList = pruePlayList.getCancionesPlayList(pruePlayList);
		//pruePlayList.getCancionesPlayList(pruePlayList);//devuelve un ArrayList<Cancion>
		
		String resultadoCancione="";
		for (Cancion c : cancionesDePlayList) {
			resultadoCancione+=c+"\n";
		}
		System.out.println(resultadoCancione);
		
		ObjetoConSonido objSonido=new ObjetoConSonido(
				"prueba", null, "./musica/Angèle - Flou (Lost Frequencies Remix).mp3");
		
	}

}
