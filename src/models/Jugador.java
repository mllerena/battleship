

package models;


import controlaArrays.ListaBarco;
import exceptions.JuegoBarcoException;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
	
	private String nombre;
	private String[][] ubicacionBarcos=new String[10][10];
	private String[][] disparosInCorrectos=new String[10][10];
        private List<Barco> barcos=new ListaBarco();
        
        
        private String[][] disparosCorrectos=new String[10][10];
	
        //CONSTRUCTOR DE LA CLASE JUGADOR
        
	public Jugador(String nombre) {
		this.nombre = nombre;
		setInicializar(ubicacionBarcos);
		setInicializar(disparosInCorrectos);
                setInicializar(disparosCorrectos);
	}
	//METODO SOLO UTILIZADO POR LA CLASE
        
	private void setInicializar(String[][] matriz){
		for(int i=0;i<matriz.length;i++){
			for(int j=0;j<matriz[i].length;j++){
				matriz[i][j]="X";
			}
		}
	}
	
	//ACCESO LISTA DE BARCO
        
	public List<Barco> getBarcos() {
		return barcos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[][] getUbicacionBarcos() {
		return ubicacionBarcos;
	}

	public void setUbicacionBarcos(String[][] ubicacionBarcos) {
		this.ubicacionBarcos = ubicacionBarcos;
	}
        
        

	/*public String[][] getDisparosInCorrectos() {
		return disparosInCorrectos;
	}

	public void setDisparosInCorrectos(String[][] disparosInCorrectos) {
		this.disparosInCorrectos = disparosInCorrectos;
	}
        
        
	
	public void mostrarAsiertos(){
		
	}
	
	public void mostrarFallos(){
		
	}*/

    public String[][] getDisparosCorrectos() {
        return disparosCorrectos;
    }

    public void setDisparosCorrectos(String[][] disparosCorrectos) {
        this.disparosCorrectos = disparosCorrectos;
    }

    public String[][] getDisparosInCorrectos() {
        return disparosInCorrectos;
    }

    public void setDisparosInCorrectos(String[][] disparosInCorrectos) {
        this.disparosInCorrectos = disparosInCorrectos;
    }
    
    

}
