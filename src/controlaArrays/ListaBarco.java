
package controlaArrays;

import exceptions.JuegoBarcoException;
import java.util.ArrayList;
import models.Barco;
import models.Buque;
import models.Lancha;
import models.PortaAvion;

public class ListaBarco extends ArrayList{
    private int portavion;
    private int buque;
    private int lancha;

    @Override
    public boolean add(Object e) {
        if(e instanceof PortaAvion){
            cantidadBarco(e);
            this.portavion--;
        }
        else if(e instanceof Buque){
            cantidadBarco(e);
            this.buque--;
        }
        else if(e instanceof Lancha){
            cantidadBarco(e);
            this.lancha--;
        }
        else
            throw new JuegoBarcoException("El objeto es diferente al tipo barco");
        return super.add(e);      
    }

    public ListaBarco() {
        this.portavion=2;
        this.buque=3;
        this.lancha=4;
    }
    
    void cantidadBarco(Object barco){
            if(barco instanceof PortaAvion && portavion==0)
                throw new JuegoBarcoException("No puedes ingresar mas portaviones");
            if(barco instanceof Buque && buque==0)
                throw new JuegoBarcoException("No puedes ingresar mas buques");
            if(barco instanceof Lancha && lancha==0)
                throw new JuegoBarcoException("No puedes ingresar mas lanchas");
        }

    public int getPortavion() {
        return portavion;
    }

    public int getBuque() {
        return buque;
    }

    public int getLancha() {
        return lancha;
    }

}
