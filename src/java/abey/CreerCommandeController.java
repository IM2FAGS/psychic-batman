package abey;

import abey.entities.Achat;
import abey.entities.Commande;
import abey.entities.Panier;
import abey.entities.ProduitPanier;
import abey.entities.Utilisateur;
import abey.login.UtilisateurSession;
import abey.services.CommandeService;
import abey.util.JsfUtil;
import java.util.ArrayList;
import java.util.List;
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
public class CreerCommandeController extends AbstractController {

    @ManagedProperty(value = "#{utilisateurSession}")
    protected UtilisateurSession utilisateurSession;

    @EJB
    private CommandeService commandeService;

    private Commande current;

    public Commande getSelected() {
        if (current == null) {
            current = new Commande();
        }
        return current;
    }

    public String create() {
        try {
            Utilisateur curUser = utilisateurSession.getUtilisateur();
            if (curUser != null) {
                curUser.getCommandes().add(current);
                current.setAcheteur(curUser);
                List<ProduitPanier> produits = curUser.getPanier().getProduits();
                List<Achat> achats = current.getAchats();
                current.setAchats(new ArrayList<Achat>());
                for (ProduitPanier p : produits) {
                    Achat a = new Achat();
                    a.setCommande(current);
                    a.setPrixUnitaire(p.getVenteImmediate().getPrix());
                    a.setQuantite(p.getQuantite());
                    achats.add(a);
                }
                curUser.setPanier(new Panier());
            } else {
                JsfUtil.addErrorMessage("ConnexionRequise");
                return null;
            }

            commandeService.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CommandeCree"));
            current = null;
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("EchecTransaction"));
            return null;
        }
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    public void setCommandeService(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

}