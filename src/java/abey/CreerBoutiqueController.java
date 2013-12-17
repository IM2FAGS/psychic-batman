package abey;

import abey.entities.Boutique;
import abey.entities.Utilisateur;
import abey.services.BoutiqueService;
import abey.util.JsfUtil;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
	
	private Boutique previous;

	public Boutique getCurrent() {
		return current;
	}

	public void setCurrent(Boutique current) {
		this.current = current;
	}

	public Boutique getPrevious() {
		return previous;
	}

	public void setPrevious(Boutique previous) {
		this.previous = previous;
	}

    public Boutique getSelected() {
        if (current == null) {
            current = new Boutique();
        }
        return current;
    }

    public String create() {
        try {
            Utilisateur utilisateur = getUtilisateurConnecte();
            if (utilisateur != null) {
                utilisateur.setBoutique(current);
                current.setProprietaire(utilisateur);
            } else {
                JsfUtil.addErrorMessage("ConnexionRequise");
                return null;
            }
            boutiqueService.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BoutiqueCree"));
			previous = current;
            current=null;
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("EchecTransaction"));
            return null;
        }
    }

    public void setBoutiqueService(BoutiqueService boutiqueService) {
        this.boutiqueService = boutiqueService;
    }

}
