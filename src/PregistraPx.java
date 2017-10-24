/*
Created by CarlosGalvez
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.border.MatteBorder;
import java.awt.EventQueue;


public class PregistraPx extends JFrame {

	private JTextField nombrePx, direccionPx, edadPx, cuartoPx, camaPx;
	private JButton altaDoc, guardarCambios, cambiarFoto;
	private ImageIcon imageIcon;

	public PregistraPx (){
		createUIR();
		setTitle("Registrar Nuevo Paciente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize-100,ySize-100);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void createUIR(){
		JPanel panelMainReg = new JPanel();
		panelMainReg.setBorder(new EmptyBorder(5,5,5,5));
		panelMainReg.setLayout(new BorderLayout());
		getContentPane().add(panelMainReg);

		//NORTH
		JPanel panelNorthReg = new JPanel(new BorderLayout());
		panelMainReg.add(panelNorthReg, BorderLayout.NORTH);

		//NORTHWEST
		JPanel panelNWReg = new JPanel(new GridBagLayout());
		panelNorthReg.add(panelNWReg, BorderLayout.WEST);
		GridBagConstraints d = new GridBagConstraints();
		d.gridx=0;
		d.gridy=0;
		d.anchor = GridBagConstraints.LINE_START;
		
		cambiarFoto = new JButton("Cambiar Foto");
		cambiarFoto.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String pathPhoto;
				pathPhoto = aceptarPhoto();
				imageIcon = setupImage(imageIcon, pathPhoto);
				panelNWReg.add(new JLabel(imageIcon), d);
			}
		});
		panelNWReg.add(cambiarFoto,d);

		//NORTHCENTER
		JPanel panelNCReg = new JPanel (new GridBagLayout());
		panelNorthReg.add(panelNCReg, BorderLayout.CENTER);
		d.gridy=0;
		d.anchor = GridBagConstraints.LINE_END;

		nombrePx = new JTextField("Nombre del paciente",32);
		panelNCReg.add(nombrePx,d);
		d.gridy++;

		direccionPx = new JTextField("Direccion del paciente",32);
		panelNCReg.add(direccionPx,d);
		d.gridy++;

		edadPx = new JTextField("Edad del paciente",32);
		panelNCReg.add(edadPx,d);

		//Reset pos
		d.gridy=0;
		d.gridx=1;
		d.anchor = GridBagConstraints.LINE_START;

		cuartoPx = new JTextField("Cuarto del Paciente",32);
		panelNCReg.add(cuartoPx,d);
		d.gridy++;

		camaPx = new JTextField("Cama del paciente",32);
		panelNCReg.add(camaPx,d);

		//NORTHEAST
		JPanel panelNEReg = new JPanel(new GridBagLayout());
		panelNorthReg.add(panelNEReg, BorderLayout.EAST);
		d.gridx=0;
		d.gridy=0;
		d.anchor = GridBagConstraints.LINE_START;

		altaDoc = new JButton("Dar de alta con documento");
		altaDoc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				aceptarArchivo();
			}
		});
		panelNEReg.add(altaDoc,d);
		d.gridy++;

		guardarCambios = new JButton("Guardar Cambios");
		guardarCambios.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

			}
		});
		panelNEReg.add(guardarCambios,d);

		//CENTER

		JPanel panelCenterReg = new JPanel();
		panelCenterReg.setLayout(new GridBagLayout());
		JTabbedPane jTPReg = new JTabbedPane();	
		d.weightx = d.weighty = 1.0;	
		d.gridx=0;
		d.fill=GridBagConstraints.BOTH;
		jTPReg.add("Tratamientos", Tratamientos());
		//d.gridx++;
		jTPReg.add("Familiares", Familiares("Panel de Familiares"));
		//d.gridx++;
		jTPReg.add("Emergencia", Emergencia());
		//d.gridx++;
		jTPReg.add("Expediente", Expediente("Panel de Expediente"));
		//d.gridx++;
		jTPReg.add("Medicamentos", Medicamentos("Panel de Medicamentos"));
		panelCenterReg.add(jTPReg,d);
		panelMainReg.add(panelCenterReg, BorderLayout.CENTER);

	}

	//Lo que va dentro de Tratamientos
	//Hacerlo con Layout
	protected JComponent Tratamientos() {
    	JPanel listContainer = new JPanel();
    	JPanel send = new JPanel();
    	JScrollPane scrollPaneF = new JScrollPane(send);

    	listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
    	send.add(new JScrollPane(listContainer), BorderLayout.CENTER);
    	JButton button = new JButton("Agregar nuevo tratamiento");
    	button.addActionListener(new ActionListener(){

    		@Override
    		public void actionPerformed(ActionEvent evt){
    			final JPanel newPanel = new JPanel();
    			newPanel.add(new JTextField("Nombre del tratamiento", 16));
    			listContainer.add(newPanel);
    			listContainer.revalidate();

    			JTextArea textArea = new JTextArea();
				textArea.setEditable(true);
				//Go to next line when reach horizontal limit
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);

				JScrollPane scrollPane = new JScrollPane(textArea);
				scrollPane.setPreferredSize(new Dimension(700, 56));
				newPanel.add(scrollPane);
				listContainer.add(newPanel);
				listContainer.revalidate();

    			//Scroll down to last added panel
    			SwingUtilities.invokeLater(new Runnable(){
    				@Override
    				public void run(){
    					newPanel.scrollRectToVisible(newPanel.getBounds());
    				}
    			});
    		}
    	});
    	send.add(button, BorderLayout.PAGE_END);
    	return scrollPaneF;
		
	}

	//Lo que va dentro de Familiares
	protected JComponent Familiares(String text) {
    	JPanel panelG = new JPanel(false);
    	JLabel filler = new JLabel(text);
    	filler.setHorizontalAlignment(JLabel.CENTER);
    	panelG.setLayout(new GridLayout(1, 1));
    	panelG.add(filler);
    	return panelG;
	}

	//Lo que va dentro de Emergencia
	protected JComponent Emergencia() {
    	JPanel panelEmergencia = new JPanel();
    	panelEmergencia.setLayout(new BoxLayout(panelEmergencia, BoxLayout.PAGE_AXIS));
    	JTextField poliza = new JTextField(32);
    	JTextField vencimiento = new JTextField(32);
    	JTextField telefonos = new JTextField(32);
    	JTextField hospital = new JTextField(32);
    	JTextArea info = new JTextArea();
    	info.setEditable(true);
    	info.setLineWrap(true);
    	info.setWrapStyleWord(true);

    	JScrollPane scrollInfo = new JScrollPane(info);
    	scrollInfo.setPreferredSize(new Dimension(700,56));

    	JPanel panelEM = new JPanel(new GridBagLayout());
    	//panelEM.setLayout(new BoxLayout(panelEM, BoxLayout.LINE_AXIS));
    	

    	GridBagConstraints gbc2 = new GridBagConstraints();
    	gbc2.gridx = 0;
    	gbc2.gridy = 0;
    	gbc2.anchor = GridBagConstraints.LINE_END;

    	panelEM.add(new JLabel("Poliza de seguro"),gbc2);
    	gbc2.gridy++;
    	panelEM.add(new JLabel("Fecha de vencimiento de poliza"),gbc2);
    	gbc2.gridy++;
    	panelEM.add(new JLabel("Telefonos de ambulancia"),gbc2);
    	gbc2.gridy++;
    	panelEM.add(new JLabel("Hospital de preferencia"),gbc2);
    	gbc2.gridy++;
    	panelEM.add(new JLabel("Informacion en caso de emergencia"),gbc2);

    	//Reset positions
    	gbc2.gridy = 0;
    	gbc2.gridx = 1;
    	gbc2.anchor = GridBagConstraints.LINE_START;

    	panelEM.add(poliza,gbc2);
    	gbc2.gridy++;
    	panelEM.add(vencimiento,gbc2);
    	gbc2.gridy++;
    	panelEM.add(telefonos,gbc2);
    	gbc2.gridy++;
    	panelEM.add(hospital,gbc2);
    	gbc2.gridy++;
    	panelEM.add(scrollInfo,gbc2);

    	panelEmergencia.add(panelEM, BorderLayout.CENTER);

    	return panelEmergencia;
	}

	//Lo que va dentro de Expediente
	protected JComponent Expediente(String text) {
    	JPanel panelG = new JPanel(false);
    	JLabel filler = new JLabel(text);
    	filler.setHorizontalAlignment(JLabel.CENTER);
    	panelG.setLayout(new GridLayout(1, 1));
    	panelG.add(filler);
    	return panelG;
	}

	//Lo que va dentro de Medicamentos
	protected JComponent Medicamentos(String text) {
    	JPanel panelG = new JPanel(false);
    	JLabel filler = new JLabel(text);
    	filler.setHorizontalAlignment(JLabel.CENTER);
    	panelG.setLayout(new GridLayout(1, 1));
    	panelG.add(filler);
    	return panelG;
	}

	public ImageIcon setupImage(ImageIcon imagee, String path){
		imagee = new ImageIcon(path); // load the image to a imageIcon
		Image image = imagee.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imagee = new ImageIcon(newimg);  // transform it back
		return imagee;
	}

	private void aceptarArchivo(){
		JPanel contentPane = new JPanel();
		//Crear el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		//Abrir la ventana, guardar la opcion
		int seleccion = fc.showOpenDialog(contentPane);
		//Si el usuario, da click en aceptar
		if(seleccion == JFileChooser.APPROVE_OPTION){
			//Seleccionar el archivo
			File archivo = fc.getSelectedFile();
			//Test de que si agarra el archivo
			//direccionPx.setText(archivo.getAbsolutePath());
		}
	}

	private String aceptarPhoto(){
		JPanel contentPane = new JPanel();
		//Crear el objeto JFileChooser
		JFileChooser fc = new JFileChooser();
		//Abrir la ventana, guardar la opcion
		int seleccion = fc.showOpenDialog(contentPane);
		File archivo;
		String path = "";
		//Si el usuario, da click en aceptar
		if(seleccion == JFileChooser.APPROVE_OPTION){
			//Seleccionar el archivo
			archivo = fc.getSelectedFile();
			//Test de que si agarra el archivo
			path = archivo.getAbsolutePath();
		}
		return path;
	}


	//Entry point of the program
	public static void main (String [] args){
		//Create a frame and shouw it through SwingUtilites
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new PregistraPx().setVisible(true);
			}

		});

	}
}













