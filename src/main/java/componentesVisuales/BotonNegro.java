package componentesVisuales;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;
/**
 * Representa todos los botones que son negros, con texto en blanco. Y al pasar por encima de ellos,
 * pasan a ser naranjas con el texto negro
 * @author Juan Castilla
 *
 */
public class BotonNegro extends JButton{
	public BotonNegro(String s) {
		super(s);
		estilosPorDefecto();//est� por defecto
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {//dentro de los eventos, no podemos usar el this.
				setForeground(new Color(0, 0, 0));
				setBackground(new Color(245, 146, 5));//color naranja
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {//dentro de los eventos, no podemos usar el this.
				estilosPorDefecto();
			}
		});
	}
	/**
	 * m�todo que cambia el color del bot�n al pasar por encima de �l
	 */
	private void estilosPorDefecto() {//esta funci�n, volver� a poner el boton por defecto
		//por ejemplo, si pongo el cursor encima de un bot�n, y este bot�n cambia, cuando salga
		//del bot�n, tiene que haber una funci�n, que cambie vuela a poner el bot�n tal y como estaba
		this.setToolTipText("Pincha aqu\u00ED para iniciar sesi\u00F3n");
		this.setForeground(new Color(255, 255, 255));
		this.setFocusable(false);
		this.setBackground(new Color(0, 0, 0));
	}

}