package interfacesGraficas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PantallaInicio extends JPanel{
	private Ventana ventana;
	
	public PantallaInicio(Ventana ventana) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel barraLateral = new JLabel("Hola feo");
		barraLateral.setHorizontalAlignment(SwingConstants.LEFT);
		barraLateral.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		add(barraLateral, BorderLayout.WEST);
		
		JLabel bottom = new JLabel("Bottom");
		bottom.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		bottom.setHorizontalAlignment(SwingConstants.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		JLabel centro = new JLabel("Informaci\u00F3n");
		centro.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		add(centro, BorderLayout.CENTER);
		
	}
}
