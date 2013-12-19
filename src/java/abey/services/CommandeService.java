package abey.services;

import abey.entities.Commande;
import javax.ejb.Stateless;

/**
 *
 * @author disavinr
 */
@Stateless
public class CommandeService extends AbstractService<Commande> {

    public CommandeService() {
        super(Commande.class);
    }

}
