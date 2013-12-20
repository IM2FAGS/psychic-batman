/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import abey.entities.Enchere;
import abey.entities.Produit;
import abey.services.EnchereService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author zianechm
 */

@ManagedBean
@SessionScoped
public class AfficherProduitController extends AbstractController{
	
    @EJB
    private EnchereService enchereService;
    
	private Produit produit;

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public String afficheProduit(){
		return "/produits/View";
	}

    public List<Enchere> getEncheresEnCours() {
        return enchereService.getEncheresEnCours(produit);
    }
}
