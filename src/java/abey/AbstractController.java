package abey;

import abey.entities.Image;
import abey.entities.Utilisateur;
import abey.login.UtilisateurSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class AbstractController {

    @ManagedProperty(value = "#{utilisateurSession}")
    protected UtilisateurSession utilisateurSession;

    @EJB
    protected abey.services.ImageService imageService;

    private static final int sizeLimit = 100000;


    public Utilisateur getUtilisateurConnecte() {
        Utilisateur utilisateur = null;
        if (utilisateurSession != null) {
            utilisateur = utilisateurSession.getUtilisateur();
        }
        return utilisateur;
    }

    public void setUtilisateurSession(UtilisateurSession utilisateurSession) {
        this.utilisateurSession = utilisateurSession;
    }

    //TODO faire en sorte que cette méthode soit dans un @ViewScoped
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("AbstractController.getImage()");
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.

            String idParam = context.getExternalContext().getRequestParameterMap().get("imageId");
            String thumbParam = context.getExternalContext().getRequestParameterMap().get("thumb");

            Image image = null;
            System.out.println("idParam = "+idParam);
            System.out.println("thumbParam = "+thumbParam);
            try {
                image = imageService.find(Long.valueOf(idParam));
            } catch (NumberFormatException e) {
                Logger.getLogger(AbstractController.class.getName()).log(Level.SEVERE, null, e);
            }

            if (image == null) {
                return null;
            }

            if (Boolean.parseBoolean(thumbParam)) {
                return new DefaultStreamedContent(new ByteArrayInputStream(image.getThumbnail()), image.getMimetypeThumbnail());
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(image.getOriginal()), image.getMimetypeOriginal());
            }
        }

    }

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
}
