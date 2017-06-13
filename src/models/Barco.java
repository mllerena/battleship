

package models;

public abstract class Barco {

	
	public enum Ubicacion{
		horizontal,
		vertical
	}
	
	private String posicionInicial;
	private Ubicacion ubicacion;
	
	protected String[] posiciones;
	protected boolean[] bajas;
	
	public Barco(String posicionInicial,Ubicacion ubicacion){
		this.posicionInicial = posicionInicial;
		this.ubicacion = ubicacion;
	}
	
	public boolean estaVivo() {
		for(int i=0;i<bajas.length;i++){
			if(bajas[i])
				return true;
		}
		return false;
	}
        
        

	public String[] getPosiciones() {
		return posiciones;
	}
	
	abstract void verificarTamano();
	abstract boolean[] mostrarVida();
	
	// portavion B3-horizontal ['b3','c3','d3','e3']
	public void setPosiciones(String[] posiciones){
		verificarTamano();
		this.posiciones = posiciones;
	}
	
	public boolean[] getBajas() {
		return bajas;
	}
	
	public String getPosicionInicial() {
		return posicionInicial;
	}
	public void setPosicionInicial(String posicionInicial) {
		this.posicionInicial = posicionInicial;
	}
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

    
}


