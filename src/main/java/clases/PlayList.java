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
	//tengo que hacerle el dao de cancion y listaCanciones
	protected ArrayList<Cancion> canciones;
	protected Usuario usuario; //usuario que lo crea
	protected LocalDateTime fechaCreacion;
	//en el dao, usuario es el nombre de usuario
	public PlayList() {	}
	
	public PlayList(String nombre, Blob foto, Usuario usuario, ArrayList<Cancion> canciones, 
			 LocalDateTime fechaCreacion) throws SQLException {
		super(nombre, foto);
		this.canciones=new ArrayList<Cancion>();
		ObjetoConNombre objNombre = new ObjetoConNombre();
		
		String ret="";
		for (Cancion cancion : canciones) {//me recorro el arraylist pasado por parámetro
			ret+=cancion+"\n";
		}
		
		Statement smt = ConexionBD.conectar();
		if(smt.executeUpdate(
				"insert into listacanciones values('"+nombre+"','"+foto+"','"+usuario.getEmail()+"','"+fechaCreacion+"', '"+ret+"')"
				)>0) {
		}
		objNombre.setNombre(nombre);//nombre de la lista
		objNombre.setFoto(foto);
		this.usuario=usuario;//el usuario
		this.fechaCreacion=fechaCreacion;
		this.canciones=ret;
		
		ConexionBD.desconectar();
	}

	

	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	

}
