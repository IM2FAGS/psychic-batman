package abey.services;

import abey.entities.Panier;
import javax.ejb.Stateless;

/**
 *
 * @author disavinr
 */
@Stateless
public class PanierService extends AbstractService<Panier> {

    public PanierService() {
        super(Panier.class);
    }

}
