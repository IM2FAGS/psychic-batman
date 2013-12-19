package abey.util;

import abey.entities.Achat;
import abey.entities.Boutique;
import abey.entities.Categorie;
import abey.entities.Commande;
import abey.entities.Enchere;
import abey.entities.ModePaiement;
import abey.entities.Produit;
import abey.entities.Surenchere;
import abey.entities.Utilisateur;
import abey.entities.VenteImmediate;
import abey.services.AchatService;
import abey.services.BoutiqueService;
import abey.services.CategorieService;
import abey.services.CommandeService;
import abey.services.EnchereService;
import abey.services.ProduitService;
import abey.services.SurenchereService;
import abey.services.UtilisateurService;
import abey.services.VenteImmediateService;
import java.math.BigDecimal;
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
    private AchatService achatService;

    @EJB
    private CommandeService commandeService;

    @EJB
    private VenteImmediateService venteImmediateService;

    @EJB
    private BoutiqueService boutiqueService;

    @EJB
    private EnchereService enchereService;

    @EJB
    private SurenchereService surenchereService;

    @PostConstruct
    public void init() {
        try {
            fillCategories();
            fillProduits();
            fillUtilisateurs();
            fillBoutiques();
            fillVentes();
            fillCommandes();
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

    private void fillBoutiques() {
        System.out.println("fillBoutiques ... ");
        Boutique b = new Boutique();
        b.setDescription("Bla bla");
        b.setNom("VDFGH");
        Utilisateur u = utilisateurService.findAll().get(0);
        b.setProprietaire(u);
        u.setBoutique(b);
        boutiqueService.create(b);
        utilisateurService.edit(u);
        System.out.println("fillBoutiques OK.");
    }

    private void fillVentes() {
        System.out.println("fillVentes ... ");
        VenteImmediate v = new VenteImmediate();
        v.setPrix(BigDecimal.TEN);
        v.setStock(18);
        Produit p = produitService.findAll().get(0);
        v.setProduit(p);
        p.getVentesImmediates().add(v);
        v.setDateVente(new Date());
        Boutique b = boutiqueService.findAll().get(0);
        v.setBoutique(b);
        b.getVentesImmediates().add(v);
        venteImmediateService.create(v);
        produitService.edit(p);
        boutiqueService.edit(b);
        System.out.println("fillVentes OK.");
    }

    private void fillCommandes() {
        System.out.println("fillCommandes ... ");
        Commande c = new Commande();
        c.setDateCommande(new Date());
        c.setModePaiement(ModePaiement.CB);
        Utilisateur u = utilisateurService.findAll().get(0);
        c.setAcheteur(u);
        u.getCommandes().add(c);
        commandeService.create(c);
        utilisateurService.edit(u);

        Achat a;
        VenteImmediate v;
        
        a = new Achat();
        a.setCommande(c);
        c.getAchats().add(a);
        a.setPrixUnitaire(new BigDecimal(50));
        a.setQuantite(8);
        v = venteImmediateService.findAll().get(0);
        a.setVenteImmediate(v);
        v.getAchats().add(a);
        achatService.create(a);
        commandeService.edit(c);
        venteImmediateService.edit(v);
        
        a = new Achat();
        a.setCommande(c);
        c.getAchats().add(a);
        a.setPrixUnitaire(new BigDecimal(50));
        a.setQuantite(8);
        v = venteImmediateService.findAll().get(0);
        a.setVenteImmediate(v);
        v.getAchats().add(a);
        achatService.create(a);
        commandeService.edit(c);
        venteImmediateService.edit(v);
        
        
        c = new Commande();
        c.setDateCommande(new Date());
        c.setModePaiement(ModePaiement.CB);
        u = utilisateurService.findAll().get(0);
        c.setAcheteur(u);
        u.getCommandes().add(c);
        commandeService.create(c);
        utilisateurService.edit(u);

        a = new Achat();
        a.setCommande(c);
        c.getAchats().add(a);
        a.setPrixUnitaire(new BigDecimal(50));
        a.setQuantite(8);
        v = venteImmediateService.findAll().get(0);
        a.setVenteImmediate(v);
        v.getAchats().add(a);
        achatService.create(a);
        commandeService.edit(c);
        venteImmediateService.edit(v);
        


        System.out.println("fillCommandes OK.");
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
        Produit p = produitService.findAll().get(0);
        Utilisateur u = utilisateurService.findAll().get(0);
        Date d /*= new Date()*/;
        e.setDateDebut(new Date());
        e.setDuree(98);
//        d.setTime(d.getTime()+60*1000);
//        e.setDateFin(d);
        e.setDateFin(new Date());
        e.setPrixInitial(new BigDecimal(55));
        e.setProduit(p);
        p.getEncheres().add(e);
        e.setVendeur(u);
        enchereService.create(e);
        produitService.edit(p);
        utilisateurService.edit(u);

        e = new Enchere();
        d = new Date();
        e.setDateDebut(new Date());
        e.setDuree(98);
        d.setTime(d.getTime() + 16 * 1000);
        e.setDateFin(d);
        e.setPrixInitial(new BigDecimal(55));
        e.setProduit(p);
        p.getEncheres().add(e);
        e.setVendeur(u);
        enchereService.create(e);
        produitService.edit(p);
        utilisateurService.edit(u);

        e = new Enchere();
        d = new Date();
        e.setDateDebut(new Date());
        e.setDuree(98);
        d.setTime(d.getTime() + 16 * 1000);
        e.setDateFin(d);
        e.setPrixInitial(new BigDecimal(55));
        e.setProduit(p);
        p.getEncheres().add(e);
        e.setVendeur(u);
        enchereService.create(e);
        produitService.edit(p);
        utilisateurService.edit(u);

        Surenchere eg = new Surenchere();
        eg.setEncherisseur(e.getVendeur());
        eg.setDateEnchere(d);
        eg.setEnchere(e);
        e.getSurencheres().add(eg);
        e.setSurenchereGagnante(eg);
        eg.setModePaiement(ModePaiement.CB);
        eg.setMontant(new BigDecimal(845));
        surenchereService.create(eg);
        enchereService.edit(e);

        e = new Enchere();
        d = new Date();
        e.setDateDebut(new Date());
        e.setDuree(98);
        d.setTime(d.getTime() + 60 * 60 * 60 * 1000);
        e.setDateFin(d);
        e.setPrixInitial(new BigDecimal(41));
        e.setProduit(p);
        p.getEncheres().add(e);
        e.setVendeur(u);
        enchereService.create(e);
        produitService.edit(p);
        utilisateurService.edit(u);
        System.out.println("fillEncheres OK.");
    }

}
