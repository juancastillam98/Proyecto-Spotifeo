package interfacesGraficas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Usuario;
import componentesVisuales.BotonNegro;
import excepciones.CantidadCaracteresIncorrecta;
import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.NombreInvalidoException;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioYaExiste;
import funciones.FicheroDatosUsuario;

import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class PantallaRegistro extends JPanel{
	private Ventana ventana;
	private JTextField campoNombre;
	private JPasswordField campoContraseña;
	private JTextField campoEmail;
	
	public PantallaRegistro(Ventana v) {
		v.setSize(550, 700);
		v.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 30, 0, 20, 0, 0, 0, 30, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 25, 0, 25, 0, 25, 0, 25, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Crear una cuenta");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 30));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
		labelNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.anchor = GridBagConstraints.SOUTHWEST;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 3;
		gbc_labelNombre.gridy = 5;
		add(labelNombre, gbc_labelNombre);
		
		campoNombre = new JTextField();
		campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_campoNombre = new GridBagConstraints();
		gbc_campoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNombre.gridwidth = 3;
		gbc_campoNombre.insets = new Insets(0, 0, 5, 5);
		gbc_campoNombre.gridx = 5;
		gbc_campoNombre.gridy = 5;
		add(campoNombre, gbc_campoNombre);
		campoNombre.setColumns(10);
		
		JLabel labelContraseña = new JLabel("Contrase\u00F1a");
		labelContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		labelContraseña.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_labelContraseña = new GridBagConstraints();
		gbc_labelContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_labelContraseña.gridx = 3;
		gbc_labelContraseña.gridy = 7;
		add(labelContraseña, gbc_labelContraseña);
		
		campoContraseña = new JPasswordField();
		GridBagConstraints gbc_campoContraseña = new GridBagConstraints();
		gbc_campoContraseña.gridwidth = 3;
		gbc_campoContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_campoContraseña.fill = GridBagConstraints.BOTH;
		gbc_campoContraseña.gridx = 5;
		gbc_campoContraseña.gridy = 7;
		add(campoContraseña, gbc_campoContraseña);
		
		JLabel labelEmail = new JLabel("Email");
		labelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_labelEmail = new GridBagConstraints();
		gbc_labelEmail.anchor = GridBagConstraints.WEST;
		gbc_labelEmail.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmail.gridx = 3;
		gbc_labelEmail.gridy = 9;
		add(labelEmail, gbc_labelEmail);
		
		campoEmail = new JTextField();
		campoEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_campoEmail = new GridBagConstraints();
		gbc_campoEmail.gridwidth = 3;
		gbc_campoEmail.insets = new Insets(0, 0, 5, 5);
		gbc_campoEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoEmail.gridx = 5;
		gbc_campoEmail.gridy = 9;
		add(campoEmail, gbc_campoEmail);
		campoEmail.setColumns(10);
		
		JLabel Foto = new JLabel("Foto");
		Foto.setHorizontalAlignment(SwingConstants.LEFT);
		Foto.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_Foto = new GridBagConstraints();
		gbc_Foto.anchor = GridBagConstraints.WEST;
		gbc_Foto.fill = GridBagConstraints.VERTICAL;
		gbc_Foto.insets = new Insets(0, 0, 5, 5);
		gbc_Foto.gridx = 3;
		gbc_Foto.gridy = 11;
		add(Foto, gbc_Foto);
		
		JButton botonSeleccionarArchivo = new JButton("Seleccionar Archivo");
		GridBagConstraints gbc_botonSeleccionarArchivo = new GridBagConstraints();
		gbc_botonSeleccionarArchivo.insets = new Insets(0, 0, 5, 5);
		gbc_botonSeleccionarArchivo.gridx = 7;
		gbc_botonSeleccionarArchivo.gridy = 11;
		add(botonSeleccionarArchivo, gbc_botonSeleccionarArchivo);
		
		JLabel labelEsPreimum = new JLabel("Premium");
		labelEsPreimum.setHorizontalAlignment(SwingConstants.LEFT);
		labelEsPreimum.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_labelEsPreimum = new GridBagConstraints();
		gbc_labelEsPreimum.anchor = GridBagConstraints.WEST;
		gbc_labelEsPreimum.insets = new Insets(0, 0, 5, 5);
		gbc_labelEsPreimum.gridx = 3;
		gbc_labelEsPreimum.gridy = 13;
		add(labelEsPreimum, gbc_labelEsPreimum);
		
		final JRadioButton radioSiEsPremium = new JRadioButton("Si");
		radioSiEsPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_radioSiEsPremium = new GridBagConstraints();
		gbc_radioSiEsPremium.insets = new Insets(0, 0, 5, 5);
		gbc_radioSiEsPremium.gridx = 5;
		gbc_radioSiEsPremium.gridy = 13;
		add(radioSiEsPremium, gbc_radioSiEsPremium);
		
		final JRadioButton radioNoEsPremium = new JRadioButton("No");
		radioNoEsPremium.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_radioNoEsPremium = new GridBagConstraints();
		gbc_radioNoEsPremium.insets = new Insets(0, 0, 5, 5);
		gbc_radioNoEsPremium.gridx = 7;
		gbc_radioNoEsPremium.gridy = 13;
		add(radioNoEsPremium, gbc_radioNoEsPremium);
		
		//Hago que solo se pueda seleccionar uno
		ButtonGroup grupoEsPremium = new ButtonGroup();
		grupoEsPremium.add(radioSiEsPremium);
		grupoEsPremium.add(radioNoEsPremium);
		
		JButton botonVolver = new BotonNegro("Volver");
		botonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_botonVolver = new GridBagConstraints();
		gbc_botonVolver.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonVolver.gridwidth = 2;
		gbc_botonVolver.insets = new Insets(0, 0, 5, 5);
		gbc_botonVolver.gridx = 3;
		gbc_botonVolver.gridy = 16;
		add(botonVolver, gbc_botonVolver);
		
		BotonNegro botonRegistrarse = new BotonNegro("Volver");
		botonRegistrarse.setText("Registrarse");
		botonRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_botonRegistrarse = new GridBagConstraints();
		gbc_botonRegistrarse.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonRegistrarse.gridwidth = 2;
		gbc_botonRegistrarse.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrarse.gridx = 6;
		gbc_botonRegistrarse.gridy = 16;
		add(botonRegistrarse, gbc_botonRegistrarse);
		
		
		//si has escogido pop.
		//te vas a una funcionone, que agrupe las canciones cuyo estilo sea pop
		
		//si has escogido rap.
		//te vas a una funcionone, que agrupe las canciones cuyo estilo sea rap
		
		/************ LISTENERS **********/
		ventana=v;
		
		//boton registrarse
		botonRegistrarse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreUsuario=campoNombre.getText();
				String contraseña=new String(campoContraseña.getPassword());
				String email=campoEmail.getText();
				String foto="./fotos/UsuarioHombreDefault.jpg";//ffoto por defecto
				Boolean esPremium=false;
				if(radioSiEsPremium.isSelected()) {
					// string estilo = pop
					esPremium=true; //lamarias al funcion que agrupa las canciones .Funcines.agrupaPorEstilo(pop)
				}else if (radioNoEsPremium.isSelected()) {
					esPremium=true;
				}
				try {
					ventana.usuarioLogueado=new Usuario(email, nombreUsuario, foto,  contraseña, esPremium);
					JOptionPane.showMessageDialog(ventana,"Registrado con exito","Registrado Completado", JOptionPane.PLAIN_MESSAGE);
					FicheroDatosUsuario.añadirDatosFicheros(email);
					ventana.irAPantalla("inicio");
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NombreInvalidoException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ContraseñaIncorrectaException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioYaExiste e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UsuarioIncorrectoException e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (EmailInvalidoException e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (CantidadCaracteresIncorrecta e1) {
					JOptionPane.showMessageDialog(ventana, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		
		//boton volver
				botonVolver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							ventana.irAPantalla("login");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UsuarioIncorrectoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
		
		
	}
}
