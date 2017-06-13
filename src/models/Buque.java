package models;



import exceptions.JuegoBarcoException;
 
public class Buque extends Barco {
	
	
        //CONSTRUCTOR DE LA CLASE CON PARAMETROS
	public Buque(String posicionInicial, Ubicacion ubicacion) {
		super(posicionInicial, ubicacion);
		posiciones=new String[3];
		bajas=new boolean[3];
	}
	
	@Override
	void verificarTamano() {
		if(posiciones.length>3)
			throw new JuegoBarcoException("El tamaño del Buque debe ser de 3");
		
	}

	@Override
	boolean[] mostrarVida() {
		
		return bajas;
	}

}

