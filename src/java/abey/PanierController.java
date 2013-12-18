package abey;

import abey.entities.ProduitPanier;
import abey.entities.VenteImmediate;
import abey.services.PanierService;
import abey.services.UtilisateurService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    
    public void AjouterProduitPanier(){
        getPanierSite().getProduits().add(produitPanier);
    }
}
