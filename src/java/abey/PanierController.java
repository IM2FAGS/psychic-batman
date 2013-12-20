package abey;

import abey.entities.ProduitPanier;
import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class PanierController extends AbstractController {
    
    ProduitPanier produitPanier;
    
    public int getNumProduits() {
        return getPanierSite().getProduits().size();
    }

    public int getNumEncheres() {
        return getPanierSite().getEncheres().size();
    }

    public ProduitPanier getProduitPanier() {
        if(produitPanier == null){
            produitPanier = new ProduitPanier();
        }
        return produitPanier;
    }

    public void setProduitPanier(ProduitPanier produitPanier) {
        this.produitPanier = produitPanier;
    }
    
    public String ajouterProduitPanier(){
		produitPanier.setQuantite(1);
        getPanierSite().getProduits().add(produitPanier);
		return "/panier/View";
    }
}
