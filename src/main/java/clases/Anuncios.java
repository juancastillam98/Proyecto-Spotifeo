package clases;
//hola que rojorrr
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;

public class Anuncios extends ObjetoConSonido{
	private String direccionAnuncio;
	
	public Anuncios(String nombre, BufferedImage foto, Player reproducirCancion, String direccionAnuncio) {
		super(nombre,foto, reproducirCancion);
		this.direccionAnuncio = direccionAnuncio;
	}
	public String getDireccionAnuncio() {
		return direccionAnuncio;
	}

	public void setDireccionAnuncio(String direccionAnuncio) {
		this.direccionAnuncio = direccionAnuncio;
	}
	
	
	
}
