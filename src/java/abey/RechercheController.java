package abey;

import abey.services.ProduitService;
import abey.entities.Produit;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author toinou
 */
@ManagedBean
@SessionScoped
public class RechercheController extends AbstractController {

    private String query;
    private List<Produit> produits;
    private Produit selectedProduit;
    
    @EJB
    private ProduitService produitService;

    public void setProduitService(ProduitService produitService) {
        this.produitService = produitService;
    }

    public Produit getSelectedProduit() {
        System.out.println("get" + selectedProduit);
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        System.out.println("selected "+ produits.size());
        this.selectedProduit = selectedProduit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public String search() {
        System.out.println("SEARCH");
        produits = produitService.rechercheProduits(query);
        System.out.println("produits.size() = " + produits.size());
        return "/recherche";
    }
}
