/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import abey.Produits;
import abey.ProduitsController;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@Named("ProduitsRecherche")
@SessionScoped
public class ProduitsRecherche implements Serializable {

    private String query;
    private List<Produits> produits;
    private Produits selectedProduit;

    public Produits getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produits selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Produits> getProduits() {
        return produits;
    }

    public void setProduits(List<Produits> produits) {
        this.produits = produits;
    }

    public void search() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/abey");
            String updateString =
                    "SELECT * FROM ABEY.PRODUITS "
                    + "where NOM LIKE ?";
            PreparedStatement rechercheProduit = con.prepareStatement(updateString);
            rechercheProduit.setString(1, "%" + query + "%");
            ResultSet rs = rechercheProduit.executeQuery();
            System.out.println(rs);
            produits = new ArrayList<>();
            while (rs.next()) {
                System.out.println(rs.getString("nom"));
                System.out.println(rs.getString("nom") + rs.getLong("prix")
                        + rs.getString("description") + rs.getDate("dateDebut") + rs.getInt("duree"));
                produits.add(new Produits(rs.getString("nom"), rs.getLong("prix"),
                        rs.getString("description"), rs.getDate("dateDebut"), rs.getInt("duree")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
