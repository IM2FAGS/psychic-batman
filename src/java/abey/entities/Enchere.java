package abey.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Anthony
 */
@Entity
public class Enchere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Utilisateur vendeur;

    @OneToMany(mappedBy = "enchere")
    private List<Surenchere> surencheres;

    @OneToOne
    private Surenchere surenchereGagnante;

    @NotNull
    @ManyToOne
    private Produit produit;

    @Basic(optional = false)
    @NotNull
    private BigDecimal prixInitial;


    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;

    @Basic(optional = false)
    @NotNull
    private int duree;

    @Basic(optional = false)
    @NotNull
    private boolean terminee = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public List<Surenchere> getSurencheres() {
        return surencheres;
    }

    public void setSurencheres(List<Surenchere> surencheres) {
        this.surencheres = surencheres;
    }

    public Surenchere getSurenchereGagnante() {
        return surenchereGagnante;
    }

    public void setSurenchereGagnante(Surenchere surenchereGagnante) {
        this.surenchereGagnante = surenchereGagnante;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public BigDecimal getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(BigDecimal prixInitial) {
        this.prixInitial = prixInitial;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
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
        if (!(object instanceof Enchere)) {
            return false;
        }
        Enchere other = (Enchere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abey.Enchere[ id=" + id + " ]";
    }

    public Surenchere getDerniereSurenchere() {
        Surenchere max = null;
        for (Surenchere surenchere : surencheres) {
            if (max == null || surenchere.getDateEnchere().after(max.getDateEnchere())) {
                max = surenchere;
            }
        }
        return max;
    }
    
    public BigDecimal getMontantCourant() {
        Surenchere derniere = getDerniereSurenchere();
        if (derniere != null) {
            return derniere.getMontant();
        }
        return prixInitial;
    }

}
