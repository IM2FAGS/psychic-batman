package abey;

import abey.entities.Enchere;
import abey.entities.Produit;
import abey.entities.VenteImmediate;
import abey.services.EnchereService;
import abey.services.ProduitService;
import abey.services.VenteImmediateService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author nicolas
 */
@ManagedBean
public class IndexController {

    @EJB
    private VenteImmediateService venteImmediateService;

    @EJB
    private EnchereService enchereService;

    @EJB
    private ProduitService produitService;

    public List<VenteImmediate> getTopVentes() {
        return venteImmediateService.findRange(0, 3);
    }

    public List<Produit> getCoupsDeCoeur() {
        return produitService.findRange(0, 3);
    }

    public List<Enchere> getEncheresASuivre() {
        return enchereService.findRange(0, 3);
    }

}
