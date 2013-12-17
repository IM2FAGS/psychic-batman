package abey;

import abey.entities.Image;
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
    protected abey.services.ImageService imageService;

    @EJB
    private PanierService panierService;
    
    @EJB
    private UtilisateurService utilisateurService;
    
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

    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        utilisateurSession.setUtilisateur(utilisateur);
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    public void setPanierSession(PanierSession panierSession) {
        this.panierSession = panierSession;
    }
    
    public void setPanierService(PanierService panierService) {
        this.panierService = panierService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    
    public int getNumProduits() {
        return panierSession.getPanier().getProduits().size();
    }

    public int getNumEncheres() {
        return panierSession.getPanier().getEncheres().size();
    }

    public void updatePanier() {
        panierService.edit(panierSession.getPanier());
    }
    
    public void setPanierUtilisateur(){
        if(utilisateurSession.getUtilisateur().getPanier() == null || (panierSession.getPanier().getEncheres().size()+panierSession.getPanier().getProduits().size() != 0)){
            utilisateurSession.getUtilisateur().setPanier(panierSession.getPanier());
            if(utilisateurSession.getUtilisateur().getPanier() != null){
                panierService.remove(utilisateurSession.getUtilisateur().getPanier());
            }
            panierService.create(panierSession.getPanier());
            utilisateurService.edit(utilisateurSession.getUtilisateur());
        }else if(panierSession.getPanier().getEncheres().size()+panierSession.getPanier().getProduits().size() == 0){
            panierSession.setPanier(utilisateurSession.getUtilisateur().getPanier());
        }
    }
}
