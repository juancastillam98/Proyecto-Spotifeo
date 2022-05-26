package clases;
//hola que rojorrr
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line.Info;

public class Anuncios extends ObjetoConSonido{
	private String direccionAnuncio;
	private Clip reproducirCancion;//preguntar a miguel, como inicializar una variable de tipo clip
	public Anuncios(String nombre, BufferedImage foto, Clip reproducirCancion, String direccionAnuncio) {
		super(nombre, foto, reproducirCancion);
		this.direccionAnuncio = direccionAnuncio;
		this.reproducirCancion=new Clip() {
			
			public void removeLineListener(LineListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			public void open() throws LineUnavailableException {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isOpen() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isControlSupported(Type control) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Info getLineInfo() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Control[] getControls() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Control getControl(Type control) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public void close() {
				// TODO Auto-generated method stub
				
			}
			
			public void addLineListener(LineListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			public void stop() {
				// TODO Auto-generated method stub
				
			}
			
			public void start() {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isRunning() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isActive() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public long getMicrosecondPosition() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public long getLongFramePosition() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public float getLevel() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int getFramePosition() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public AudioFormat getFormat() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public void flush() {
				// TODO Auto-generated method stub
				
			}
			
			public void drain() {
				// TODO Auto-generated method stub
				
			}
			
			public int available() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public void setMicrosecondPosition(long microseconds) {
				// TODO Auto-generated method stub
				
			}
			
			public void setLoopPoints(int start, int end) {
				// TODO Auto-generated method stub
				
			}
			
			public void setFramePosition(int frames) {
				// TODO Auto-generated method stub
				
			}
			
			public void open(AudioFormat format, byte[] data, int offset, int bufferSize) throws LineUnavailableException {
				// TODO Auto-generated method stub
				
			}
			
			public void open(AudioInputStream stream) throws LineUnavailableException, IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void loop(int count) {
				// TODO Auto-generated method stub
				
			}
			
			public long getMicrosecondLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int getFrameLength() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	public String getDireccionAnuncio() {
		return direccionAnuncio;
	}

	public void setDireccionAnuncio(String direccionAnuncio) {
		this.direccionAnuncio = direccionAnuncio;
	}
	
	
	
}
