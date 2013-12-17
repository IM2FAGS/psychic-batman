package abey.util;

import abey.entities.Categorie;
import abey.entities.Produit;
import abey.entities.Utilisateur;
import abey.services.CategorieService;
import abey.services.ProduitService;
import abey.services.UtilisateurService;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author nicolas
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class Test {

    @EJB
    private ProduitService produitService;

    @EJB
    private UtilisateurService utilisateurService;

    @EJB
    private CategorieService categorieService;

    @PostConstruct
    public void init() {
        try {
        fillCategories();
//        fillProduits();
        fillUtilisateurs();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void fillProduits() {
        System.out.println("fillProduits ... ");
        
        Random rd = new Random();
        List<Categorie> categories = categorieService.findAll();
        for (int i = 0; i < 40; i++) {
            Produit produit = new Produit();
            produit.setNom("Produit #" + i);
            produit.setDescription("Description du produit #" + i);
            produit.setCategorie(categories.get(rd.nextInt(categories.size())));
            produitService.create(produit);
        }
        System.out.println("fillProduits OK.");
    }

    private void fillUtilisateurs() {
        System.out.println("fillUtilisateurs ... ");
        for (int i = 0; i < 40; i++) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setMail("mail" + i + "@example.com");
            utilisateur.setNom("utilisateur" + i);
            String salt = Salt.newSalt();
            utilisateur.setSalt(salt);
            String passCrypte = null;
            try {
                passCrypte = Salt.hashPassword("pass", salt);
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex);
            }
            utilisateur.setPass(passCrypte);
            Calendar cal = Calendar.getInstance();
            cal.set(2010, 0, (i + 1) % 28);
            Date d = cal.getTime();
            utilisateur.setDateNaissance(d);
            utilisateurService.create(utilisateur);
        }
        System.out.println("fillUtilisateurs OK.");
    }

    private void fillCategories() {
        System.out.println("fillCategories ... ");
        String categories[] = {
            "Produits culturels",
            "Habillement - Textile",
            "Voyage - Tourisme - Loisirs",
            "Électroménager - Informatique - Téléphonie",
            "Santé - Beauté",
            "Places de spectacle",
            "Jeux - Jouets",
            "Alimentation - Gastronomie",
            "Développement photo",
            "Presse",
            "Mobilier",
            "Horticulture - Articles de jardin - Animalerie",
            "Boissons - Vins - Spiritueux",
            "Horlogerie - Bijouterie - Collection"
        };
        for (String nom : categories) {
            Categorie cat = new Categorie();
            cat.setNom(nom);
            categorieService.create(cat);
        }
        System.out.println("fillCategories OK.");
    }

}
