package abey.services;

import abey.entities.Panier;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author disavinr
 */
@Stateless
public class PanierService extends AbstractService<Panier> {

    public PanierService() {
        super(Panier.class);
    }

    public List<Panier> getCoupsDeCoeur() {
        return findAll();
    }

}
