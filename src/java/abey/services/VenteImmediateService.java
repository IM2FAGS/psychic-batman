package abey.services;

import abey.entities.VenteImmediate;
import javax.ejb.Stateless;

/**
 *
 * @author nicolas
 */
@Stateless
public class VenteImmediateService extends AbstractService<VenteImmediate> {

    public VenteImmediateService() {
        super(VenteImmediate.class);
    }
    
}
