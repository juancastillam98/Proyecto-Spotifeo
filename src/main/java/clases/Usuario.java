package clases;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import excepciones.CantidadCaracteresIncorrecta;
import excepciones.Contrase�aIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.NombreInvalidoException;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioYaExiste;
import utils.ConexionBD;
/**
 * Hace referencia a un usuario logueado.
 * @author Juan Castilla
 *
 */
public class Usuario extends ObjetoConNombre {
	/**
	 * Indica el email del artista
	 */
	protected String email;
	/**
	 * Indica si es premium o no el usuario
	 */
	protected Boolean esPremium;
	/**
	 * Referencia a la contrase�a del usuario
	 */
	protected String contrase�a;
	
	/**
	 * Constructor vac�o
	 */
	public Usuario() {// constructor para recorrer valores de del
		super();
	}

	/**
	 * Constructor que inserta datos del usuario en la base de datos
	 * 
	 * @param nombre     del usuario
	 * @param fotoArray  foto de perfil del usuario
	 * @param email      email del usuario
	 * @param esPremium  true si es usuario Premium, false en caso contrario
	 * @param contrase�a password del usuario
	 * @throws SQLException
	 * @throws NombreInvalidoException 
	 * @throws UsuarioYaExiste 
	 * @throws CantidadCaracteresIncorrecta 
	 */
	public Usuario(String email, String nombre, String fotoArray,  String contrase�a, Boolean esPremium)
			throws SQLException, NombreInvalidoException, Contrase�aIncorrectaException, UsuarioYaExiste, CantidadCaracteresIncorrecta {
		super(); 
		
		if(!nombreValido(nombre)) {//si el nombre est� vac�o, devuelve true (ha negado 2 veces, es lo mismo que no negar ninguna vez
			throw new NombreInvalidoException("El nombre est� vac�o");
		}
		if (cantidadCaracteresIncorrecta(contrase�a)) {
			throw new CantidadCaracteresIncorrecta("La contrase�a debe tener al menos 4 caracteres");
		}
		
		Statement cnx = ConexionBD.conectar();
		
		//Compruebo si el usuario ya existe en la bd
		ResultSet consulta = cnx.executeQuery("select * from usuario where email='"+email+"'");
		if(consulta.next()) {//si esto devuelve un true
			this.email=consulta.getString("email");//almaceno la contrase�a DE LA DB
			if(this.email.equals(email)) {//si la email que hay en la bd es el mismo que el introducido
				ConexionBD.desconectar();
				throw new UsuarioYaExiste("Ya existe el usuario "+email);
			}
		}
		
		if (cnx.executeUpdate("insert into usuario values ('"+email+"','"+nombre+"','"+fotoArray.replace((char) 92, '/')+"','"
				+contrase�a+"', "+esPremium+")"
				) > 0) {
			this.email = email;
			this.setNombre(nombre);
			this.setFoto(fotoArray);
			this.contrase�a = contrase�a;
			this.esPremium = esPremium;
		} else {
			ConexionBD.desconectar();
			throw new SQLException("No se ha podido insertar en la bd");
		}
		
		ConexionBD.desconectar();
	}

