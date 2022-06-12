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
import java.time.format.DateTimeFormatter;
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
import excepciones.Contrase�aIncorrectaException;
import excepciones.NombreInvalidoException;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioYaExiste;
import funciones.FicheroDatosUsuario;
import hilos.MusicaReproducir;
import interfacesGraficas.Ventana;
import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) {
		//ARGUMENTOS
		String contrase�aDefault=null;
		String emailDefault=null;
		
		for(byte i=0;i< args.length; i++) {
			if(args[i].equals("-email")){//por ejemplo, si le paso -nombre
				emailDefault=args[i+1];//indico que me coja lo siguiente, es decir el nombre
			}
			if(args[i].equals("-contrase�a")){
				contrase�aDefault=args[i+1];
			}
			if (args[i].equals("-h") || args[i].equals("-help") || args[i].equals("-!")) {
				System.out.println("Programa para dibujar un texto con un pero"
						+"\nArgumentos posibles"
						+"\n\t-email establece el email del usuario"
						+"\n\t-contrase�a establece la contrase�a usuario");
				System.exit(0);
			}
		}

		if(emailDefault!=null) {//si hay email
			System.out.println("Recibido por argumentos " + emailDefault);
		}
		if (contrase�aDefault!=null) {//si hay contrase�a
			System.out.println("Recibido por argumentos " + contrase�aDefault);
		}
		
		//HASTA AQUI
		
		
		
		//Musica musica = new Musica(new File("./musica/Ang�le - Flou (Lost Frequencies Remix).mp3"));
		 /*
				Artista probando=null;
				try {
					 probando= new Artista("probando@gmail.com", "probando", "./fotos/UsuarioHombreDefault.jpg", 
								"juan", true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NombreInvalidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Contrase�aIncorrectaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UsuarioYaExiste e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//se inserta correctamente, pero no entiendo por que no se muestra
				System.out.println("nombre --> artista "+probando.getNombre()); 
				System.out.println("foto --> artista "+probando.getFoto());
				System.out.println("contrase�a --> artista "+probando.getContrase�a());
				System.out.println("email --> artista "+probando.getEmail());
				System.out.println("Sigue por aqui 1");
				Cancion pruebaDeSonido = null;
				try {
					pruebaDeSonido = new Cancion(null, "Ang�le - Flou (Lost Frequencies Remix).mp3", 
							probando, Estilos.EDM, LocalDateTime.now(), 10, 110);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				System.out.println("Sigue por aqui 2");
				MusicaReproducir reproducir = new MusicaReproducir(pruebaDeSonido);
				reproducir.start();
				//ObjetoConSonido objetoConSonido=new ObjetoConSonido(pruebaDeSonido, null, "./musica");
				//pruebaDeSonido.getReproducirCancion();
				//ObjetoConSonido objetoConSonido = new Cancion();
				//pruebaDeSonido.getReproducirCancion();
		*/
		Ventana ventana = new Ventana(args);
		
		 // PRUEBA DE INSERCI�N 
		
		
		

	}

}
