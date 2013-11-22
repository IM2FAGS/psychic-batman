/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey.util;

import abey.ProductService;
import abey.Produits;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toinou
 */
@ManagedBean
@SessionScoped
public class ProduitsRecherche implements Serializable {

    private String query;
    private List<Produits> produits;
    private Produits selectedProduit;
    @ManagedProperty(value = "#{productService}")
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Produits getSelectedProduit() {
        System.out.println("get" + selectedProduit);
        return selectedProduit;
    }

    public void setSelectedProduit(Produits selectedProduit) {
        System.out.println("selected "+ produits.size());
        this.selectedProduit = selectedProduit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Produits> getProduits() {
        System.out.println("getproduits query="+query);
        return produits;
    }

    public void setProduits(List<Produits> produits) {
        System.out.println("ta race");
        this.produits = produits;
    }

    public String search() {
        
        produits = productService.getProduits("%" + query + "%");
        System.out.println("voila les produits "+produits.size());
        /*
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
         */
        return "/recherche";



    }
}
