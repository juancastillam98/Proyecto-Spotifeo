package interfacesGraficas;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import clases.Usuario;

public class Ventana extends JFrame{
	
	protected Usuario usuarioLogueado;
	private JPanel pantallaActual;
	private String[] args;
	public Ventana(String[] args) {
		this.setSize(600, 500);
		this.setTitle("Spotifeo");
		this.setIconImage(new ImageIcon("./fotos/icono.png").getImage());
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		this.setAlwaysOnTop(true); //pone la panalla siempre por delante
		this.setLocationRelativeTo(null);//centra una ventana, despu�s del setSize
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//cierra cuando pulsas x
		this.setResizable(false);
		//pantalla completa
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setArgs(args);
		this.pantallaActual= new PantallaLogin(this, this.getArgs());
		this.setContentPane(this.pantallaActual);//busca la pantalla login y la pone
		this.setVisible(true);
	}
	public String[] getArgs() {
		return args;
	}
	private void setArgs(String[] args) {
		this.args=args;
		
	}
	/** ejemplo de pantalla con hashMap
	public void irAPantalla(String nombrePantalla) {
		Iterator it=this.pantalla.values().iterator();//recorremos las pantallas (Recuerda, las pantallas son JPanel
		while (it.hasNext()) {//Recuerda; los valores de la pantalla son JPanel
			JPanel actual = (JPanel)it.next();
			actual.setVisible(false);//por defecto, todas las pantallas las pondr� no visible
		}
		this.pantalla.get(nombrePantalla).setVisible(true);//la pantalla que queremos mostrar, la ponemos visible
		this.setContentPane(this.pantalla.get(nombrePantalla));
	}
	*/
	
	
	public void irAPantalla(String nombrePantalla) {
		this.pantallaActual.setVisible(false);
		this.pantallaActual=null;//cada vez que cambie de pantalla, la pongo en null
		switch (nombrePantalla) {//en funci�n del nombre que escriba, me redirigir� a una p�gina u otra
			case "login":
				this.pantallaActual=new PantallaLogin(this, this.getArgs());
				break;
			case "inicio":
				this.pantallaActual=new PantallaInicio(this);
				break;
			case "registro":
				this.pantallaActual=new PantallaRegistro(this);
				break;
			default:
				break;
		}
		this.pantallaActual.setVisible(true);
		this.setContentPane(pantallaActual);
	}

}
