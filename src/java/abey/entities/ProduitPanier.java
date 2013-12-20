package abey.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anthony
 */
@Entity
public class ProduitPanier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private VenteImmediate venteImmediate;

    @Basic(optional = false)
    @NotNull
    private int quantite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VenteImmediate getVenteImmediate() {
        return venteImmediate;
    }

    public void setVenteImmediate(VenteImmediate venteImmediate) {
        this.venteImmediate = venteImmediate;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
        if (!(object instanceof ProduitPanier)) {
            return false;
        }
        ProduitPanier other = (ProduitPanier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abey.entities.ProduitPanier[ id=" + id + " ]";
    }

	/**
	 *
	 * @return
	 */
	public BigDecimal calculerPrixProduitPanier(){
		return this.getVenteImmediate().getPrix().multiply(new BigDecimal(this.getQuantite()));
	}
}
