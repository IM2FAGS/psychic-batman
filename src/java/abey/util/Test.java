package abey.util;

import abey.entities.Categorie;
import abey.entities.Enchere;
import abey.entities.EnchereGagnee;
import abey.entities.ModePaiement;
import abey.entities.Produit;
import abey.entities.Utilisateur;
import abey.services.CategorieService;
import abey.services.EnchereGagneeService;
import abey.services.EnchereService;
import abey.services.ProduitService;
import abey.services.UtilisateurService;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
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

    @EJB
    private EnchereService enchereService;

    @EJB
    private EnchereGagneeService enchereGagneeService;

    @PostConstruct
    public void init() {
        try {
            fillCategories();
            fillProduits();
            fillUtilisateurs();
            fillEncheres();
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

    private void fillEncheres() {
        System.out.println("fillEncheres ... ");
        Enchere e = new Enchere();
        Date d /*= new Date()*/;
        e.setDateDebut(new Date());
        e.setDuree(98);
//        d.setTime(d.getTime()+60*1000);
//        e.setDateFin(d);
        e.setDateFin(new Date());
        e.setPalierMin(1);
        e.setPrixInitial(55);
        e.setProduit(produitService.findAll().get(0));
        e.setVendeur(utilisateurService.findAll().get(0));
        enchereService.create(e);

        e = new Enchere();
        d = new Date();
        e.setDateDebut(new Date());
        e.setDuree(98);
        d.setTime(d.getTime()+16*1000);
        e.setDateFin(d);
        e.setPalierMin(1);
        e.setPrixInitial(55);
        e.setProduit(produitService.findAll().get(0));
        e.setVendeur(utilisateurService.findAll().get(0));
        enchereService.create(e);
        
        e = new Enchere();
        d = new Date();
        e.setDateDebut(new Date());
        e.setDuree(98);
        d.setTime(d.getTime()+16*1000);
        e.setDateFin(d);
        e.setPalierMin(1);
        e.setPrixInitial(55);
        e.setProduit(produitService.findAll().get(0));
        e.setVendeur(utilisateurService.findAll().get(0));
        
        EnchereGagnee eg = new EnchereGagnee();
        eg.setAcheteur(e.getVendeur());
        eg.setDateSurenchere(d);
        eg.setEnchere(e);
        eg.setModePaiement(ModePaiement.CB);
        eg.setMontant(845);
        enchereService.create(e);
        enchereGagneeService.create(eg);
        e.setEnchereGagnee(eg);
        enchereService.edit(e);
        System.out.println("fillEncheres OK.");
    }

}
