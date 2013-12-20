package abey;

import abey.entities.Enchere;
import abey.entities.ModePaiement;
import abey.entities.Produit;
import abey.entities.Surenchere;
import abey.entities.VenteImmediate;
import abey.services.EnchereService;
import abey.services.ProduitService;
import abey.services.SurenchereService;
import abey.services.UtilisateurService;
import abey.services.VenteImmediateService;
import abey.util.JsfUtil;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class EncherirController extends AbstractController {

    @EJB
    private SurenchereService surenchereService;

    @EJB
    private EnchereService enchereService;

    @EJB
    private UtilisateurService utilisateurService;
    
    private Enchere enchere;

    private Surenchere surenchere;

    public Surenchere getSurenchere() {
        if (surenchere == null) {
            surenchere = new Surenchere();
        }
        return surenchere;
    }

    public void setSurenchere(Surenchere surenchere) {
        this.surenchere = surenchere;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public String preEncherir() {
        return "/encheres/View";
    }

    public String encherir() {
        try {
            surenchere.setDateEnchere(new Date());
            surenchere.setEncherisseur(getUtilisateurConnecte());
            surenchere.setEnchere(enchere);
            surenchere.setModePaiement(ModePaiement.PAYPAL);
            surenchereService.create(surenchere);
            enchere.getSurencheres().add(surenchere);
            getUtilisateurConnecte().getSurencheres().add(surenchere);
            enchereService.edit(enchere);
            utilisateurService.edit(getUtilisateurConnecte());
            return "/utilisateurs/Profil";
        } catch (Exception e) {
            System.out.println("ex1=" + e);
            System.out.println("ex2=" + e.getCause());
            if (e.getCause() instanceof ConstraintViolationException) {
                System.out.println("ex3=" + ((ConstraintViolationException) e.getCause()).getConstraintViolations());
            }
            JsfUtil.addErrorMessage(
                    ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("AuctionSaleError")
            );
            return "/index.xhtml";
        }
    }

}
