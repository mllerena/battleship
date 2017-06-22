package jugar;

import models.Barco;
import models.Buque;
import models.Barco.Ubicacion;
import models.Jugador;
import models.Lancha;
import models.PortaAvion;
import Tablero.TableroUi;

import Tablero.TableroParametrosUi;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Main {

    public static void main(String[] args) {

        Runnable procesoSonidoFondo = new Runnable() {

            @Override
            public void run() {

                while (true) {
                    try {
                        Player apl = new Player(
                                getClass().getClassLoader().getResourceAsStream("recursos/background.mp3")
                        );

                        apl.play();

                    } catch (JavaLayerException ex) {
                        Logger.getLogger(TableroUi.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(TableroUi.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        };

        Runnable procesoJuego = new Runnable() {
            @Override
            public void run() {
                TableroParametrosUi tableroParametos = new TableroParametrosUi();
                tableroParametos.cargarBarcosInicial(1);
                tableroParametos.setVisible(true);

            }

        };

        new Thread(procesoSonidoFondo).start();
        new Thread(procesoJuego).start();

    }

}
