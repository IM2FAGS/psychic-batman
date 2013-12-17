package abey;

import abey.entities.Produit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerProduitController extends AbstractController {

    private Produit produit;

    public Produit getProduit() {
        if (produit == null) {
            produit = new Produit();
        }
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public String creer() {
        return null;
    }

}
