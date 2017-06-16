/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mllerena
 */
public class DialogoModal extends JDialog {
    
    
    private JComboBox tiposBarco;
    
    
    private JComboBox orientacion;
    

    /**
     * Constructor que pone titulo al dialogo, construye la ventana y la hace
     * modal.
     * 
     * @param padre
     *            Frame que hace de padre de esta dialogo.
     */
    public DialogoModal(Frame padre, String posicion) {

        // padre y modal
        super(padre, true);
        
        //setDefaultCloseOperation(0);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setSize(50,100);
        
        setTitle("Por favor diga los criterios del barco");
        
        setAlwaysOnTop(true);
        setModal(true);
        setLocationRelativeTo( padre );
        
        
        getContentPane().setLayout(new BorderLayout());
       
        
        
       
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3,2));
        formPanel.setSize(50, 20);
        
        
        JLabel jl0 = new JLabel("Posición Capturada:");
        formPanel.add(jl0);
        
        JTextField jt0 = new JTextField( posicion );
        jt0.setColumns(25);
        jt0.setEditable(false);
        
        formPanel.add(jt0);
        
        
        JLabel jl1 = new JLabel("Tipo");
        formPanel.add(jl1);
        
        tiposBarco = new JComboBox();
        tiposBarco.addItem("Porta avion");
        tiposBarco.addItem("Buque");
        tiposBarco.addItem("Lancha");
        formPanel.add(tiposBarco);
        
        
        JLabel jl2 = new JLabel("Orientación");
        formPanel.add(jl2);
        
        orientacion = new JComboBox();
        orientacion.addItem("Horizontal");
        orientacion.addItem("Vertical");
        
        formPanel.add(orientacion);
        
        
        JButton btn = new JButton("Agregar");
        
        // Se oculta la ventana al pulsar <enter> sobre el button
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        
        

        getContentPane().add(formPanel, BorderLayout.CENTER);        
        
        getContentPane().add(btn, BorderLayout.SOUTH);   
        
    }

    public JComboBox getTiposBarco() {
        return tiposBarco;
    }

    public void setTiposBarco(JComboBox tiposBarco) {
        this.tiposBarco = tiposBarco;
    }

    public JComboBox getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(JComboBox orientacion) {
        this.orientacion = orientacion;
    }

    
    
    
    
}
