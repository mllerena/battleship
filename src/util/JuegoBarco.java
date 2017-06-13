

package util;

import exceptions.JuegoBarcoException;

public class JuegoBarco {
	
	public static int getFila(String posicion){
            
                //System.out.println("JuegoBarco.getFila posicion: "+posicion);
		
		//B10
		String filaString = posicion.substring(1,posicion.length());
		
		int fila = 0;
		try{
			fila = Integer.parseInt(filaString);
		}
		catch(NumberFormatException ex){
			throw new JuegoBarcoException("La fila debe ser un número de 1-10");
		}
		if(fila>10)
			throw new JuegoBarcoException("La fila debe ser un número de 1-10");
		return fila-1;
	}
	
	public static int getColumna(String posicion){
		
		String colunmaString = posicion.substring(0,1);
		
		switch (colunmaString.toLowerCase()) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		case "i":
			return 8;
		case "j":
			return 9;
		default:
			throw new JuegoBarcoException("La columna esta fuera de rango. A-J");
		}
	}
        public static String getColumnaLetras(int  posicion){
		

		
		switch (posicion) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		case 6:
			return "G";
		case 7:
			return "H";
		case 8:
			return "I";
		case 9:
			return "J";
		default:
			throw new JuegoBarcoException("La columna esta fuera de rango. A-J");
		}
	}
        
        public static String  getFilaNumero(int posicion)
        {
            posicion+=1;
            String letra=String.valueOf(posicion);
            return letra ;  
        }

}
