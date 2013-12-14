package abey.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Anthony
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Utilisateur.getByNom",
            query = "select u from Utilisateur u where u.nom=?1")
})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "proprietaire")
    private Boutique boutique;

    @OneToMany(mappedBy = "acheteur")
    private Collection<EnchereGagnee> encheresGagnees;

    @OneToMany(mappedBy = "encherisseur")
    private Collection<Surenchere> surencheres;

    @OneToMany(mappedBy = "vendeur")
    private Collection<Enchere> encheresCrees;

    @OneToOne
    private Panier panier;

    @OneToMany(mappedBy = "acheteur")
    private Collection<Achat> achats;

    @OneToMany(mappedBy = "acheteur")
    private Collection<NoteProduit> notesProduits;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(unique = true)
    private String mail;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String nom;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String pass;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String salt;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

    @Basic(optional = false)
    @NotNull
    private boolean administrateur = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<EnchereGagnee> getEncheresGagnees() {
        return encheresGagnees;
    }

    public void setEncheresGagnees(Collection<EnchereGagnee> encheresGagnees) {
        this.encheresGagnees = encheresGagnees;
    }

    public Collection<Surenchere> getSurencheres() {
        return surencheres;
    }

    public void setSurencheres(Collection<Surenchere> surencheres) {
        this.surencheres = surencheres;
    }

    public Collection<Enchere> getEncheresCrees() {
        return encheresCrees;
    }

    public void setEncheresCrees(Collection<Enchere> encheresCrees) {
        this.encheresCrees = encheresCrees;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Collection<Achat> getAchats() {
        return achats;
    }

    public void setAchats(Collection<Achat> achats) {
        this.achats = achats;
    }

    public Collection<NoteProduit> getNotesProduits() {
        return notesProduits;
    }

    public void setNotesProduits(Collection<NoteProduit> notesProduits) {
        this.notesProduits = notesProduits;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "abey.entities.Utilisateur[ id=" + id + " ]";
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }

}
