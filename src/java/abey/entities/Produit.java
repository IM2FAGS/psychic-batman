package abey.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.swing.ImageIcon;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anthony
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Produit.recherche",
               query = "select p from Produit p where p.nom like ?1")
})
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	private String nom;

	@Basic(optional = false)
	@NotNull
	private String description;

//	@Basic(optional = false)
//	@NotNull
//	private ImageIcon iconimImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public ImageIcon getIconimImage() {
//		return iconimImage;
//	}
//
//	public void setIconimImage(ImageIcon iconimImage) {
//		this.iconimImage = iconimImage;
//	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Produit)) {
			return false;
		}
		Produit other = (Produit) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "abey.entities.Produit[ id=" + id + " ]";
	}

}
