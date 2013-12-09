package abey;

import abey.entities.Boutique;
import abey.entities.Utilisateur;
import abey.services.BoutiqueService;
import abey.util.JsfUtil;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class CreerBoutiqueController extends AbstractController {

    @EJB
    private BoutiqueService boutiqueService;
    
    private Boutique current;

    public Boutique getSelected() {
        if (current == null) {
            current = new Boutique();
        }
        return current;
    }

    public String create() {
        try {
            Utilisateur curUser = getUtilisateurConnecte();
            if(curUser != null){
                curUser.setBoutique(current);
                current.setProprietaire(curUser);
            }else {
                JsfUtil.addErrorMessage("ConnexionRequise");
                return null;
            }
            
            boutiqueService.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BoutiqueCree"));
            current=null;
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("EchecTransaction"));
            return null;
        }
    }

    public void setBoutiqueService(BoutiqueService boutiqueService) {
        this.boutiqueService = boutiqueService;
    }
    
    

}
