package abey.services;

import abey.entities.Image;
import javax.ejb.Stateless;

/**
 *
 * @author Anthony
 */
@Stateless
public class ImageService extends AbstractService<Image> {

	public ImageService() {
		super(Image.class);
	}

}
