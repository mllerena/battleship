

package jugar;


import exceptions.JuegoBarcoException;
import models.Barco;
import models.Barco.Ubicacion;
import models.Jugador;
import models.PortaAvion;
import util.JuegoBarco;
import models.Buque;

public class Tablero {
	
	private Jugador jugador1;
	private Jugador jugador2;
	
	public Tablero(Jugador jugador1,Jugador jugador2){
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}
	
        //ESTE METODO IMPLEMENTA BARCOS A EL JUGADOR
        
	public void setBarco(Jugador jugador,Barco barco){
		if(jugador!=jugador1 && jugador!=jugador2)
			throw new JuegoBarcoException("No existe el jugador");
		
                // B3, B10, C10, CR
                // ESTA CONDICION ME MUESTRA EL TAMAÑO QUE NO SE PASE DE 3
                
		if(barco.getPosicionInicial().length()>3)
			throw new JuegoBarcoException("La ubicación del barco se define por las columnas de A-J y las filas de 1-10");
					                
                //LLAMO A LOS METODOS PARA SU VALIDACION
                
                limitaPosiciones(barco);
                confirmaPosicion(jugador, barco);
                colocaBarco(jugador, barco);
                jugador.getBarcos().add(barco);
        }
        
        //METODO VALIDA UBICACION DE BARCO
        
        public void comprobarUbicacion(Barco barco) 
        {
            int columna = JuegoBarco.getColumna(barco.getPosicionInicial());
            int fila = JuegoBarco.getFila(barco.getPosicionInicial());
            if (barco instanceof PortaAvion)
            {
                if (Ubicacion.horizontal==barco.getUbicacion() && !(columna<7) )
                {
                  throw new JuegoBarcoException ("BARCO FUERA DE RANGO ");
                    
                }
               if (Ubicacion.vertical==barco.getUbicacion() && !(fila<7) )
                {
                  throw new JuegoBarcoException ("BARCO FUERA DE RANGO");
                    
                }
                
            }
            
            if (barco instanceof Buque)
            {
                if (Ubicacion.horizontal==barco.getUbicacion() && !(columna<8) )
                {
                  throw new JuegoBarcoException ("BUQUE FUERA DE RANGO ");
                    
                }
               if (Ubicacion.vertical==barco.getUbicacion() && !(fila<8) )
                {
                  throw new JuegoBarcoException ("BUQUE FUERA DE RANGO ");
                    
                }
                
            }
            
        }
        // METODO ASIGNA UBICACION BARCO
        
        public void limitaPosiciones(Barco barco)
                
        {
            int columna = JuegoBarco.getColumna(barco.getPosicionInicial());
	    int fila = JuegoBarco.getFila(barco.getPosicionInicial());
            
            
            comprobarUbicacion(barco);
                
             if(Ubicacion.horizontal==barco.getUbicacion()){
                    for(int i=0;i<barco.getPosiciones().length;i++){
                    barco.getPosiciones()[i]=JuegoBarco.getColumnaLetras(columna)+JuegoBarco.getFilaNumero(fila);
                        columna++;
                    }
                }
                    if(Ubicacion.vertical==barco.getUbicacion()){
                    for(int i=0;i<barco.getPosiciones().length;i++){
                    barco.getPosiciones()[i]=JuegoBarco.getColumnaLetras(columna)+JuegoBarco.getFilaNumero(fila);
                        fila++;
                    }
                }
            
            
        }
            //METODO VERIFICA SOBREPOSICION
        
            public void confirmaPosicion(Jugador jugador,Barco barco){
                for(int i=0;i<barco.getPosiciones().length;i++){
                    if(jugador.getUbicacionBarcos()[JuegoBarco.getFila(barco.getPosiciones()[i])]
                            [JuegoBarco.getColumna(barco.getPosiciones()[i])].equals("#")){
                        throw new JuegoBarcoException("NO PERMITE COLOCAR BARCO ");
                    }
                }
            }   
            
            //METODO COLOCA BARCOS EN TABLERO DEL JUGADOR
            
            public void colocaBarco(Jugador jugador,Barco barco){
                int columna = JuegoBarco.getColumna(barco.getPosicionInicial());
                int fila = JuegoBarco.getFila(barco.getPosicionInicial());
                
                if(Ubicacion.horizontal==barco.getUbicacion()){
                    for(int i=0;i<barco.getPosiciones().length;i++){
                        jugador.getUbicacionBarcos()[fila][columna]="#";
                        columna++;
                        barco.getBajas()[i]=true;
                    }
                }
                
                if(Ubicacion.vertical==barco.getUbicacion()){
                    for(int i=0;i<barco.getPosiciones().length;i++){
                        jugador.getUbicacionBarcos()[fila][columna]="#";
                        fila++;
                        barco.getBajas()[i]=true;
                    }
                }
            }
          
	
	/*
	public void disparar(Jugador jugador,String posicion){
		
	}
	
	public boolean juegoFinalizado(){
		return false;
	}*/
	

}

