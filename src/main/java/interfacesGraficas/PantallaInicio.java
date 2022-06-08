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
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JSeparator;

public class PantallaInicio extends JPanel{
	private Ventana ventana;
	
	public PantallaInicio(Ventana ventana) {
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
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 12;
		gbc_separator.insets = new Insets(0, 15, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 0;
		panelInformacionUsr.add(separator, gbc_separator);
		
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
		
		JLabel labelFotoUsr = new JLabel("foto");
		labelFotoUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelFotoUsr = new GridBagConstraints();
		gbc_labelFotoUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelFotoUsr.gridx = 3;
		gbc_labelFotoUsr.gridy = 8;
		panelInformacionUsr.add(labelFotoUsr, gbc_labelFotoUsr);
		
		JLabel labelNombreUsr = new JLabel("Usuario: ");
		labelNombreUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelNombreUsr = new GridBagConstraints();
		gbc_labelNombreUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombreUsr.gridx = 3;
		gbc_labelNombreUsr.gridy = 9;
		panelInformacionUsr.add(labelNombreUsr, gbc_labelNombreUsr);
		
		JLabel labelEmailUsr = new JLabel("Email");
		labelEmailUsr.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelEmailUsr = new GridBagConstraints();
		gbc_labelEmailUsr.insets = new Insets(0, 0, 5, 5);
		gbc_labelEmailUsr.gridx = 3;
		gbc_labelEmailUsr.gridy = 10;
		panelInformacionUsr.add(labelEmailUsr, gbc_labelEmailUsr);
		
		JLabel labelEsPremium = new JLabel("Es Premium");
		labelEsPremium.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_labelEsPremium = new GridBagConstraints();
		gbc_labelEsPremium.insets = new Insets(0, 0, 0, 5);
		gbc_labelEsPremium.gridx = 3;
		gbc_labelEsPremium.gridy = 11;
		panelInformacionUsr.add(labelEsPremium, gbc_labelEsPremium);
		
		JPanel panelPlaylist = new JPanel();
		panelIzquierdo.add(panelPlaylist, BorderLayout.SOUTH);
		
		JList listaPlaylist = new JList();
		listaPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		panelPlaylist.add(listaPlaylist);
		
		
		
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
		gbl_panelDerecho.rowHeights = new int[]{19, 0, 0, 0};
		gbl_panelDerecho.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDerecho.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDerecho.setLayout(gbl_panelDerecho);
		
		JLabel botonAñadirPlaylist = new JLabel("A\u00F1adir Playlist");
		botonAñadirPlaylist.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		GridBagConstraints gbc_botonAñadirPlaylist = new GridBagConstraints();
		gbc_botonAñadirPlaylist.insets = new Insets(0, 0, 0, 5);
		gbc_botonAñadirPlaylist.anchor = GridBagConstraints.NORTH;
		gbc_botonAñadirPlaylist.gridx = 1;
		gbc_botonAñadirPlaylist.gridy = 2;
		panelDerecho.add(botonAñadirPlaylist, gbc_botonAñadirPlaylist);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		JLabel labelResultados = new JLabel("Resultado");
		labelResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		panel.add(labelResultados);
		
	}
}
