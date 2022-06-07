package interfacesGraficas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import clases.Usuario;
import componentesVisuales.BotonNegro;
import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;
import funciones.FicheroDatosUsuario;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class PantallaLogin extends JPanel{
	private Ventana ventana;
	private JTextField introducirEmail;
	private JPasswordField introducirContraseña;
	
	public PantallaLogin(Ventana v, final String[] args) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 143, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 15, 5, 0, 0, 10, 0, 10, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setIcon(new ImageIcon(PantallaLogin.class.getResource("/imagenes/icono.png")));
		GridBagConstraints gbc_labelLogo = new GridBagConstraints();
		gbc_labelLogo.insets = new Insets(0, 0, 5, 5);
		gbc_labelLogo.gridx = 1;
		gbc_labelLogo.gridy = 2;
		add(labelLogo, gbc_labelLogo);
		
		JLabel tituloLogin = new JLabel("Inicio de sesion");
		tituloLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 35));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_tituloLogin = new GridBagConstraints();
		gbc_tituloLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloLogin.gridwidth = 4;
		gbc_tituloLogin.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLogin.gridx = 2;
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
		gbc_campoEmail.gridx = 1;
		gbc_campoEmail.gridy = 5;
		add(campoEmail, gbc_campoEmail);
		
		introducirEmail = new JTextField();
		introducirEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		introducirEmail.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_introducirEmail = new GridBagConstraints();
		gbc_introducirEmail.gridwidth = 2;
		gbc_introducirEmail.insets = new Insets(0, 0, 5, 5);
		gbc_introducirEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_introducirEmail.gridx = 3;
		gbc_introducirEmail.gridy = 5;
		add(introducirEmail, gbc_introducirEmail);
		introducirEmail.setColumns(10);
		
		final JLabel campoContraseña = new JLabel("Contrase\u00F1a");
		campoContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		campoContraseña.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_campoContraseña = new GridBagConstraints();
		gbc_campoContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_campoContraseña.gridx = 1;
		gbc_campoContraseña.gridy = 7;
		add(campoContraseña, gbc_campoContraseña);
		
		introducirContraseña = new JPasswordField();
		introducirContraseña.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_introducirContraseña = new GridBagConstraints();
		gbc_introducirContraseña.gridwidth = 2;
		gbc_introducirContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_introducirContraseña.fill = GridBagConstraints.HORIZONTAL;
		gbc_introducirContraseña.gridx = 3;
		gbc_introducirContraseña.gridy = 7;
		add(introducirContraseña, gbc_introducirContraseña);
		botonIniciarSesion.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_botonIniciarSesion = new GridBagConstraints();
		gbc_botonIniciarSesion.gridwidth = 2;
		gbc_botonIniciarSesion.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonIniciarSesion.insets = new Insets(0, 0, 5, 5);
		gbc_botonIniciarSesion.gridx = 3;
		gbc_botonIniciarSesion.gridy = 9;
		add(botonIniciarSesion, gbc_botonIniciarSesion);
		
		JButton botonRegistrarse = new BotonNegro("Crear una cuenta");
		botonRegistrarse.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_botonRegistrarse = new GridBagConstraints();
		gbc_botonRegistrarse.gridwidth = 2;
		gbc_botonRegistrarse.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrarse.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrarse.gridx = 3;
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
				String contraseña = new String(introducirContraseña.getPassword());
				
				if (email.length() <= 0 || contraseña.length() <= 0) {
					email = args[1];
					contraseña = args[3];
				}
				try {
					ventana.usuarioLogueado=new Usuario(email, contraseña);//usuarioLogueado es un atributo de ventana
					JOptionPane.showMessageDialog(ventana, "Bienvenid@"+ventana.usuarioLogueado.getNombre()
					,"Inicio de sesion correcto",
					JOptionPane.INFORMATION_MESSAGE);
					File carpeta=null;
					FicheroDatosUsuario.obtenerDatosUsuario(email, contraseña);
					ventana.irAPantalla("inicio");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ContraseñaIncorrectaException e1) {
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
