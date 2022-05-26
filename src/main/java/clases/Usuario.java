package clases;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Usuario extends ObjetoConNombre{
	private String email;
	private Boolean esPrimium;
	private String contraseña;

	
	public Usuario(String nombre, BufferedImage foto, String email, Boolean esPrimium, String contraseña) {
		super(nombre, foto);
		this.email = email;
		this.esPrimium = esPrimium;
		this.contraseña = contraseña;
	}
	public ArrayList<ListaCanciones> getBiblioteca() {//método que devuelve un arraylist de arraylist
		 ArrayList<ListaCanciones> listaCanciones=new ArrayList<ListaCanciones>();
		 //Aqui lo que hay que hacer es un select de listaCanciones where usuario = this.email
		 return listaCanciones;
	}
	
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Boolean getEsPrimium() {
		return esPrimium;
	}
	public void setEsPrimium(Boolean esPrimium) {
		this.esPrimium = esPrimium;
	}
	
}
