package abey.login;

import abey.PanierController;
import abey.entities.Panier;
import abey.entities.Utilisateur;
import abey.services.PanierService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class UtilisateurSession implements Serializable {

    private Utilisateur utilisateur;
    
    @EJB
    private PanierController panierController;
    @EJB
    private PanierService panierService;
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        Panier panierSession = panierController.getPanier();
        if(panierController.getNumEncheres()+panierController.getNumProduits() != 0 || utilisateur.getPanier() == null){
           this.utilisateur.setPanier(panierSession);
           panierService.create(panierSession);
        }else {
            panierController.setPanier(utilisateur.getPanier());
        }
    }
}
