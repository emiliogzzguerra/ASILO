import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class RegistraPaciente extends JFrame {

    private JTextArea familiarArea;
    private JTextArea responsableArea;
    private JTextArea instruccionesArea;
    private JButton buttonRegistrar;
    private JButton buttonImage;
    private ImageIcon imageIcon;
    private JTextArea PadecimientosArea;

    public RegistraPaciente(){

        createUIR();
        setTitle("REGISTRAR PACIENTE NUEVO");
        //Make window exit application on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Set display size
        setSize(700,750);
        setMinimumSize (new Dimension(700,750));
        //Center frame to middle of the screen
        setLocationRelativeTo(null);
        //Enable resize
        setResizable(true);
    }

    private void createUIR(){
        JPanel panelPrin = new JPanel();
        getContentPane().add(panelPrin);
        //MARGEN
        panelPrin.setBorder(new EmptyBorder(5,5,5,5));

        JPanel panelFormat = new JPanel(new GridBagLayout());
        panelPrin.add(panelFormat);
        //Posicionamiento de X
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 0;
        d.anchor = GridBagConstraints.LINE_END;

        panelFormat.add(new JLabel("Nombre completo del Paciente: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Fecha de Nacimiento (dd/mm/aaaa): "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Foto: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Dirección: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Familiares: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Responsables: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Padecimientos: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Estatus (Regular, Receso, Baja, Deceso): "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Cuarto: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Cama: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Seguro: "), d);
        d.gridy++;
        panelFormat.add(new JLabel("Instrucciones: "), d);


        //Reset positions
        d.gridx = 1;
        d.gridy = 0;
        d.anchor = GridBagConstraints.LINE_START; //LINE_START

        panelFormat.add(new JTextField(23), d);
        d.gridy++;
        panelFormat.add(new JTextField(10), d);
        d.gridy++;

        imageIcon = setupImage(imageIcon);
        panelFormat.add(new JLabel(imageIcon),d);

        d.anchor = GridBagConstraints.CENTER;

        buttonImage = new JButton("Insertar Imagen");
        buttonImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
        panelFormat.add(buttonImage, d);
        d.gridy++;
        d.anchor = GridBagConstraints.LINE_START;
        panelFormat.add(new JTextField(29), d);
        d.gridy++;
        JScrollPane scroll = setupJTArea(familiarArea);
        panelFormat.add(scroll, d);
        d.gridy++;
        JScrollPane scroll2 = setupJTArea(responsableArea);
        panelFormat.add(scroll2, d);
        d.gridy++;
        JScrollPane scroll3 = setupJTArea(PadecimientosArea);
        panelFormat.add(scroll3, d);
        d.gridy++;
        panelFormat.add(new JTextField(6), d);
        d.gridy++;
        panelFormat.add(new JTextField(3), d);
        d.gridy++;
        panelFormat.add(new JTextField(2), d);
        d.gridy++;
        panelFormat.add(new JTextField(20), d);
        d.gridy++;
        JScrollPane scroll4 = setupJTArea(instruccionesArea);
        panelFormat.add(scroll4, d);
        d.gridy ++;

        d.anchor = GridBagConstraints.CENTER;
        buttonRegistrar = new JButton("Registrar");
        buttonRegistrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    //Get connection with MySQL database
                    Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_asilo?useSSL=false",
                            "root", "1212");
                    Statement myStmt = mycon.createStatement();
                    //Adds the new score to the database
                    String mySQLTable = "asilo";

                    String sql = "INSERT INTO " + mySQLTable + "(nombre,ciudad,calle,codigo_postal,cuartos_disponibles,camas_disponibles) "
                            + "VALUES ('asilo1','Monterrey','Eugenio Gza Sada',64860,20,10)";

                    myStmt.executeUpdate(sql);
                    //Execute SQL query
                    ResultSet myRs = myStmt.executeQuery("SELECT * from " + mySQLTable);

                    while (myRs.next()) {
                        System.out.println(myRs.getString(1)); //gets the first column's rows.
                    }
                } catch (Exception e) {
                    System.out.println("Error with database connection");
                    e.printStackTrace();
                }
            }
        });
        panelFormat.add(buttonRegistrar, d);
        d.gridy++;
        panelFormat.add(new JLabel("Paciente Reservado con EXITO! "), d);

    }


    public JScrollPane setupJTArea (JTextArea jta){
        jta = new JTextArea();
        jta.setEditable(true);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(jta);
        scrollPane.setPreferredSize(new Dimension(350,90));
        return scrollPane;

    }

    public ImageIcon setupImage(ImageIcon imagee){
        imagee = new ImageIcon("/Users/carlosgalvez/Desktop/TEC/Proyecto/gui/working/abuelo.jpg"); // load the image to a imageIcon
        Image image = imagee.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imagee = new ImageIcon(newimg);  // transform it back
        return imagee;
    }

    public static void main (String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new RegistraPaciente().setVisible(true);
            }

        });
    }
}