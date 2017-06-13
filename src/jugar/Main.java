

package jugar;



import models.Barco;
import models.Buque;
import models.Barco.Ubicacion;
import models.Jugador;
import models.Lancha;
import models.PortaAvion;
import Tablero.TableroUi;

public class Main {

 
	public static void main(String[] args) {
		
		Jugador jugador1=new Jugador("Galo");
		Jugador jugador2=new Jugador("Lorena");
                
               
		
		Tablero tablero = new Tablero(jugador1,jugador2);
		Barco portavion1=new PortaAvion("A1", Ubicacion.horizontal);
                Barco portavion2=new PortaAvion("D5", Ubicacion.vertical);
                Barco portavion3=new PortaAvion("B5", Ubicacion.vertical);
                Barco portavion4=new PortaAvion("F4", Ubicacion.horizontal);
		Barco buque1=new Buque("C2", Ubicacion.horizontal);
                Barco buque2=new Buque("C3", Ubicacion.horizontal);
                Barco buque3=new Buque("A7", Ubicacion.horizontal);
                Barco buque4=new Buque("C2", Ubicacion.horizontal);
                Barco buque5=new Buque("C3", Ubicacion.vertical);/////////
                Barco buque6=new Buque("A7", Ubicacion.horizontal);
		Barco lancha1=new Lancha("H5", Ubicacion.vertical);
                Barco lancha2=new Lancha("J10", Ubicacion.vertical);
                Barco lancha3=new Lancha("A10", Ubicacion.vertical);
                Barco lancha4=new Lancha("J1", Ubicacion.vertical);
                Barco lancha5=new Lancha("H5", Ubicacion.horizontal);
                Barco lancha6=new Lancha("J10", Ubicacion.horizontal);
                Barco lancha7=new Lancha("I10", Ubicacion.horizontal);
                Barco lancha8=new Lancha("J1", Ubicacion.vertical);
                
		tablero.setBarco(jugador1,portavion1);
		tablero.setBarco(jugador1,portavion2);
		tablero.setBarco(jugador1,buque1);
		tablero.setBarco(jugador1,lancha1);
                tablero.setBarco(jugador1,lancha2);
                tablero.setBarco(jugador1,buque2);
                tablero.setBarco(jugador1,buque3);
                tablero.setBarco(jugador1,lancha3);
                tablero.setBarco(jugador1,lancha4);
                
                tablero.setBarco(jugador2,portavion3);
                tablero.setBarco(jugador2,portavion4);
                tablero.setBarco(jugador2,buque4);
                tablero.setBarco(jugador2,buque5);
                //tablero.setBarco(jugador2,buque6);
                tablero.setBarco(jugador2,lancha5);
                tablero.setBarco(jugador2,lancha6);
                tablero.setBarco(jugador2,lancha7);
                tablero.setBarco(jugador2,lancha8);
                
                
                for(int i=0;i<jugador1.getUbicacionBarcos().length;i++){
			for(int j=0;j<jugador1.getUbicacionBarcos()[i].length;j++){
                            System.out.print(jugador1.getUbicacionBarcos()[i][j]+" ");
                        }
                        System.out.println();
                }
                
                if(portavion1.estaVivo())
                    System.out.println("Esta vivooooooo");
                
                TableroUi tableroUi=new TableroUi();
                
                tableroUi.cargarBarcosInicial(jugador1,jugador2);
                
		//tjagador1.setBarcos(jugador1.getBarcos());
                
                //guardo datos del contrincante
                //tjagador1.setContrincante(jugador2.getBarcos());
                
		tableroUi.setVisible(true);
		
                /*
		TableroUi tjagador2=new TableroUi(jugador2.getNombre());
		tjagador2.setBarcos(jugador2.getBarcos());
                //guardo datos del contrincante
                tjagador2.setContrincante(jugador1.getBarcos());
		//tjagador2.setVisible(true);
                */
                
                
	}

}
