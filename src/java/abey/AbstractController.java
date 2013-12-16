package abey;

import abey.entities.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public abstract class AbstractController implements Serializable {

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
}
