package abey;

import abey.entities.Panier;
import abey.login.UtilisateurSession;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import abey.services.PanierService;

/**
 *
 * @author toinou
 */
@ManagedBean
@SessionScoped
public class PanierController extends AbstractController {

    private Panier panier;
    
    @EJB
    private PanierService panierService;
    
    @EJB
    private UtilisateurSession utilisateurSession;
    
    public void setPanierService(PanierService panierService) {
        this.panierService = panierService;
    }

    public Panier getPanier() {
        if(panier == null){
            panier = new Panier();
        }
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public int getNumProduits(){
        return panier.getProduits().size();
    }
    
    public int getNumEncheres(){
        return panier.getEncheres().size();
    }
}
