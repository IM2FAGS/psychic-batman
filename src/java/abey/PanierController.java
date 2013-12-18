package abey;

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
    
    public int getNumProduits() {
        return getPanierSite().getProduits().size();
    }

    public int getNumEncheres() {
        return getPanierSite().getEncheres().size();
    }
}
