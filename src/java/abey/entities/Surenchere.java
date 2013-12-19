package abey.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anthony
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Surenchere.getSurencheresGagnantes",
            query = "select s from Surenchere s "
            + "where s.encherisseur = ?1 "
            + "and s = s.enchere.surenchereGagnante "
            + "order by s.dateEnchere desc"),
    @NamedQuery(name = "Surenchere.getSurencheres",
            query = "select s from Surenchere s "
            + "where s.encherisseur = ?1 "
            + "order by s.dateEnchere desc")
})
public class Surenchere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Utilisateur encherisseur;

    @NotNull
    @ManyToOne
    private Enchere enchere;

    @Basic(optional = false)
    @NotNull
    private float montant;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnchere;

    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @Basic(optional = false)
    @NotNull
    private boolean consultee = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getEncherisseur() {
        return encherisseur;
    }

    public void setEncherisseur(Utilisateur encherisseur) {
        this.encherisseur = encherisseur;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Date dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public boolean isConsultee() {
        return consultee;
    }

    public void setConsultee(boolean consultee) {
        this.consultee = consultee;
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
        if (!(object instanceof Surenchere)) {
            return false;
        }
        Surenchere other = (Surenchere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abey.entities.Surenchere[ id=" + id + " ]";
    }

}
