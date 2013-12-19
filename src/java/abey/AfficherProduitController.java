/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import abey.entities.Produit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author zianechm
 */

@ManagedBean
@SessionScoped
public class AfficherProduitController extends AbstractController{
	
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
}
