package clases;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Artista extends Usuario{
	private ArrayList<ListaCanciones> discografía;

	public Artista(String nombre, BufferedImage foto, String email, Boolean esPrimium, String contraseña, ArrayList<ListaCanciones> discografía) {
		super(nombre, foto, email, esPrimium, contraseña);
		this.discografía = new ArrayList<ListaCanciones>();
	}

	public ArrayList<ListaCanciones> getDiscografía() {
		return discografía;
	}

	public void setDiscografía(ArrayList<ListaCanciones> discografía) {
		this.discografía = discografía;
	}
	
	
}
