package clases;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import excepciones.CantidadCaracteresIncorrecta;
import excepciones.ContraseñaIncorrectaException;
import excepciones.NombreInvalidoException;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioYaExiste;
import utils.ConexionBD;
/**
 * Representa al artista de una canción
 * @author Juan
 *
 */
public class Artista extends Usuario{
	public Artista() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * Constructor que añade un nuevo artista a la base de datos
	 * @param email email del artista
	 * @param nombre nombre del artista
	 * @param foto foto del artista
	 * @param contraseña contraseña del artista
	 * @param esPremium true si es premium, false en caso contrario
	 * @throws SQLException
	 * @throws NombreInvalidoException
	 * @throws UsuarioYaExiste
	 * @throws CantidadCaracteresIncorrecta 
	 */
	public Artista(String email, String nombre,  String foto, String contraseña, Boolean esPremium) 
			throws SQLException, NombreInvalidoException, ContraseñaIncorrectaException, UsuarioYaExiste, CantidadCaracteresIncorrecta {
		super();
		
		//UN ARTISTA ES UN USUARIO, LUEGO HAY QUE AÑADIR UN USUARIO Y LUEGO EL ARTISTA
		Usuario usuario = new Usuario(email, nombre, foto, contraseña, esPremium);

		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into artista values ('"+usuario.getEmail()+"')"
				)>0) {
			this.email=usuario.getEmail();
		}else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido conectar con la BD");
		}
		ConexionBD.desconectar();
		
	}
	
	/**
	 * Constructor que devuelve toda la información de un artista
	 * @param nombre nombre del artista
	 * @throws SQLException
	 * @throws ContraseñaIncorrectaException 
	 */
	public Artista(String nombre) throws SQLException, ContraseñaIncorrectaException { 
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from usuario where nombre = '"+nombre+"'");
		if(consulta.next()) {
			this.email=consulta.getString("email");
			this.setNombre(consulta.getString("nombre"));
			this.setFoto(consulta.getString("foto"));
			this.contraseña=consulta.getString("contraseña");
			this.esPremium=consulta.getBoolean("espremium");
		}else {
			ConexionBD.desconectar();
			//throw new UsuarioNoExisteException("No existe el mail en la BD");
			throw new SQLException("no se ha podido devolver la información del usuario");
		}
		ConexionBD.desconectar();
	}
	/**
	 * Método que devuelve todos los artistas de la bd
	 * @return lista de todos los usuarios
	 */
	public static ArrayList<Usuario> getTodosArtistas() {
		ArrayList<Usuario> ret= new ArrayList<Usuario>();
		Statement smt= ConexionBD.conectar();
		try {
			ResultSet cursor = smt.executeQuery(" select * from usuario where email in ( select usuario_email from artista) ");
			while(cursor.next()) {//usamos while, porque recorre todos los usuarios de la base.
				//antes solamente teníamos información de 1 solo usuario
				Usuario u=new Artista();//necesio un Usuario, porque  el select es de usuario.
				u.email=cursor.getString("email");//no puedo llamar a this, porque sería un usuario específico.
				u.setNombre(cursor.getString("nombre"));
				u.setFoto(cursor.getString("foto"));
				u.contraseña=cursor.getString("contraseña");
				u.esPremium=cursor.getBoolean("espremium");
			}
		} catch (SQLException e) {
			// Aquí no debería entrar porque la consulta va a ser correcta
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return ret;
	}
	/**
	 * Método que devuelve todos los artistas en formato cadena
	 * @return todos los artistas
	 */
	public String mostrarTodosArtistas(){
		String res="";
		for(Usuario artista : getTodosArtistas()) {
			res+=artista+"\n";
		}
		return res;
	}
	
	/**
	 * método que devuelve las discografías / albumes de un artista
	 * @return ArrayList de playslist de un usuario
	 * @throws SQLException 
	 * @throws ContraseñaIncorrectaException 
	 */
	public ArrayList<PlayList> getDiscografía() throws SQLException {
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
				} catch (SQLException | ContraseñaIncorrectaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				discografia.add(listas);
			}
			ConexionBD.desconectar();

			return discografia;
	}
	/**
	 * función toString, que muestra la información del objeto Artista
	 * @return String con la información del artista en una línea
	 */
	
	@Override
	public String toString() {
		return "Artista | foto=" + this.getFoto()+" -  nombre=" + this.getNombre();//solo me interesa la foto 
	}

	
	
}
