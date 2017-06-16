package Tablero;

import controlaArrays.ListaBarco;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.JuegoBarcoException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jugar.ProcesoDisparo;
import jugar.Tablero;
import models.Barco;
import models.Buque;
import models.Jugador;
import models.Lancha;
import models.PortaAvion;
import util.JuegoBarco;

import models.Barco.Ubicacion;

public class TableroParametrosUi extends JFrame implements ActionListener {

    JPanel celdas = new JPanel();
    JPanel celdasMapa = new JPanel();

    Tablero tablero = null;

    private Jugador jugador1 = null;
    private Jugador jugador2 = null;
    
    
    JTextField nombre = new JTextField();

    private List<Barco> contrincante;
    
    
    int numeroJugador = 0;

    public TableroParametrosUi() {
        jugador1=new Jugador("1");
        jugador2=new Jugador("2");    
        
        tablero = new Tablero(jugador1,jugador2);    
        
        this.setTitle("Parametros de jugadores");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(620, 620);

    }
    
    public TableroParametrosUi(Jugador j1, Jugador j2, Tablero t) {
        jugador1=j1;
        jugador2=j2;    
        
        tablero = tablero;    
        
        this.setTitle("Parametros de jugadores");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(620, 620);

    }

    public void cargarBarcosInicial(int numeroJugador) {
        
        System.out.println("cargarBarcosInicial. "+numeroJugador);
        
        this.numeroJugador = numeroJugador;
        
        getContentPane().removeAll();

        getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();

        formPanel.setSize(300, 20);

        //formPanel.setLayout(new GridLayout(1,2));
        JLabel jl1 = new JLabel("Nombre jugador "+numeroJugador);
        nombre = new JTextField();
        nombre.setColumns(25);
        formPanel.add(jl1);
        formPanel.add(nombre);

        getContentPane().add(formPanel, BorderLayout.PAGE_START);

        //Creamos el conjunto de pestañas
        JTabbedPane pestañas = new JTabbedPane();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));

        contentPanel.add(creaPanelConfiguracion());

        //Añadimos un nombre de la pestaña y el panel
        pestañas.addTab("Configure su flota", contentPanel);

        getContentPane().add(pestañas, BorderLayout.CENTER);

    }

    public JPanel creaPanelConfiguracion() {

        JPanel panelMapa = new JPanel();
        BorderLayout borderpanelBase = new BorderLayout(0, 20);
        panelMapa.setLayout(borderpanelBase);

        JPanel title = new JPanel();
        title.add(new JLabel("Seleccione la casilla donde desea sus barcos"));
        panelMapa.add(title, BorderLayout.PAGE_START);

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(10, 0));
        numberPanel.add(new JLabel("1"));
        numberPanel.add(new JLabel("2"));
        numberPanel.add(new JLabel("3"));
        numberPanel.add(new JLabel("4"));
        numberPanel.add(new JLabel("5"));
        numberPanel.add(new JLabel("6"));
        numberPanel.add(new JLabel("7"));
        numberPanel.add(new JLabel("8"));
        numberPanel.add(new JLabel("9"));
        numberPanel.add(new JLabel("10"));

        panelMapa.add(numberPanel, BorderLayout.LINE_START);

        JPanel celdas = new JPanel();
        GridLayout grid = new GridLayout(10, 10);
        celdas.setLayout(grid);

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                JButton btn = new JButton("");
                btn.setName(fila + "," + columna);
                btn.setSize(20, 20);
                btn.addActionListener(this);

                btn.setBorder(new LineBorder(Color.decode("#8298ad")));
                btn.setBackground(Color.decode("#161e3d"));
                celdas.add(btn);
            }
        }

        this.celdas = celdas;

        JPanel centerPanel = new JPanel();
        JPanel letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(0, 10));
        letterPanel.add(new JLabel("A"));
        letterPanel.add(new JLabel("B"));
        letterPanel.add(new JLabel("C"));
        letterPanel.add(new JLabel("D"));
        letterPanel.add(new JLabel("E"));
        letterPanel.add(new JLabel("F"));
        letterPanel.add(new JLabel("G"));
        letterPanel.add(new JLabel("H"));
        letterPanel.add(new JLabel("I"));
        letterPanel.add(new JLabel("J"));

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(letterPanel, BorderLayout.PAGE_START);
        centerPanel.add(celdas, BorderLayout.CENTER);

        panelMapa.add(centerPanel, BorderLayout.CENTER);

        return panelMapa;

    }

    public JTextField getNombre() {
        return nombre;
    }
    
    

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public List<Barco> getContrincante() {
        return contrincante;
    }

    public void setContrincante(List<Barco> contrincante) {
        this.contrincante = contrincante;
    }

    public void setBarcos(List<Barco> barcos) {
        for (Barco barco : barcos) {
            this.setBarco(barco);
        }
    }

    public void setBarco(Barco barco) {
        Image img = null;

        for (int i = 0; i < barco.getPosiciones().length; i++) {

            int fila = JuegoBarco.getFila(barco.getPosiciones()[i]);
            int columna = JuegoBarco.getColumna(barco.getPosiciones()[i]);

            JButton celda = getButton(fila, columna);

            if (celda == null) {
                return;
            }

            try {

                if (barco instanceof PortaAvion) {
                    //img = ImageIO.read(getClass().getClassLoader().getResource("recursos/portavion.png"));

                    if (barco.getUbicacion() == Barco.Ubicacion.horizontal) {
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/portaavion_horizontal_0" + (i + 1) + "_opt.png"));
                    }

                    if (barco.getUbicacion() == Barco.Ubicacion.vertical) {
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/portaavion_vertical_0" + (i + 1) + "_opt.png"));
                    }

                    celda.setIcon(new ImageIcon(img));
                    //#161e3d

                } else if (barco instanceof Buque) {

                    if (barco.getUbicacion() == Barco.Ubicacion.horizontal) {
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/buque-horizontal_0" + (i + 1) + "_opt.png"));
                    }

                    if (barco.getUbicacion() == Barco.Ubicacion.vertical) {
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/buque-vertical_0" + (i + 1) + "_opt.png"));
                    }

                    //buque_01_opt
                    celda.setIcon(new ImageIcon(img));

                } else if (barco instanceof Lancha) {
                    img = ImageIO.read(getClass().getClassLoader().getResource("recursos/lancha_opt.png"));
                    celda.setIcon(new ImageIcon(img));
                } else {
                    throw new JuegoBarcoException("No existe el barco");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    // Busca el boton por la fila y la columna
    public JButton getButton(int fila, int columna) {
        for (int i = 0; i < 100; i++) {
            String name = fila + "," + columna;
            JButton btn = (JButton) celdas.getComponent(i);
            if (btn.getName().equals(name)) {
                return btn;
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton) e.getSource();
        String name = btn.getName();
        String[] coordenadas = name.split(",");
        int fila = Integer.parseInt(coordenadas[0]);
        int columna = Integer.parseInt(coordenadas[1]);
        System.out.println(fila + ", " + columna);

        btn.setBackground(Color.LIGHT_GRAY);

        String filaPattern = JuegoBarco.getFilaNumero(fila);
        String columnaPattern = JuegoBarco.getColumnaLetras(columna);

        String coordenada = columnaPattern + filaPattern;

        System.out.println("El usuario a seleccionado: " + coordenada);

        DialogoModal dialogoModal = new DialogoModal((Frame) this, coordenada);
        dialogoModal.pack();  // para darle tamaño automático a la ventana.
        dialogoModal.setVisible(true);

        String orientacion = "" + dialogoModal.getOrientacion().getSelectedItem();
        String tipoBarco = "" + dialogoModal.getTiposBarco().getSelectedItem();

        //jugador1=new Jugador("Galo");
        //JOptionPane.showMessageDialog(null, "orientacion: "+orientacion+" tipoBarco:"+tipoBarco);
        
        btn.setBackground(Color.decode("#161e3d"));
        
        Ubicacion ub = null;

        if (orientacion.equals("Horizontal")) {
            ub = Ubicacion.horizontal;
        } else if (orientacion.equals("Vertical")) {
            ub = Ubicacion.vertical;
        }

        try {

            if (tipoBarco.equals("Porta avion")) {

                Barco portavion = new PortaAvion(coordenada, ub);
                
                if( numeroJugador == 1 ){
                    this.tablero.setBarco(jugador1, portavion);    
                }else if( numeroJugador == 2 ){
                    this.tablero.setBarco(jugador2, portavion);    
                }

                
                this.setBarco(portavion);

            } else if (tipoBarco.equals("Buque")) {

                Barco buque = new Buque(coordenada, ub);
                
                
                if( numeroJugador == 1 ){
                    this.tablero.setBarco(jugador1, buque);
                }else if( numeroJugador == 2 ){
                     this.tablero.setBarco(jugador2, buque);    
                }

               
                
                this.setBarco(buque);

            } else if (tipoBarco.equals("Lancha")) {
                Barco lancha = new Lancha(coordenada, ub);
                
                 if( numeroJugador == 1 ){
                    this.tablero.setBarco(jugador1, lancha);
                }else if( numeroJugador == 2 ){
                    this.tablero.setBarco(jugador2, lancha);
                }

                
                
                this.setBarco(lancha); 
                
            }
            
            

        } catch (JuegoBarcoException ex) {
            ex.getStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
            
            
            
            
            
            
            
        }
        
        
        
        if( numeroJugador == 1 ){
                
                ListaBarco listaBarcos = (ListaBarco) jugador1.getBarcos();
                        
                if(  listaBarcos.getPortavion() == 0 && listaBarcos.getBuque() == 0 && listaBarcos.getLancha() == 0){
                    
                    
                    String nombre = getNombre().getText();
                    
                    System.out.println("nombre: "+nombre);
                    
                    if( nombre.isEmpty() ){
                            nombre = JOptionPane.showInputDialog(null, "Falta Ingresar el nombre del jugador");    
                    }
                    
                    jugador1.setNombre(nombre);
                    
                    JOptionPane.showMessageDialog(null, "Usted a culminado el ingreso de sus barcos. Por favor ahora es el turno del jugador 2");
                    
                    setVisible(false);
                    
                    TableroParametrosUi  tableroParametos = new TableroParametrosUi(jugador1, jugador2, tablero);
                    tableroParametos.cargarBarcosInicial(2);
                    tableroParametos.setVisible(true);
                    
                    //System.exit(0);
                    
                    
                    
                    
                    //cargarBarcosInicial(2);
                    
                    
                }
                
                
            }else if( numeroJugador == 2 ){
                
                
                ListaBarco listaBarcos = (ListaBarco) jugador2.getBarcos();
                        
                if(  listaBarcos.getPortavion() == 0 && listaBarcos.getBuque() == 0 && listaBarcos.getLancha() == 0){
                    
                    
                    String nombre = getNombre().getText();
                    
                    if( nombre.isEmpty() ){
                            nombre = JOptionPane.showInputDialog(null, "Falta Ingresar el nombre del jugador");       
                    }
                    
                    jugador2.setNombre(nombre);
                    
                    JOptionPane.showMessageDialog(null, "Usted a culminado el ingreso de sus barcos. Es momento de inicar el juego!!!!!!!!!!!!");
                    
                    
                    setVisible(false);
                    
                    TableroUi tableroUi=new TableroUi();
                    tableroUi.cargarBarcosInicial(jugador1,jugador2);
                    tableroUi.setVisible(true);
                
                
                }
                
                 
            }

    }

}
