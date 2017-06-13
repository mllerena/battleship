

package models;

import exceptions.JuegoBarcoException;

public class Lancha extends Barco {
	
        //CONSTRUCTOR DE LA CLASE CON PARAMETROS
	public Lancha(String posicionInicial, Ubicacion ubicacion) {
		super(posicionInicial, ubicacion);
		posiciones=new String[1];
		bajas=new boolean[1];
	}

	@Override
	void verificarTamano() {
		if(posiciones.length>1)
			throw new JuegoBarcoException("El tamaño de la lancha debe ser de 1");
		
	}

	@Override
	boolean[] mostrarVida() {
		// TODO Auto-generated method stub
		return bajas;
	}


}

