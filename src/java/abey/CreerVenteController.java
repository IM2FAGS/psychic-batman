package abey;

import abey.entities.Produit;
import abey.entities.VenteImmediate;
import abey.services.ProduitService;
import abey.services.VenteImmediateService;
import abey.util.JsfUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerVenteController extends AbstractController {

    @ManagedProperty(value = "#{venteImmediateService}")
    private VenteImmediateService venteImmediateService;

    @ManagedProperty(value = "#{produitService}")
    private ProduitService produitService;

    private VenteImmediate venteImmediate;

    private String recherche;

    private List<Produit> produits;
    private Produit produit;

    public String getRecherche() {
        return recherche;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public VenteImmediate getVenteImmediate() {
        if (venteImmediate == null) {
            venteImmediate = new VenteImmediate();
            venteImmediate.setStock(1);
        }
        return venteImmediate;
    }

    public void setVenteImmediate(VenteImmediate venteImmediate) {
        this.venteImmediate = venteImmediate;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    private void annulerCreer() {
        venteImmediate = null;
        recherche = null;
        produits = null;
        produit = null;
    }

    public String creer() {
        venteImmediate = getVenteImmediate();
        if (venteImmediate.getProduit() == null) {
            produits = produitService.rechercheProduits(recherche);
            return "Create";
        } else if (venteImmediate.getStock() > 0 && venteImmediate.getPrix() > 0) {
            try {
                venteImmediate.setDateVente(new Date());
                venteImmediate.setProduit(produit);
                venteImmediateService.create(venteImmediate);
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SaleCreated"));
                annulerCreer();
                return "Create";
            } catch (Exception e) {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return "Create";
            }
        } else {
            return "Create";
        }
    }
    
    public String creerProduit() {
        annulerCreer();
        return "/produits/Create";
    }
    
    public String annuler() {
        annulerCreer();
        return "Create";
    }

    public void setVenteImmediateService(VenteImmediateService venteImmediateService) {
        this.venteImmediateService = venteImmediateService;
    }

    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

}
