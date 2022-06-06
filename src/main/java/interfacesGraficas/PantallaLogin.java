package interfacesGraficas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import clases.Usuario;
import componentesVisuales.BotonNegro;
import excepciones.Contrase�aIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;

public class PantallaLogin extends JPanel{
	private Ventana ventana;
	private JTextField introducirEmail;
	private JPasswordField introducirContrase�a;
	
	public PantallaLogin(Ventana v) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 143, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 15, 5, 0, 0, 10, 0, 10, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel tituloLogin = new JLabel("Inicio de sesion");
		tituloLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 35));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_tituloLogin = new GridBagConstraints();
		gbc_tituloLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloLogin.gridwidth = 5;
		gbc_tituloLogin.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLogin.gridx = 1;
		gbc_tituloLogin.gridy = 2;
		add(tituloLogin, gbc_tituloLogin);
		
		JButton botonIniciarSesion = new BotonNegro("Iniciar Sesion");
		botonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel campoEmail = new JLabel("email");
		campoEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		campoEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_campoEmail = new GridBagConstraints();
		gbc_campoEmail.insets = new Insets(0, 0, 5, 5);
		gbc_campoEmail.gridx = 2;
		gbc_campoEmail.gridy = 5;
		add(campoEmail, gbc_campoEmail);
		
		introducirEmail = new JTextField();
		introducirEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		introducirEmail.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_introducirEmail = new GridBagConstraints();
		gbc_introducirEmail.insets = new Insets(0, 0, 5, 5);
		gbc_introducirEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_introducirEmail.gridx = 4;
		gbc_introducirEmail.gridy = 5;
		add(introducirEmail, gbc_introducirEmail);
		introducirEmail.setColumns(10);
		
		final JLabel campoContrase�a = new JLabel("Contrase\u00F1a");
		campoContrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		campoContrase�a.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_campoContrase�a = new GridBagConstraints();
		gbc_campoContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_campoContrase�a.gridx = 2;
		gbc_campoContrase�a.gridy = 7;
		add(campoContrase�a, gbc_campoContrase�a);
		
		introducirContrase�a = new JPasswordField();
		introducirContrase�a.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_introducirContrase�a = new GridBagConstraints();
		gbc_introducirContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_introducirContrase�a.fill = GridBagConstraints.HORIZONTAL;
		gbc_introducirContrase�a.gridx = 4;
		gbc_introducirContrase�a.gridy = 7;
		add(introducirContrase�a, gbc_introducirContrase�a);
		botonIniciarSesion.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_botonIniciarSesion = new GridBagConstraints();
		gbc_botonIniciarSesion.gridwidth = 3;
		gbc_botonIniciarSesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonIniciarSesion.insets = new Insets(0, 0, 5, 5);
		gbc_botonIniciarSesion.gridx = 2;
		gbc_botonIniciarSesion.gridy = 9;
		add(botonIniciarSesion, gbc_botonIniciarSesion);
		
		JButton botonRegistrarse = new BotonNegro("Crear una cuenta");
		botonRegistrarse.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_botonRegistrarse = new GridBagConstraints();
		gbc_botonRegistrarse.gridwidth = 3;
		gbc_botonRegistrarse.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrarse.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrarse.gridx = 2;
		gbc_botonRegistrarse.gridy = 11;
		add(botonRegistrarse, gbc_botonRegistrarse);
		// TODO Auto-generated constructor stub
		
		ventana=v;
		/**************LISTENERS*****************/
		//Click en iniciar sesion
		botonIniciarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email=introducirEmail.getText();
				String contrase�a = new String(introducirContrase�a.getPassword());
				
				try {
					ventana.usuarioLogueado=new Usuario(email, contrase�a);//usuarioLogueado es un atributo de ventana
					JOptionPane.showMessageDialog(ventana, "Bienvenid@"+ventana.usuarioLogueado.getNombre()
					,"Inicio de sesion correcto",
					JOptionPane.INFORMATION_MESSAGE);
					ventana.irAPantalla("inicio");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Contrase�aIncorrectaException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioIncorrectoException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				} catch (EmailInvalidoException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		//Click en registrarse
		botonRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ventana.irAPantalla("registro");
			}
		});
		
		
		
	}

}
