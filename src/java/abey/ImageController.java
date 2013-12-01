package abey;

import abey.entities.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Anthony
 */
@ManagedBean
@ApplicationScoped
public class ImageController implements Serializable {

	@EJB
	private abey.services.ImageService imageService;

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			// So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
			return new DefaultStreamedContent();
		} else {
			// So, browser is requesting the image. Return a real StreamedContent with the image bytes.

			String idParam = context.getExternalContext().getRequestParameterMap().get("imageId");
			String thumbParam = context.getExternalContext().getRequestParameterMap().get("thumb");

			Image image;

			try {
				image = imageService.find(Long.valueOf(idParam));
			} catch (NumberFormatException e) {
				return null;
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
}
