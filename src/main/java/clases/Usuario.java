package clases;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Usuario extends ObjetoConNombre{
	private String email;
	private Boolean esPrimium;
	private String contrase�a;

	
	public Usuario(String nombre, BufferedImage foto, String email, Boolean esPrimium, String contrase�a) {
		super(nombre, foto);
		this.email = email;
		this.esPrimium = esPrimium;
		this.contrase�a = contrase�a;
	}
	public ArrayList<ListaCanciones> getBiblioteca() {//m�todo que devuelve un arraylist de arraylist
		 ArrayList<ListaCanciones> listaCanciones=new ArrayList<ListaCanciones>();
		 //Aqui lo que hay que hacer es un select de listaCanciones where usuario = this.email
		 return listaCanciones;
	}
	
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public Boolean getEsPrimium() {
		return esPrimium;
	}
	public void setEsPrimium(Boolean esPrimium) {
		this.esPrimium = esPrimium;
	}
	
}
