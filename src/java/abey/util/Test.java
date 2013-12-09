/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import abey.entities.Produit;
import abey.entities.Utilisateur;
import abey.services.ProduitService;
import abey.services.UtilisateurService;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
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

    @PostConstruct
    public void init() {
        fillProduits();
        fillUtilisateurs();
    }

    private void fillProduits() {
        System.out.println("fillProduits ... ");
        for (int i = 0; i < 40; i++) {
            Produit produit = new Produit();
            produit.setNom("Produit #" + i);
            produit.setDescription("Description du produit #" + i);
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
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
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

}
