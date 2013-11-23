package abey.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anthony
 */
@Entity
public class EnchereGagnee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@NotNull
	private Enchere enchere;

	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSurenchere;

	@Basic(optional = false)
	@NotNull
	private short modePaiement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public Date getDateSurenchere() {
		return dateSurenchere;
	}

	public void setDateSurenchere(Date dateSurenchere) {
		this.dateSurenchere = dateSurenchere;
	}

	public short getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(short modePaiement) {
		this.modePaiement = modePaiement;
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
		if (!(object instanceof EnchereGagnee)) {
			return false;
		}
		EnchereGagnee other = (EnchereGagnee) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "abey.entities.EnchereGagnee[ id=" + id + " ]";
	}

}