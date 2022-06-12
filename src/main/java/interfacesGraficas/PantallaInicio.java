package interfacesGraficas;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

import clases.Cancion;
import clases.PlayList;
import clases.Usuario;
import componentesVisuales.BotonNegro;
import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;
import funciones.FicheroDatosUsuario;
import funciones.ReproducirCanciones;
import hilos.MusicaReproducir;
import interfaces.Reproductor;
import utils.ConexionBD;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.SystemColor;

import javax.lang.model.element.Element;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * Pantalla de menú principal de la aplicación.
 * @param ventana
 */
public class PantallaInicio extends JPanel{
	/**
	 * Indica la ventan sobre la que nos encotramos
	 */
	private Ventana ventana;
	private Cancion cancionActual;
	private DefaultListModel cancionesModelo;
	private JTextField textFieldBuscarCanciones;
	
	public PantallaInicio(final Ventana ventana) {
		Reproductor reproductor;//declaro una referencia a la interface Reproductor
		
		
		PlayList playListUsrLogueado = new PlayList();
		Usuario usrLogueado=null;
		try {
			usrLogueado = new Usuario(ventana.usuarioLogueado.getEmail());//el usuario lo recupero de ventana
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (UsuarioIncorrectoException e2) {
			e2.printStackTrace();
		}
		
		Queue<String> datos=null;
		try {
			datos = FicheroDatosUsuario.obtenerDatosUsuario(ventana.usuarioLogueado.getEmail());
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (UsuarioIncorrectoException e1) {
			e1.printStackTrace();
		} catch (EmailInvalidoException e1) {
			e1.printStackTrace();
		} catch (ContraseñaIncorrectaException e1) {
			e1.printStackTrace();
		}
		String[] datosUsuario = new String[4];//0 email, 1 nombre, 2 foto, 3 es premium
		byte pos=0;
		while(!datos.isEmpty()) {
			String actual = datos.poll();
			datosUsuario[pos]=actual;
			pos++;
		}

		setLayout(new BorderLayout(0, 0));
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panelIzquierdo = new JPanel();
		add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panelInformacionUsr = new JPanel();
		panelIzquierdo.add(panelInformacionUsr, BorderLayout.NORTH);
		GridBagLayout gbl_panelInformacionUsr = new GridBagLayout();
		gbl_panelInformacionUsr.columnWidths = new int[]{0, 0, 0, 180, 0, 0, 0};
		gbl_panelInformacionUsr.rowHeights = new int[]{0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInformacionUsr.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelInformacionUsr.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInformacionUsr.setLayout(gbl_panelInformacionUsr);
		
		JSeparator separatorVerticalUsr = new JSeparator();
		GridBagConstraints gbc_separatorVerticalUsr = new GridBagConstraints();
		gbc_separatorVerticalUsr.gridheight = 12;
		gbc_separatorVerticalUsr.insets = new Insets(0, 15, 0, 5);
		gbc_separatorVerticalUsr.gridx = 2;
		gbc_separatorVerticalUsr.gridy = 0;
		panelInformacionUsr.add(separatorVerticalUsr, gbc_separatorVerticalUsr);
		
		JSeparator separatorInformacionUsr = new JSeparator();
		GridBagConstraints gbc_separatorInformacionUsr = new GridBagConstraints();
		gbc_separatorInformacionUsr.insets = new Insets(30, 0, 5, 5);
		gbc_separatorInformacionUsr.gridx = 3;
		gbc_separatorInformacionUsr.gridy = 2;
		panelInformacionUsr.add(separatorInformacionUsr, gbc_separatorInformacionUsr);
		
		JLabel labelInformacionUsr = new JLabel("Informaci\u00F3n del Usuario");
		labelInformacionUsr.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		labelInformacionUsr.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelInformacionUsr = new GridBagConstraints();
		gbc_labelInformacionUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelInformacionUsr.gridx = 3;
		gbc_labelInformacionUsr.gridy = 6;
		panelInformacionUsr.add(labelInformacionUsr, gbc_labelInformacionUsr);
		
		
		JLabel labelFotoUsr = new JLabel();
		labelFotoUsr.setIcon(new ImageIcon(datosUsuario[2].toString().replace("\n", "")));//no lo coge
		labelFotoUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelFotoUsr = new GridBagConstraints();
		gbc_labelFotoUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelFotoUsr.gridx = 3;
		gbc_labelFotoUsr.gridy = 8;
		panelInformacionUsr.add(labelFotoUsr, gbc_labelFotoUsr);
		
		JLabel labelEmailUsr = new JLabel("Email: "+datosUsuario[0]);
		labelEmailUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelEmailUsr = new GridBagConstraints();
		gbc_labelEmailUsr.anchor = GridBagConstraints.WEST;
		gbc_labelEmailUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmailUsr.gridx = 3;
		gbc_labelEmailUsr.gridy = 9;
		panelInformacionUsr.add(labelEmailUsr, gbc_labelEmailUsr);
		
		JLabel labelNombreUsr = new JLabel("Usuario: "+datosUsuario[1]);
		labelNombreUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelNombreUsr = new GridBagConstraints();
		gbc_labelNombreUsr.anchor = GridBagConstraints.WEST;
		gbc_labelNombreUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreUsr.gridx = 3;
		gbc_labelNombreUsr.gridy = 10;
		panelInformacionUsr.add(labelNombreUsr, gbc_labelNombreUsr);
		
		JLabel labelEsPremium = new JLabel("Es Premium: "+datosUsuario[3]);
		labelEsPremium.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelEsPremium = new GridBagConstraints();
		gbc_labelEsPremium.anchor = GridBagConstraints.WEST;
		gbc_labelEsPremium.insets = new Insets(0, 0, 0, 5);
		gbc_labelEsPremium.gridx = 3;
		gbc_labelEsPremium.gridy = 11;
		panelInformacionUsr.add(labelEsPremium, gbc_labelEsPremium);
		
		final JPanel panelPlaylist = new JPanel();
		panelPlaylist.setSize(200, 300);
		panelPlaylist.setBounds(20, 20, 100, 300);
		FlowLayout flowLayout = (FlowLayout) panelPlaylist.getLayout();
		flowLayout.setHgap(15);
		panelPlaylist.setBackground(SystemColor.controlHighlight);
		panelIzquierdo.add(panelPlaylist, BorderLayout.CENTER);
		

		
		/**
		 * Mostrar las playlist. Solamente quiero saber el nombre
		 */
		final JList listaPlaylist = new JList();
		listaPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		final DefaultListModel modeloListaPlaylist = new DefaultListModel();
		listaPlaylist.setModel(modeloListaPlaylist);
		
		try {
			ArrayList<PlayList> playlistUsuario = ventana.usuarioLogueado.getBiblioteca();
			for (PlayList playList : playlistUsuario) {
				modeloListaPlaylist.addElement(playList.getNombre());

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panelPlaylist.add(listaPlaylist);

		
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(panelSur, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSur = new GridBagLayout();
		gbl_panelSur.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSur.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelSur.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSur.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSur.setLayout(gbl_panelSur);
		
		JLabel lblNewLabel = new JLabel("foto cancion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panelSur.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton botonPausar = new JButton("Pausar");
		botonPausar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_botonPausar = new GridBagConstraints();
		gbc_botonPausar.insets = new Insets(0, 0, 5, 5);
		gbc_botonPausar.gridx = 6;
		gbc_botonPausar.gridy = 1;
		panelSur.add(botonPausar, gbc_botonPausar);
		

		
		JLabel lblNewLabel_1 = new JLabel("Reproductor");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 10;
		gbc_lblNewLabel_1.gridy = 2;
		panelSur.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panelDerecho = new JPanel();
		add(panelDerecho, BorderLayout.EAST);
		GridBagLayout gbl_panelDerecho = new GridBagLayout();
		gbl_panelDerecho.columnWidths = new int[]{15, 0, 15, 0};
		gbl_panelDerecho.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelDerecho.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDerecho.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDerecho.setLayout(gbl_panelDerecho);
		
		JButton botonAñadirPlaylist = new BotonNegro("A\u00F1adir Playlist");
		
		botonAñadirPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		botonAñadirPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_botonAñadirPlaylist = new GridBagConstraints();
		gbc_botonAñadirPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_botonAñadirPlaylist.gridx = 1;
		gbc_botonAñadirPlaylist.gridy = 3;
		panelDerecho.add(botonAñadirPlaylist, gbc_botonAñadirPlaylist);
		
		BotonNegro botonBuscarCanciones = new BotonNegro("A\u00F1adir Playlist");
		
		botonBuscarCanciones.setText("BuscarCancion");
		botonBuscarCanciones.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_botonBuscarCanciones = new GridBagConstraints();
		gbc_botonBuscarCanciones.insets = new Insets(0, 0, 5, 5);
		gbc_botonBuscarCanciones.gridx = 1;
		gbc_botonBuscarCanciones.gridy = 5;
		panelDerecho.add(botonBuscarCanciones, gbc_botonBuscarCanciones);
		
		textFieldBuscarCanciones = new JTextField();
		
		textFieldBuscarCanciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldBuscarCanciones = new GridBagConstraints();
		gbc_textFieldBuscarCanciones.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldBuscarCanciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBuscarCanciones.gridx = 1;
		gbc_textFieldBuscarCanciones.gridy = 7;
		panelDerecho.add(textFieldBuscarCanciones, gbc_textFieldBuscarCanciones);
		textFieldBuscarCanciones.setColumns(10);
		
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
	
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{30, 109, 0, 0, 0, 30, 0};
		gbl_panelCentral.rowHeights = new int[]{24, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		JLabel labelResultados = new JLabel("Resultado");
		labelResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_labelResultados = new GridBagConstraints();
		gbc_labelResultados.gridwidth = 2;
		gbc_labelResultados.insets = new Insets(0, 0, 5, 5);
		gbc_labelResultados.anchor = GridBagConstraints.NORTHWEST;
		gbc_labelResultados.gridx = 2;
		gbc_labelResultados.gridy = 0;
		panelCentral.add(labelResultados, gbc_labelResultados);
		
	
		
		JButton btnSeleccionarPlaylist = new JButton("seleccionar");
		
		btnSeleccionarPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_btnSeleccionarPlaylist = new GridBagConstraints();
		gbc_btnSeleccionarPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionarPlaylist.gridx = 2;
		gbc_btnSeleccionarPlaylist.gridy = 2;
		panelCentral.add(btnSeleccionarPlaylist, gbc_btnSeleccionarPlaylist);
		btnSeleccionarPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		final JLabel labelPlaylistSeleccionada = new JLabel("Seleccionada");
		labelPlaylistSeleccionada.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		GridBagConstraints gbc_labelPlaylistSeleccionada = new GridBagConstraints();
		gbc_labelPlaylistSeleccionada.insets = new Insets(0, 0, 5, 5);
		gbc_labelPlaylistSeleccionada.gridx = 2;
		gbc_labelPlaylistSeleccionada.gridy = 3;
		panelCentral.add(labelPlaylistSeleccionada, gbc_labelPlaylistSeleccionada);
		
		/**
		 * Panel donde se mostrarán todas las canciones de una playlist
		 */
		final JPanel panelListarCancionesPlaylist = new JPanel();
		GridBagConstraints gbc_panelListarCancionesPlaylist = new GridBagConstraints();
		gbc_panelListarCancionesPlaylist.gridwidth = 4;
		gbc_panelListarCancionesPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_panelListarCancionesPlaylist.fill = GridBagConstraints.BOTH;
		gbc_panelListarCancionesPlaylist.gridx = 1;
		gbc_panelListarCancionesPlaylist.gridy = 5;
		panelCentral.add(panelListarCancionesPlaylist, gbc_panelListarCancionesPlaylist);
		
		//TODO implementar el reproductor
		final JList listaCancionesPlaylist = new JList();
		listaCancionesPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		/**
		 * Interfaz reproductor
		 */
		JButton botonReproducir = new JButton("Reproducir");
		botonReproducir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_botonReproducir = new GridBagConstraints();
		gbc_botonReproducir.insets = new Insets(0, 0, 5, 5);
		gbc_botonReproducir.gridx = 8;
		gbc_botonReproducir.gridy = 1;
		panelSur.add(botonReproducir, gbc_botonReproducir);
		
		
		
		
		/**
		 * LISTENERS
		 */
		
		/**
		 * Mosstrar todas las canciones que tiene una playlist al hacer click sobre una de ellas
		 */

		//Click en un elemento de una playlist
		listaPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String listaSeleccionada=(String) listaPlaylist.getSelectedValue();//elementos del JList
				labelPlaylistSeleccionada.setText(listaSeleccionada.toString());//en el label, muestro el nombre de la playlist Seleccionada
				
				PlayList lista = new PlayList();
				//cuando haga click en una playlist
				 cancionesModelo = new DefaultListModel();
				listaCancionesPlaylist.setModel(cancionesModelo);//añado el modelo al Jlist
				ArrayList<Cancion> todasCanciones=lista.getCancionesPlayList(listaSeleccionada);//devuelve un arraylist
		        //System.out.println(String.valueOf(sampleObject));

				for (Cancion cancion : todasCanciones) {
					//System.out.println("Cancion ---> "+cancion);
					cancionesModelo.addElement(cancion);//añado las canciones al modelo
				}
				panelListarCancionesPlaylist.add(listaCancionesPlaylist);//añado la lista, con las canciones, al panel
			}

		});
		panelListarCancionesPlaylist.add(listaCancionesPlaylist);//Panel, donde se muestran todas las canciones del JList

		/***************  HACER REPRODUCIR UNA CANCIÓNN *******************
		 * Evento al hacer click en una cancion.
		 */

		listaCancionesPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Cancion cancionARperoducir = (Cancion) listaCancionesPlaylist.getSelectedValue();//esto es un objeto de cancion
			}
		});
		
		final ReproducirCanciones reproducirCanciones = new ReproducirCanciones();

		//CLICK EN REPRODUCIR
		botonReproducir.addMouseListener(new MouseAdapter() {
			byte click=0;
			public void mouseClicked(MouseEvent e) {
				click=(byte) ((click +1)%2);
				
				//NO se como pner MusicaReproducir global, para que no me salga la restricción final y pueda llamar siempre que quiera al hilo
				cancionActual=(Cancion) listaCancionesPlaylist.getSelectedValue();
				MusicaReproducir reproducirCancion = new MusicaReproducir(cancionActual);
				
				if(click==1) {
					reproducirCancion.start();
				}else {
					try {
						reproducirCancion.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				//reproducirCanciones.reproducir(cancionActual, click, reproducirCancion);
			}
		});
		//CLICK EN PAUSAR
		botonPausar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancionActual=(Cancion) listaCancionesPlaylist.getSelectedValue();
				reproducirCanciones.pausar(cancionActual);
			}
		});
		
		//Añadir una nueva playlist
		botonAñadirPlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String nombre= JOptionPane.showInputDialog(ventana, "Introduce un nombre para playlist","Agregar playlist",JOptionPane.DEFAULT_OPTION);
		        String usuarioemail=ventana.usuarioLogueado.getEmail();
		        LocalDateTime fechaIncorporacion=LocalDateTime.now();//El constructor de playlist, se encargar de darle el formato
		     
		        File rutaFichero = null;
		        JFileChooser fChooser = new JFileChooser();
		        int seleccion = fChooser.showOpenDialog(ventana);
		        if(seleccion== JFileChooser.APPROVE_OPTION) {//si hace click en aprovar
		        	rutaFichero =fChooser.getSelectedFile();//en file guardo el fichero
		        }
		        
		        File origen = rutaFichero;
				String destino = "./fotos/";
		 
				if (origen.renameTo(new File(destino+ origen.getName()))) {
					//System.out.println("File is moved to " + destino);
				} else {
					//System.out.println("Failed");
				}
				PlayList nuevaList=null;
		        try {
		            nuevaList = new PlayList(destino, nombre, ventana.usuarioLogueado, fechaIncorporacion);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		                
		        modeloListaPlaylist.addElement(nuevaList.getNombre());
		        panelPlaylist.add(listaPlaylist);
		        
		        
			}
		});
		

		
		//Buscar canciones -- boton
		botonBuscarCanciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre= JOptionPane.showInputDialog(ventana, "Introduce un de cancion","Buscar Cancion",JOptionPane.DEFAULT_OPTION);
				try {
					Cancion cancion = new Cancion(nombre);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cancionesModelo.removeAllElements();
				cancionesModelo.addElement(nombre);
				panelListarCancionesPlaylist.add(listaCancionesPlaylist);
			}
		});
	}
}
