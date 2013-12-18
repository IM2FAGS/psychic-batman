package abey;

import abey.entities.Boutique;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class AfficherBoutiqueController extends AbstractController {
    
    private Boutique boutique;

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
    
    public String initMaBoutique() {
        boutique = getUtilisateurConnecte().getBoutique();
        return "View";
    }
    
}
