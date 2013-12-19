package abey;

import abey.entities.Commande;
import abey.entities.Surenchere;
import abey.entities.Utilisateur;
import abey.services.SurenchereService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@RequestScoped
public class AfficherHistoriqueController extends AbstractController {

    @EJB
    SurenchereService surenchereService;

    public List<Commande> getCommandes() {
        Utilisateur u = getUtilisateurConnecte();
        if (u != null) {
            return u.getCommandes();
        } else {
            return null;
        }
    }

    public List<Surenchere> getSurencheres() {
        Utilisateur u = getUtilisateurConnecte();
        if (u != null) {
            return u.getSurencheres();
        } else {
            return null;
        }
    }

    public List<Surenchere> getSurencheresRemportees() {
        Utilisateur u = getUtilisateurConnecte();
        if (u != null) {
            return surenchereService.getSurencheresGagnantes(u);
        } else {
            return null;
        }
    }
    
}
