/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.login;

import javax.enterprise.inject.Model;

/**
 *
 * @author toinou
 */
@Model
public class Identifiants {
    
    private String nom;
    private String pass;

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

}
