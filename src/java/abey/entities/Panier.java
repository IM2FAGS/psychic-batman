package abey.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Anthony
 */
@Entity
public class Panier implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany
	private Collection<ProduitPanier> produits;

	@OneToMany
	private Collection<Enchere> encheres;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<ProduitPanier> getProduits() {
		return produits;
	}

	public void setProduits(Collection<ProduitPanier> produits) {
		this.produits = produits;
	}

	public Collection<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(Collection<Enchere> encheres) {
		this.encheres = encheres;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Panier)) {
			return false;
		}
		Panier other = (Panier) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "abey.Cart[ id=" + id + " ]";
	}

}
