package abey.services;

import abey.entities.Achat;
import javax.ejb.Stateless;

/**
 *
 * @author Anthony
 */
@Stateless
public class AchatService extends AbstractService<Achat> {

    public AchatService() {
        super(Achat.class);
    }

}
