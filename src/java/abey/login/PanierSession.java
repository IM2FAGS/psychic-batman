package abey.login;

import abey.entities.Panier;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class PanierSession implements Serializable {

    private Panier panier;

    public Panier getPanier() {
        if(panier == null){
            panier = new Panier();
        }
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    
}
