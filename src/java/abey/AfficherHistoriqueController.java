package abey;

import abey.entities.Commande;
import abey.entities.Surenchere;
import abey.entities.Utilisateur;
import abey.services.CommandeService;
import abey.services.SurenchereService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Anthony
 */
@ManagedBean
@RequestScoped
public class AfficherHistoriqueController extends AbstractController {

    @EJB
    SurenchereService surenchereService;

    @EJB
    CommandeService commandeService;

    public List<Commande> getCommandes() {
        Utilisateur u = getUtilisateurConnecte();
        if (u != null) {
            return commandeService.getCommandes(u);
        } else {
            return null;
        }
    }

    public List<Surenchere> getSurencheres() {
        Utilisateur u = getUtilisateurConnecte();
        if (u != null) {
            return surenchereService.getSurencheres(u);
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
