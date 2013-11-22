package abey;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gervaila
 */
@Entity
@Table(name = "PRODUITS")
@NamedQueries({
    @NamedQuery(name = "Produits.findProduct",
               query = "select p from Produits p where p.nom like ?1")
})
public class Produits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM")
    protected String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIX")
    private long prix;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATEDEBUT")
    protected Date dateDebut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DUREE")
    protected int duree;

    /*
    public Produits() {
    }
    public Produits(Long id) {
    this.id = id;
    }*/
    public Produits(){}
    public Produits(String nom, long prix, String description, Date dateDebut, int duree) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    
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

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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
        if (!(object instanceof Produits)) {
            return false;
        }
        Produits other = (Produits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abey.Produits[ id=" + id + " ]";
    }
    
    // methodes bonus
    public Date getDateFin() {
        long dureeMillisecondes = duree * 24 * 3600 * 1000;
        Date dateFin = new Date(dateDebut.getTime() + dureeMillisecondes);
        return dateFin;
    }
}