	/**
	 * Constructor que que nos devulve informaci�n del usuario de la bd
	 * 
	 * @param email email del usuario
	 * @param contrase�a password del usuario
	 * @throws SQLException
	 * @throws Contrase�aIncorrectaException 
	 * @throws UsuarioIncorrectoException 
	 * @throws EmailInvalidoException 
	 */
	public Usuario(String email, String contrase�a) throws SQLException, Contrase�aIncorrectaException, UsuarioIncorrectoException, EmailInvalidoException {
		super();
		//LOGIN
		Statement cnx = ConexionBD.conectar();
		ResultSet consulta = cnx.executeQuery("select * from usuario where email='" + email + "'");
		if (consulta.next()) {
			this.contrase�a=consulta.getString("contrase�a");
			if(!this.contrase�a.equals(contrase�a)) {//si la contrase�a que hay en la bd, no es la misma que la introducida
				ConexionBD.desconectar();
				throw new Contrase�aIncorrectaException("contrase�a incorrecta");
			}
			
			this.email = consulta.getString("email");
			if(!this.email.equals(email)) {//si el email de la bd no es el mismo
				ConexionBD.desconectar();
				throw new EmailInvalidoException("email incorrecto");
			}
			
			this.setNombre(consulta.getString("nombre"));
			this.setFoto(consulta.getString("foto"));
			this.esPremium = consulta.getBoolean("esPremium");
		} else {
			ConexionBD.desconectar();
			throw new UsuarioIncorrectoException("No existe el email "+email);
		}
		ConexionBD.desconectar();
	}
	/**
	 * Constructor que devuelve toda la informaci�n de un usuario concreto de la base de datos
	 * @throws UsuarioIncorrectoException 
	 */
	public Usuario(String email) throws SQLException, UsuarioIncorrectoException {
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from usuario where email = '"+email+"'");
		if(consulta.next()) {
			this.email=consulta.getString("email");
			this.setNombre(consulta.getString("nombre"));
			this.esPremium=consulta.getBoolean("espremium");
			this.contrase�a=consulta.getString("contrase�a");
		}else {
			ConexionBD.desconectar();
			throw new UsuarioIncorrectoException("No existe el email "+email);
		}
		ConexionBD.desconectar();
		
	}
	
