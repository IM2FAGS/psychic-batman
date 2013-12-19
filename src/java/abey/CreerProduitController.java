package abey;

import abey.entities.Categorie;
import abey.entities.Image;
import abey.entities.Produit;
import abey.util.JsfUtil;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerProduitController extends AbstractController {

    @ManagedProperty(value = "#{rechercheController}")
    private RechercheController rechercheController;

    private Produit produit;
    
    private int action;

    public void setAction(int action) {
        this.action = action;
    }

    public void setRechercheController(RechercheController rechercheController) {
        this.rechercheController = rechercheController;
    }

    public Produit getProduit() {
        if (produit == null) {
            produit = new Produit();
        }
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public long getProduitCategorieId() {
        if (produit.getCategorie() == null) {
            return -1;
        }
        return produit.getCategorie().getId();
    }

    public void setProduitCategorieId(long id) {
        List<Categorie> categories = rechercheController.getAllCategories();
        for (Categorie categorie : categories) {
            if (categorie.getId() == id) {
                produit.setCategorie(categorie);
                break;
            }
        }
    }
    
    public String creer() {
        System.out.println("ACTION = " + action);
        switch (action) {
            case CreerVenteController.ACTION_CREER_VENTE_IMMEDIATE:
                return creerVenteImmediate();
                
            case CreerVenteController.ACTION_CREER_ENCHERE:
                return creerEnchere();
        }
        return null;
    }

    public String creerVenteImmediate() {
        return "/vente/Create";
    }
    
     public String creerEnchere() {
        return "/encheres/Create";
    }

    public void uploadImageProduit(FileUploadEvent event) {
        Image image = uploadImage(event);
        if (image != null) {
            List<Image> images = produit.getImages();
            images.add(image);
            produit.setImages(images);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageUploaded"));
        } else {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImageUploadedError"));
        }
    }

}
