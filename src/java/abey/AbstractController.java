package abey;

import abey.entities.Image;
import abey.entities.Panier;
import abey.entities.Utilisateur;
import abey.login.PanierSession;
import abey.login.UtilisateurSession;
import abey.services.PanierService;
import abey.services.UtilisateurService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public abstract class AbstractController implements Serializable {

    @ManagedProperty(value = "#{utilisateurSession}")
    private UtilisateurSession utilisateurSession;

    @ManagedProperty(value = "#{panierSession}")
    private PanierSession panierSession;

    @EJB
    private PanierService panierService;
    
    @EJB
    private UtilisateurService utilisateurService;
    
    @EJB
    protected abey.services.ImageService imageService;

    private static final int sizeLimit = 100000;

    protected Image uploadImage(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        Image image = new Image();

        image.setMimetypeOriginal(uploadedFile.getContentType());

        System.out.println("TYPE = " + image.getMimetypeOriginal());
        try {
            InputStream is = uploadedFile.getInputstream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();

            image.setOriginal(buffer.toByteArray());
            System.out.println("TAILLE = " + image.getOriginal().length);
        } catch (IOException ex) {
            Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageService.create(image);
        System.out.println("ID = " + image.getId());

        return image;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurSession.getUtilisateur();
    }

    public Panier getPanierSite() {
        return panierSession.getPanier();
    }

    public void setPanierSite(Panier panier) {
        panierSession.setPanier(panier);
    }

    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        utilisateurSession.setUtilisateur(utilisateur);
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    public void setPanierSession(PanierSession panierSession) {
        this.panierSession = panierSession;
    }

    public void updatePanier() {
        panierService.edit(getUtilisateurConnecte().getPanier());
    }

    public void setPanierUtilisateur() {
        if (getUtilisateurConnecte().getPanier() == null || (getPanierSite().getEncheres().size() + getPanierSite().getProduits().size() != 0)) {
            if (getUtilisateurConnecte().getPanier() != null) {
                panierService.remove(getUtilisateurConnecte().getPanier());
            }
            getPanierSite().setUtilisateur(getUtilisateurConnecte());
            getUtilisateurConnecte().setPanier(getPanierSite());
            if(getPanierSite().getId() == null){
                System.out.println("CREATE");
                panierService.create(getPanierSite());
                utilisateurService.edit(getUtilisateurConnecte());
            }else{
                panierService.edit(getPanierSite());
            }
        } else if (getPanierSite().getEncheres().size() + getPanierSite().getProduits().size() == 0) {
            setPanierSite(getUtilisateurConnecte().getPanier());
        }
    }

    public void setPanierService(PanierService panierService) {
        this.panierService = panierService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
}
