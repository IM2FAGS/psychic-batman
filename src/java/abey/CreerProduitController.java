package abey;

import abey.entities.Image;
import abey.entities.Produit;
import abey.util.JsfUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author nicolas
 */
@ManagedBean
@SessionScoped
public class CreerProduitController extends AbstractController {

    private Produit produit;

    public Produit getProduit() {
        if (produit == null) {
            produit = new Produit();
        }
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    public String creer() {
        return null;
    }
    
    public void uploadImageProduit(FileUploadEvent event) {
        Image image = uploadImage(event);
        if (image != null) {
            List<Image> images = produit.getImages();
            images.add(image);
            produit.setImages(images);
            JsfUtil.addSuccessMessage("OK");
        } else {
            JsfUtil.addSuccessMessage("Erreur");
        }
    }

}
