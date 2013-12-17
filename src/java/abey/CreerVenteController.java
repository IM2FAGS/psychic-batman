package abey;

import abey.entities.Produit;
import abey.entities.VenteImmediate;
import abey.services.ProduitService;
import abey.services.VenteImmediateService;
import abey.util.JsfUtil;
import abey.util.LangString;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerVenteController extends AbstractController {

    @EJB
    private VenteImmediateService venteImmediateService;

    @EJB
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
            venteImmediate.setPrix(BigDecimal.ONE);
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
        System.out.println("SETPRODUIT" + produit);
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
        if (produit == null) {
            if (recherche != null) {
                produits = produitService.rechercheProduits(recherche);
            }
            return "Create";
        } else if (venteImmediate.getStock() > 0 && venteImmediate.getPrix().compareTo(BigDecimal.ZERO) > 0) {
            try {
                venteImmediate.setDateVente(new Date());
                venteImmediate.setProduit(produit);
                venteImmediateService.create(venteImmediate);
                annulerCreer();
                JsfUtil.addSuccessMessage(
                        LangString.params(
                                ResourceBundle.getBundle("/Bundle").getString("SaleCreated"),
                                produit.getNom()
                        )
                );
                return "Created";
            } catch (Exception e) {
                JsfUtil.addErrorMessage(
                        ResourceBundle.getBundle("/Bundle").getString("SaleCreatedError")
                );
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

}
