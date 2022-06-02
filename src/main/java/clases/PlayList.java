package clases;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.print.attribute.standard.DateTimeAtCompleted;

import utils.ConexionBD;

public class PlayList extends ObjetoConNombre{

	protected ArrayList<Almacenacanciones> almacenacanciones;
	protected Usuario usuario; //usuario que lo crea
	protected LocalDateTime fechaCreacion;
	//en el dao, usuario es el nombre de usuario
	public PlayList() {	}
	
	public PlayList(Blob foto, String nombre, Usuario usuario, LocalDateTime fechaCreacion) throws SQLException {
		super(nombre, foto);		
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into playlist values('"+nombre+"','"+foto+"','"+usuario.getEmail()+"','"+fechaCreacion+"')"
				)>0) {
		}
		this.setNombre(nombre);//nombre de la lista
		this.setFoto(foto);
		this.usuario=usuario;//el usuario
		this.fechaCreacion=fechaCreacion;
		
		ConexionBD.desconectar();
	}

	public ArrayList<Almacenacanciones> getalmacenacanciones() {
		return almacenacanciones;
	}

	public void setalmacenacanciones(ArrayList<Almacenacanciones> almacenacanciones) {
		this.almacenacanciones = almacenacanciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	
	
	

}
