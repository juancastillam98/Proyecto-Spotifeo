package principal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
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
import funciones.FicheroDatosUsuario;
import interfacesGraficas.Ventana;
import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) throws java.sql.SQLIntegrityConstraintViolationException, SQLException {
		//ARGUMENTOS
		String contraseñaDefault=null;
		String emailDefault=null;
		
		for(byte i=0;i< args.length; i++) {
			if(args[i].equals("-email")){//por ejemplo, si le paso -nombre
				emailDefault=args[i+1];//indico que me coja lo siguiente, es decir el nombre
			}
			if(args[i].equals("-contraseña")){
				contraseñaDefault=args[i+1];
			}
			if (args[i].equals("-h") || args[i].equals("-help") || args[i].equals("-!")) {
				System.out.println("Programa para dibujar un texto con un pero"
						+"\nArgumentos posibles"
						+"\n\t-email establece el email del usuario"
						+"\n\t-contraseña establece la contraseña usuario");
				System.exit(0);
			}
		}

		if(emailDefault!=null) {//si hay email
			System.out.println("Recibido por argumentos " + emailDefault);
		}
		if (contraseñaDefault!=null) {//si hay contraseña
			System.out.println("Recibido por argumentos " + contraseñaDefault);
		}
		
		//HASTA AQUI
		
		Ventana ventana = new Ventana(args);
		
		Usuario pruebaUsuario=new Usuario();
		Artista pruebaArtista = new Artista();
		PlayList pruebaPlayList = new PlayList();
		
		//LISTAR TODOS LOS USUARIOS DE LA BASE DE DATOS
		System.out.println("LISTAR USUARIOS DE LA BASE DE DATOS");
		ArrayList<Usuario> listarUsuarios=pruebaUsuario.getTodosUsuarios();
		String resListarUsuario="";
		for (Usuario usuario : listarUsuarios) {
			resListarUsuario+=usuario+"\n";
		}
		System.out.println(resListarUsuario);
		
		System.out.println("Forma 2:");
		System.out.println(pruebaUsuario.mostrarTodosUsuarios());
		System.out.println("---------------------------------------------------\n");
		
		
		System.out.println("LISTAR DATOS DEL USUARIO DESDE UN FICHERO");
		FicheroDatosUsuario.leerDatos();
		//System.out.println(FicheroDatosUsuario.leerDatos());
		System.out.println("---------------------------------------------------\n");
		
		
		//LISTAR TODOS LOS ARTISTAS DE LA BASE DE DATOS ---HE hecho polimorfismo
		System.out.println("LISTAR ARTISTAS DE LA BASE DE DATOS");
		ArrayList<Usuario> listarArtista=pruebaArtista.getTodosArtistas();
		String resListarArtista="";
		for (Usuario artista : listarArtista) {
			resListarArtista+=artista+"\n";
		}
		System.out.println(resListarArtista);
		
		System.out.println("Forma 2:");
		System.out.println(pruebaArtista.mostrarTodosArtistas());
		
		System.out.println("LISTAR ESTILOS DE MUSICA");//ver con miguel
		System.out.println("---------------------------------------------------\n");
		
		
		System.out.println("LISTAR TODAS LAS PLAYLIST / BIBLIOTECA DE UN USUARIO");//LO QUE viene a ser una getBIBLIOTECA
		ArrayList<PlayList> listaDePlaylist = pruebaUsuario.getBiblioteca();
		System.out.println(pruebaUsuario.getBiblioteca());
		//todo, listar todas las canciones de un usuario
		
		/*
		
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
		*/
	}

}
