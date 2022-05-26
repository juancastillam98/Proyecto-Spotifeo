package clases;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Artista extends Usuario{
	private ArrayList<ListaCanciones> discograf�a;

	public Artista(String nombre, BufferedImage foto, String email, Boolean esPrimium, String contrase�a, ArrayList<ListaCanciones> discograf�a) {
		super(nombre, foto, email, esPrimium, contrase�a);
		this.discograf�a = new ArrayList<ListaCanciones>();
	}

	public ArrayList<ListaCanciones> getDiscograf�a() {
		return discograf�a;
	}

	public void setDiscograf�a(ArrayList<ListaCanciones> discograf�a) {
		this.discograf�a = discograf�a;
	}
	
	
}
