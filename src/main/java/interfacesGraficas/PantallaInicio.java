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

import excepciones.ContraseñaIncorrectaException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioIncorrectoException;
import funciones.FicheroDatosUsuario;
import utils.ConexionBD;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JSeparator;
import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Queue;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class PantallaInicio extends JPanel{
	private Ventana ventana;
	
	public PantallaInicio(Ventana ventana, String email) {
		Queue<String> datos=null;
		try {
			datos = FicheroDatosUsuario.obtenerDatosUsuario(email);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioIncorrectoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EmailInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ContraseñaIncorrectaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] datosUsuario = new String[4];//0 email, 1 nombre, 2 foto, 3 es premium
		byte pos=0;
		while(!datos.isEmpty()) {
			String actual = datos.poll();
			datosUsuario[pos]=actual;
			pos++;
		}
		System.out.println("datos usuario"+datosUsuario[3]);
		System.out.println("despues");
		setLayout(new BorderLayout(0, 0));
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel panelIzquierdo = new JPanel();
		add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInformacionUsr = new JPanel();
		panelIzquierdo.add(panelInformacionUsr, BorderLayout.NORTH);
		GridBagLayout gbl_panelInformacionUsr = new GridBagLayout();
		gbl_panelInformacionUsr.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
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
		
		JLabel labelFotoUsr = new JLabel("foto: "+datosUsuario[2]);
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
		
		JPanel panelPlaylist = new JPanel();
		panelPlaylist.setSize(200, 300);
		panelPlaylist.setBounds(20, 20, 100, 300);
		FlowLayout flowLayout = (FlowLayout) panelPlaylist.getLayout();
		flowLayout.setHgap(15);
		panelPlaylist.setBackground(SystemColor.controlHighlight);
		panelIzquierdo.add(panelPlaylist, BorderLayout.SOUTH);
		panelIzquierdo.setSize(200, 500);
		

		
		
		JList listaPlaylist = new JList();
		listaPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		panelPlaylist.add(listaPlaylist);
/*
		//Mostrar las playlist
		DefaultListModel listarPlaylist = new DefaultListModel();
		JList listaPlaylist = new JList();
		listaPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		listaPlaylist.setModel(listarPlaylist);
		
		FileWriter escribir=null;
		String ret="";
		Statement smt = ConexionBD.conectar();
		ResultSet consulta = smt.executeQuery("select * from infoarchivos");
		while(consulta.next()) {
			String nombre=consulta.getString("nombre");
			float tamanio=consulta.getFloat("tamanioMB");
			ret=nombre +" -- "+tamanio+"MB";
			modeloLista.addElement(ret);
			
			try {
				escribir=new FileWriter("./resultado.txt", true);

				escribir.write("Archivo: "+ret+" ");
				escribir.write("\n");
				escribir.flush();
				escribir.close();						
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("No se  hay fichero");
			}
			
		}
		ConexionBD.desconectar();
		
		add(listaInfoArchivos, BorderLayout.CENTER);
*/
		//hasta aqui
		
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(panelSur, BorderLayout.SOUTH);
		GridBagLayout gbl_panelSur = new GridBagLayout();
		gbl_panelSur.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSur.rowHeights = new int[]{0, 0};
		gbl_panelSur.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelSur.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelSur.setLayout(gbl_panelSur);
		
		JLabel lblNewLabel = new JLabel("foto cancion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelSur.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reproductor");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 9;
		gbc_lblNewLabel_1.gridy = 0;
		panelSur.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JPanel panelDerecho = new JPanel();
		add(panelDerecho, BorderLayout.EAST);
		GridBagLayout gbl_panelDerecho = new GridBagLayout();
		gbl_panelDerecho.columnWidths = new int[]{15, 0, 15, 0};
		gbl_panelDerecho.rowHeights = new int[]{19, 0, 0, 0, 0};
		gbl_panelDerecho.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDerecho.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDerecho.setLayout(gbl_panelDerecho);
		
		JButton botonAñadirPlaylist = new JButton("A\u00F1adir Playlist");
		botonAñadirPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		botonAñadirPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_botonAñadirPlaylist = new GridBagConstraints();
		gbc_botonAñadirPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_botonAñadirPlaylist.gridx = 1;
		gbc_botonAñadirPlaylist.gridy = 3;
		panelDerecho.add(botonAñadirPlaylist, gbc_botonAñadirPlaylist);
		
		JPanel panelCentral = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelCentral.getLayout();
		flowLayout_1.setVgap(10);
		add(panelCentral, BorderLayout.CENTER);
		System.out.println("dimensiones" +panelCentral.getWidth() +" - "+panelCentral.getBounds());
		System.out.println("dimensiones panelPlaylist" +panelPlaylist.getWidth() +" - "+panelPlaylist.getBounds());
		JLabel labelResultados = new JLabel("Resultado");
		labelResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		panelCentral.add(labelResultados);
		
	}
}
