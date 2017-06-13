

package models;

import exceptions.JuegoBarcoException;

public class PortaAvion extends Barco {
	
	//CONSTRUCTOR DE LA CLASE CON PARAMETROS	
	public PortaAvion(String posicionInicial, Ubicacion ubicacion) {
		super(posicionInicial, ubicacion);
		posiciones=new String[4];
		bajas=new boolean[4];
	}

	@Override
	void verificarTamano() {
		if(posiciones.length>4)
			throw new JuegoBarcoException("El tamaño de la Portavion debe ser de 4");
		
	}

	@Override
	boolean[] mostrarVida() {
                
            return bajas;
	}

}


