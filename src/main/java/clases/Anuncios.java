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

/**
 * Representa un objeto que mostrará una serie de anuncios a aquellos usuarios que no son premium
 * @author Juan Castilla Mariscal
 *
 */
public class Anuncios extends ObjetoConSonido{
	/**
	 * Url de destino del anuncio
	 */
	private String direccionAnuncio;
	/**
	 * Constructor de Anuncios con todos sus campos
	 * @param nombre nombre del anuncio
	 * @param foto foto representativa del anuncio
	 * @param direccion url de destino del anuncio 
	 */
	public Anuncios(Cancion nombre, String foto, String direccion) {
		super(nombre,foto);
		this.direccionAnuncio = direccionAnuncio;
	}
	/**
	 * Getter de dirección
	 * @return url de destino del anuncio<
	 */
	public String getDireccionAnuncio() {
		return direccionAnuncio;
	}
/**
 * Setter de dirección.
 * @param direccionAnuncio nueva url de destino del anuncio
 */
	public void setDireccionAnuncio(String direccionAnuncio) {
		this.direccionAnuncio = direccionAnuncio;
	}
	
	
	
}
