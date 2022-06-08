package clases;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.Contrase�aIncorrectaException;
import excepciones.NombreInvalidoException;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioYaExiste;
import utils.ConexionBD;

public class Artista extends Usuario{
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * Constructor que a�ade un nuevo artista a la base de datos
	 * @param email email del artista
	 * @param nombre nombre del artista
	 * @param foto foto del artista
	 * @param contrase�a contrase�a del artista
	 * @param esPremium true si es premium, false en caso contrario
	 * @throws SQLException
	 * @throws NombreInvalidoException
	 * @throws Contrase�aIncorrectaException
	 * @throws UsuarioYaExiste
	 */
	public Artista(String email, String nombre,  String foto, String contrase�a, Boolean esPremium) 
			throws SQLException, NombreInvalidoException, Contrase�aIncorrectaException, UsuarioYaExiste {
		super(email, nombre, foto, contrase�a, esPremium);
		

		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into artista values ('"+email+"','"+nombre+"','"+foto+"','"
						+contrase�a+"', "+esPremium+")"
				)>0) {
			this.setEmail(email);
			this.setNombre(nombre);
			this.setFoto(foto);
			this.setContrase�a(contrase�a);
			this.setesPremium(esPremium);
			
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido conectar con la BD");
		}
		ConexionBD.desconectar();
		
	}
	
	/**
	 * Constructor que devuelve toda la informaci�n de un artista
	 * @param nombre nombre del artista
	 * @throws SQLException
	 * @throws Contrase�aIncorrectaException 
	 */
	public Artista(String nombre) throws SQLException, Contrase�aIncorrectaException { 
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from usuario where nombre = '"+nombre+"'");
		if(consulta.next()) {
			this.setEmail(consulta.getString("email"));
			this.setNombre(consulta.getString("nombre"));
			this.setFoto(consulta.getString("foto"));
			this.setContrase�a(consulta.getString("contrase�a"));
			this.setesPremium(consulta.getBoolean("espremium"));
		}else {
			ConexionBD.desconectar();
			//throw new UsuarioNoExisteException("No existe el mail en la BD");
			throw new SQLException("no se ha podido devolver la informaci�n del usuario");
		}
		ConexionBD.desconectar();
	}
	/**
	 * M�todo que devuelve todos los artistas de la bd
	 * @return lista de todos los usuarios
	 */
	public static ArrayList<Usuario> getTodosArtistas() {
		ArrayList<Usuario> ret= new ArrayList<Usuario>();
		Statement smt= ConexionBD.conectar();
		try {
			ResultSet cursor = smt.executeQuery("select * from artista");
			while(cursor.next()) {//usamos while, porque recorre todos los usuarios de la base.
				//antes solamente ten�amos informaci�n de 1 solo usuario
				Usuario u=new Artista();//necesio un Usuario, porque  el select es de usuario.
				u.email=cursor.getString("email");//no puedo llamar a this, porque ser�a un usuario espec�fico.
				u.setNombre(cursor.getString("nombre"));
				u.setFoto(cursor.getString("foto"));
				u.contrase�a=cursor.getString("contrase�a");
				u.esPremium=cursor.getBoolean("espremium");
			}
		} catch (SQLException e) {
			// Aqu� no deber�a entrar porque la consulta va a ser correcta
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return ret;
	}
	public String mostrarTodosArtistas(){
		String res="";
		for(Usuario artista : getTodosArtistas()) {
			res+=artista+"\n";
		}
		return res;
	}
	
	/**
	 * m�todo que devuelve las discograf�as / albumes de un artista
	 * @return ArrayList de playslist de un usuario
	 * @throws SQLException 
	 * @throws Contrase�aIncorrectaException 
	 */
	public ArrayList<PlayList> getDiscograf�a() throws SQLException {
		ArrayList<PlayList> discografia=new ArrayList<PlayList>();
		Statement smt = ConexionBD.conectar();
			
			ResultSet consulta = smt.executeQuery("select * from playlist where usuario_email = '"+this.getEmail()+"'");
			while(consulta.next()) {
				PlayList listas=new PlayList();
				listas.setFoto(consulta.getString("foto"));
				listas.fechaCreacion=consulta.getTimestamp("fechaincorporacion").toLocalDateTime();
				listas.setNombre(consulta.getString("nombre"));
				try {
					listas.usuario=new Artista(consulta.getString("usuario_email"));
				} catch (SQLException | Contrase�aIncorrectaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				discografia.add(listas);
			}
			ConexionBD.desconectar();

			return discografia;
	}
	
	
	@Override
	public String toString() {
		return "Artista | email=" + this.getEmail()+" -  nombre=" + this.getNombre()+
				"- foto=" + this.getFoto()+"- activo=" + this.esPremium;
	}

	
	
}
