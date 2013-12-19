package abey;

import abey.entities.Achat;
import abey.entities.Boutique;
import abey.entities.Produit;
import abey.entities.VenteImmediate;
import abey.services.BoutiqueService;
import abey.services.ProduitService;
import abey.services.VenteImmediateService;
import abey.util.JsfUtil;
import abey.util.LangString;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerVenteController extends AbstractController {

    @ManagedProperty(value = "#{creerProduitController}")
    private CreerProduitController creerProduitController;

    @EJB
    private VenteImmediateService venteImmediateService;

    @EJB
    private ProduitService produitService;

    @EJB
    private BoutiqueService boutiqueService;

    private VenteImmediate venteImmediate;

    private String recherche;

    private Produit produit;
    private List<Produit> produits;

    public void setCreerProduitController(CreerProduitController creerProduitController) {
        this.creerProduitController = creerProduitController;
    }

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
        if (produit != null) {
            return produit;
        }

        Produit produitCree = creerProduitController.getProduit();
        if (produitCree.getNom() != null) {
            return produitCree;
        }

        return null;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    private void annulerCreer() {
        venteImmediate = null;
        recherche = null;
        produits = null;
        produit = null;
        creerProduitController.setProduit(null);
    }

    public String creer() {
        venteImmediate = getVenteImmediate();
        Produit produitVente = getProduit();
        if (produitVente == null) {
            if (recherche != null) {
                produits = produitService.rechercheProduits(recherche);
            }
            return "Create";
        } else if (venteImmediate.getStock() > 0 && venteImmediate.getPrix().compareTo(BigDecimal.ZERO) > 0) {
            try {
                Boutique boutique = getUtilisateurConnecte().getBoutique();

                venteImmediate.setDateVente(new Date());
                venteImmediate.setProduit(produitVente);
                venteImmediate.setBoutique(boutique);
                venteImmediate.setAchats(new ArrayList<Achat>());

                List<VenteImmediate> ventesImmediates = boutique.getVentesImmediates();
                ventesImmediates.add(venteImmediate);
                boutique.setVentesImmediates(ventesImmediates);

                if (produitVente.getId() == null) {
                    produitService.create(produitVente);
                }

                System.out.println("boutique = " + boutique);
                boutiqueService.edit(boutique);

                venteImmediateService.create(venteImmediate);

                annulerCreer();
                JsfUtil.addSuccessMessage(
                        LangString.params(
                                ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("SaleCreated"),
                                produitVente.getNom()
                        )
                );
                return "Created";
            } catch (Exception e) {
                System.out.println("ex1=" + e);
                System.out.println("ex2=" + e.getCause());
                if (e.getCause() instanceof ConstraintViolationException) {
                    System.out.println("ex3=" + ((ConstraintViolationException) e.getCause()).getConstraintViolations());
                }
                JsfUtil.addErrorMessage(
                        ResourceBundle.getBundle("/Bundle", getLangueSession().getLocale()).getString("SaleCreatedError")
                );
                return "Create";
            }
        } else {
            System.out.println("Probleme avec le formulaire??");
            return "Create";
        }
    }

    public String creerProduit() {
        annulerCreer();
        creerProduitController.setAction(CreerProduitController.ACTION_CREER_VENTE_IMMEDIATE);
        return "/produits/Create";
    }

    public String annuler() {
        annulerCreer();
        return "Create";
    }

}
