package abey;

import abey.entities.Boutique;
import abey.entities.Image;
import abey.entities.Utilisateur;
import abey.services.BoutiqueService;
import abey.services.UtilisateurService;
import abey.util.JsfUtil;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class CreerBoutiqueController extends AbstractController {

    @ManagedProperty(value = "#{afficherBoutiqueController}")
    private AfficherBoutiqueController afficherBoutiqueController;

    @EJB
    private BoutiqueService boutiqueService;
    
    @EJB
    private UtilisateurService utilisateurService;

    private Boutique boutique;

    public Boutique getBoutique() {
        if (boutique == null) {
            boutique = new Boutique();
        }
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

    public void setAfficherBoutiqueController(AfficherBoutiqueController afficherBoutiqueController) {
        this.afficherBoutiqueController = afficherBoutiqueController;
    }

    public void uploadImageBoutique(FileUploadEvent event) {
        Image image = uploadImage(event);
        if (image != null) {
            boutique.setImage(image);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageUploaded"));
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ImageUploadedError"));
        }
    }

    public String creer() {
        try {
            Utilisateur utilisateur = getUtilisateurConnecte();
            if (utilisateur == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("YouMustBeLoggedIn"));
                return null;
            } else if (utilisateur.getBoutique() != null) {
                afficherBoutiqueController.setBoutique(utilisateur.getBoutique());
                return "/boutiques/View";
            } else if (boutique.getImage() == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ShopImageRequired"));
                return null;
            }
            utilisateur.setBoutique(boutique);
            boutique.setProprietaire(utilisateur);
            boutiqueService.create(boutique);
            utilisateurService.edit(utilisateur);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ShopCreated"));
            afficherBoutiqueController.setBoutique(boutique);
            //boutique = null;
            System.out.println("TOUT EST OK POUR LA CREATION DE LA BOUTIQUE " + boutique);
            return "/boutiques/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ShopCreatedError"));
            return null;
        }
    }
    
    public String initEditer() {
        boutique = getUtilisateurConnecte().getBoutique();
        return "/boutiques/Edit";
    }
    
    public String editer() {
        try {
            Utilisateur utilisateur = getUtilisateurConnecte();
            if (utilisateur == null) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("YouMustBeLoggedIn"));
                return null;
            }
            boutiqueService.edit(boutique);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ShopUpdated"));
            afficherBoutiqueController.setBoutique(boutique);
            boutique = null;
            return "/boutiques/View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ShopUpdatedError"));
            return null;
        }
    }

}
