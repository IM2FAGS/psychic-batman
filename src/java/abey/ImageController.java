package abey;

import abey.entities.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Anthony
 */
@ManagedBean
@RequestScoped
public class ImageController extends AbstractController {

    @ManagedProperty("#{param.imageId}")
    private long imageId;
    @ManagedProperty("#{param.thumb}")
    private boolean thumb;

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public boolean isThumb() {
        return thumb;
    }

    public void setThumb(boolean thumb) {
        this.thumb = thumb;
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            Image image = imageService.find(imageId);
            if (image == null) {
                return null;
            } else if (thumb) {
                return new DefaultStreamedContent(new ByteArrayInputStream(image.getThumbnail()), image.getMimetypeThumbnail());
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(image.getOriginal()), image.getMimetypeOriginal());
            }
        }

    }

}
