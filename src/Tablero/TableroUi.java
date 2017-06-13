package Tablero;

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
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jugar.ProcesoDisparo;
import models.Barco;
import models.Buque;
import models.Jugador;
import models.Lancha;
import models.PortaAvion;
import util.JuegoBarco;

public class TableroUi extends JFrame implements ActionListener {

    JPanel celdas = new JPanel();
    JPanel celdasMapa = new JPanel();

    private Jugador jugador1;
    private Jugador jugador2;

    private List<Barco> contrincante;

    public TableroUi() {

        this.setTitle("battleship");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1240, 620);

    }

    public void cargarBarcosInicial(Jugador actual, Jugador contrincante) {

        setJugador1(actual);
        setJugador2(contrincante);

        getContentPane().removeAll();

        getContentPane().setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Derriba al Enemigo es el turno de: " + actual.getNombre()));

        getContentPane().add(titlePanel, BorderLayout.PAGE_START);

        //Creamos el conjunto de pestañas
        JTabbedPane pestañas = new JTabbedPane();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 2));

        contentPanel.add(creaPanelBase());
        contentPanel.add(creaPanelMapa());

        //Añadimos un nombre de la pestaña y el panel
        pestañas.addTab(actual.getNombre(), contentPanel);

        getContentPane().add(pestañas, BorderLayout.CENTER);

        //getContentPane().add(contentPanel, BorderLayout.CENTER);
        setBarcos(actual.getBarcos());
        setContrincante(contrincante.getBarcos());

    }

    public JPanel creaPanelBase() {

        JPanel panelBase = new JPanel();
        BorderLayout borderpanelBase = new BorderLayout(0, 20);
        panelBase.setLayout(borderpanelBase);

        JPanel title = new JPanel();
        title.add(new JLabel("Panel Base"));
        panelBase.add(title, BorderLayout.PAGE_START);

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

        panelBase.add(numberPanel, BorderLayout.LINE_START);

        JPanel celdas = new JPanel();
        GridLayout grid = new GridLayout(10, 10);
        
        celdas.setLayout(grid);

        for (int fila = 0; fila < 10; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                JButton btn = new JButton("");
                btn.setName(fila + "," + columna);
                //btn.setSize(10, 10);
                
                //btn.setLayout(new FlowLayout());
                btn.setBackground(null);
                
                btn.setBorder(null);
                
                btn.setBorderPainted(false);
                
                btn.setMargin(new Insets(0,0,0,0));
                
                //btn.setContentAreaFilled(false);
                
                //btn.addActionListener(this);
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

        panelBase.add(centerPanel, BorderLayout.CENTER);

        return panelBase;

    }

    public JPanel creaPanelMapa() {

        JPanel panelMapa = new JPanel();
        BorderLayout borderpanelBase = new BorderLayout(0, 20);
        panelMapa.setLayout(borderpanelBase);

        JPanel title = new JPanel();
        title.add(new JLabel("Panel Mapa"));
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

                //P, B, L
                if (jugador1.getDisparosCorrectos()[fila][columna].equals("P")) {
                    btn.setBackground(Color.GRAY);
                }

                if (jugador1.getDisparosCorrectos()[fila][columna].equals("B")) {
                    btn.setBackground(Color.BLACK);
                }

                if (jugador1.getDisparosCorrectos()[fila][columna].equals("L")) {
                    btn.setBackground(Color.BLUE);
                }

                if (jugador1.getDisparosInCorrectos()[fila][columna].equals("A")) {
                    try {

                        Image img = ImageIO.read(getClass().getClassLoader().getResource("recursos/agua.png"));
                        btn.setIcon(new ImageIcon(img));

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }

                celdas.add(btn);
            }
        }

        this.celdasMapa = celdas;

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
                    
                    if( barco.getUbicacion() == Barco.Ubicacion.horizontal ){
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/portaavion_horizontal_0"+ (i+1) +"_opt.png"));
                    }
                    
                    if( barco.getUbicacion() == Barco.Ubicacion.vertical ){
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/portaavion_vertical_0"+ (i+1) +"_opt.png"));
                    }
                    
                    celda.setIcon(new ImageIcon(img));
                } else if (barco instanceof Buque) {
                    
                    
                    if( barco.getUbicacion() == Barco.Ubicacion.horizontal ){
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/buque-horizontal_0"+ (i+1) +"_opt.png"));
                    }
                    
                    
                    if( barco.getUbicacion() == Barco.Ubicacion.vertical ){
                        img = ImageIO.read(getClass().getClassLoader().getResource("recursos/buque-vertical_0"+ (i+1) +"_opt.png"));
                    }
                    
                    
                    //buque_01_opt
                    
                    celda.setIcon(new ImageIcon(img));
                } else if (barco instanceof Lancha) {
                    img = ImageIO.read(getClass().getClassLoader().getResource("recursos/lancha_opt.png"));
                    celda.setIcon(new ImageIcon(img));
                } else {
                    throw new JuegoBarcoException("No existe el barco");
                }

                if (jugador2.getDisparosCorrectos()[fila][columna].equals("P")
                        || jugador2.getDisparosCorrectos()[fila][columna].equals("B")
                        || jugador2.getDisparosCorrectos()[fila][columna].equals("L")) {
                    img = ImageIO.read(getClass().getClassLoader().getResource("recursos/damage.png"));
                    celda.setIcon(new ImageIcon(img));
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

    // Busca el boton por la fila y la columna
    public JButton getButtonMapa(int fila, int columna) {
        for (int i = 0; i < 100; i++) {
            String name = fila + "," + columna;
            JButton btn = (JButton) celdasMapa.getComponent(i);
            if (btn.getName().equals(name)) {
                return btn;
            }
        }
        return null;
    }

    /*public static void main(String[] arg){
		TableroUi frame=new TableroUi("Jugador 1");
		frame.setVisible(true);
		
	}*/
    @Override
    public void actionPerformed(ActionEvent e) {

        Runnable d1 = new ProcesoDisparo() {
            @Override
            public void run() {

                try {
                    Player apl = new Player(
                            getClass().getClassLoader().getResourceAsStream("recursos/boom.mp3")
                    );

                    apl.play();

                } catch (JavaLayerException ex) {
                    Logger.getLogger(TableroUi.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(TableroUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };

        Runnable d2 = new ProcesoDisparo() {
            @Override
            public void run() {
                JButton btn = (JButton) e.getSource();
                String name = btn.getName();
                String[] coordenadas = name.split(",");
                int fila = Integer.parseInt(coordenadas[0]);
                int columna = Integer.parseInt(coordenadas[1]);
                System.out.println(fila + ", " + columna);

                boolean tocado = false;
                
                
                boolean estaVivo = false;
                String tipoBarco = "";

                for (int j = 0; j < getContrincante().size(); j++) {
                    Barco barco = getContrincante().get(j);
                    for (int i = 0; i < barco.getPosiciones().length; i++) {
                        int filaContrincante = JuegoBarco.getFila(barco.getPosiciones()[i]);
                        int columnaContrincante = JuegoBarco.getColumna(barco.getPosiciones()[i]);
                        
                        

                        if (filaContrincante == fila && columnaContrincante == columna) {

                            if (barco instanceof PortaAvion) {
                                System.out.println("Tocado un PortaAvion");
                                tocado = true;
                                btn.setBackground(Color.GRAY);

                                //String name = fila + "," + columna;
                                jugador1.getDisparosCorrectos()[fila][columna] = "P";
                                
                                barco.getBajas()[i]=false;
                                
                                
                                estaVivo = barco.estaVivo() ;
                                
                                tipoBarco = "porta avion";

                            } else if (barco instanceof Buque) {
                                System.out.println("Tocado un Buque");
                                tocado = true;
                                btn.setBackground(Color.BLACK);

                                jugador1.getDisparosCorrectos()[fila][columna] = "B";
                                
                                barco.getBajas()[i]=false;
                                
                                
                                estaVivo = barco.estaVivo() ;
                                
                                tipoBarco = "buque";

                            } else if (barco instanceof Lancha) {
                                System.out.println("Tocado un Lancha");
                                tocado = true;
                                btn.setBackground(Color.BLUE);

                                jugador1.getDisparosCorrectos()[fila][columna] = "L";
                                
                                barco.getBajas()[i]=false;
                                
                                estaVivo = barco.estaVivo() ;
                                
                                tipoBarco = "lancha";

                                
                            }
                            break;
                        }
                    }

                }

                if (!tocado) {
                    System.out.println("Es agua");

                    JOptionPane.showMessageDialog(null, "Fallaste, les diste al agua.");
                    
                    jugador1.getDisparosInCorrectos()[fila][columna] = "A";
                    
                    
                    JOptionPane.showMessageDialog(null, "Es el turno de tu contrincante. Por favor presione aceptar para cambiar de jugador.");


                    System.out.println("Tablero mapa correctos: ");
                    for (int i = 0; i < jugador1.getDisparosCorrectos().length; i++) {
                        for (int j = 0; j < jugador1.getDisparosCorrectos()[i].length; j++) {
                            System.out.print(jugador1.getDisparosCorrectos()[i][j] + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Tablero mapa INcorrectos--------------------------: ");
                    for (int i = 0; i < jugador1.getDisparosInCorrectos().length; i++) {
                        for (int j = 0; j < jugador1.getDisparosInCorrectos()[i].length; j++) {
                            System.out.print(jugador1.getDisparosInCorrectos()[i][j] + " ");
                        }
                        System.out.println();
                    }

                    cargarBarcosInicial(jugador2, jugador1);
                }
                else{
                    
                    String mensaje = "Tocado, Acertaste!";
                    
                    if( !estaVivo ){
                        mensaje = mensaje + ". Se hundio el "+tipoBarco;
                    }
                    
                    //System.out.println("barco bajas: "+barco.estaVivo());
                    
                    JOptionPane.showMessageDialog(null, mensaje);
                }
                
                
                //verificando si gane
                
                boolean gane = true;
                
                for (int j = 0; j < getContrincante().size(); j++) {
                    Barco barco = getContrincante().get(j);
                    for (int i = 0; i < barco.getPosiciones().length; i++) {
                        if( barco.estaVivo() ){
                            gane = false;
                            break;
                        }
                        
                    }
                }
                
                if( gane ){
                    JOptionPane.showMessageDialog(null, "FELICIDADADES GANASTE!!!!!  HUNDISTE TODA LA FLOTA DEL CONTRINCANTE");
                    System.exit(0);
                }
                

            }

        };
        
        new Thread(d1).start();
        new Thread(d2).start();
        

    }

}