	/**
	 * M�todo que devuelve todos los usuarios de la base de datos
	 * @return lista de todos los usuarios
	 */
	public static ArrayList<Usuario> getTodosUsuarios() {
		ArrayList<Usuario> ret= new ArrayList<Usuario>();
		Statement smt= ConexionBD.conectar();
		try {
			ResultSet cursor = smt.executeQuery("select * from usuario");
			while(cursor.next()) {//usamos while, porque recorre todos los usuarios de la base.
				//antes solamente ten�amos informaci�n de 1 solo usuario
				Usuario u=new Usuario();//necesio un Usuario, porque  el select es de usuario.
				u.email=cursor.getString("email");//no puedo llamar a this, porque ser�a un usuario espec�fico.
				u.setNombre(cursor.getString("nombre"));
				u.setFoto(cursor.getString("foto"));
				u.contrase�a=cursor.getString("contrase�a");
				u.esPremium=cursor.getBoolean("espremium");
				ret.add(u);			
			}
		} catch (SQLException e) {
			// Aqu� no deber�a entrar porque la consulta va a ser correcta
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return ret;
	}
	/**
	 * M�todo que devuelve todos usuarios
	 * @return String con toda la informaci�n de los usuarios
	 */
	public String mostrarTodosUsuarios(){
		String res="";
		for(Usuario usuario : getTodosUsuarios()) {
			res+=usuario+"\n";
		}
		return res;
	}

	/**
	 * m�todo que devuelve las playlist de un usuario
	 * @return ArrayList de playslist de un usuario
	 * @throws SQLException 
	 */
	public ArrayList<PlayList> getBiblioteca() throws SQLException {//m�todo que devuelve un arraylist de arraylist
		 ArrayList<PlayList> biblioteca=new ArrayList<PlayList>();
		 //Aqui lo que hay que hacer es un select de playslist where usuario = this.email
		Statement smt = ConexionBD.conectar();
		try {	
			ResultSet consulta = smt.executeQuery("select * from playlist where usuario_email = '"+this.email+"'");
			while(consulta.next()) {
				PlayList listas=new PlayList();
				listas.setFoto(consulta.getString("foto"));
				listas.setNombre(consulta.getString("nombre"));
				this.email= consulta.getString("usuario_email");
				listas.setFechaCreacion(consulta.getTimestamp("fechaincorporacion").toLocalDateTime());
				biblioteca.add(listas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConexionBD.desconectar();
		return biblioteca;
	}
	/**
	 * M�todo que devuelve todas las las playlist del usuarios
	 * @return String con todas las playlist del usuario
	 * @throws SQLException
	 */
	public String mostrarPlaylist() throws SQLException{
		String res="";
		for(PlayList playlist : getBiblioteca()) {
			res+=playlist;
		}
		return res;
	}
	
	
	
	
	//FUNCIONES PARA PROTEGER LOS CAMPOS
	/**
	 * Funci�n que comprueba si el nombre de usuario est� vac�o.
	 * @param nombre del usuario usuario
	 * @return true si el campo est� vac�o, f
	 */
	private static boolean nombreValido(String nombre) {
		return !nombre.isBlank();// si el nombre no est� en blanco, devuelve true.
		
	}
	/**
	 * Funci�n que comprueba la cantidad de caracteres del campo contrase�a
	 * @param pass contrase�a introducida
	 * @return true, si la contrase�a tiene menos de 3 caracteres, false en caso contrario
	 */
	public static boolean cantidadCaracteresIncorrecta(String pass) {
		if(pass.length()<=3) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//GETTERS Y SETTERS
	/**
	 * Getter de email
	 * @return email del usuario
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter de email,
	 * @param email nuevo email del usuario
	 * @throws SQLException
	 */
	public void setEmail(String email) throws SQLException {
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set email ='"+email+"' where email = '"+this.email+"'"
				)>0) {
			this.email=email;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el email");
		}
		ConexionBD.desconectar();
	}
	/**
	 * Getter de contrase�a
	 * @return contrase�a del usuario
	 */
	public String getContrase�a() {
		return contrase�a;
	}
	/**
	 * M�todo para actualizar la contrase�a del usuario de la base de datos spotifeo
	 * @param contrase�a nueva contrase� del usuario
	 * @throws SQLException
	 * @throws Contrase�aIncorrectaException
	 */
	public void setContrase�a(String contrase�a) throws SQLException, Contrase�aIncorrectaException {
		if (cantidadCaracteresIncorrecta(contrase�a)) {
			throw new Contrase�aIncorrectaException("La contrase�a debe tener al menos 4 caracteres");
		}
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set contrase�a ='"+contrase�a+"' where email = '"+this.email+"'"
				)>0) {
			this.contrase�a=contrase�a;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el la contrase�a");
		}
		ConexionBD.desconectar();
		
	}
	/**
	 * Getter de esPremium
	 * @return true si el usuario es premium, false en caso contrario.
	 */
	public Boolean getesPremium() {
		return esPremium;
	}
	/**
	 *M�todo que actuliza el estado de es premium en la base de datos spotifeo
	 * @param esPremium Nuevo estado de Es Premium, true o false
	 * @throws SQLException
	 */
	public void setesPremium(Boolean esPremium) throws SQLException {
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set espremium ="+esPremium+" where email = '"+this.email+"'"
				)>0) {
			this.esPremium=esPremium;
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el estado de Premium");
		}
		ConexionBD.desconectar();
		
	}
	/**
	 * Metodo para cambiar el nombre de usuario
	 * @param nombre Nuevo nombre del usuario
	 * @throws SQLException
	 */
	public void cambiaNombre(String nombre) throws SQLException {
		Statement smt= ConexionBD.conectar();

		if(smt.executeUpdate(
				"update usuario set nombre = '"+nombre+"' where email='"+this.email+"'"
				)>0) {
			this.setNombre(nombre);
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el nombre");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * Metodo para cambiar la foto de usuario
	 * @param nombre Nuevo nombre del usuario
	 * @throws SQLException
	 */
	public void cambiaFoto(String foto) throws SQLException {
		Statement smt= ConexionBD.conectar();
		if(smt.executeUpdate(
				"update usuario set foto = '"+foto.replace((char) 92, '/')+"' where email='"+this.email+"'"
				)>0) {
			this.setFoto(foto);
		}else {
			ConexionBD.desconectar();
			throw new SQLException("no se ha podido cambiar el nombre");
		}
		ConexionBD.desconectar();
	}
	
	/**
	 * funci�n toString, que muestra la informaci�n del objeto Usuario
	 * @return String con la informaci�n del objeto Usuario en una sola l�nea
	 */
	@Override
	public String toString() {
		return "Usuario | email=" + this.getEmail()+" -  nombre=" + this.getNombre()+
				"- foto=" + this.getFoto()+"- activo=" + this.esPremium;
	}

	

}
