package abey.services;

import abey.entities.VenteImmediate;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author nicolas
 */
@Named("venteImmediateService")
@Stateless
public class VenteImmediateService extends AbstractService<VenteImmediate> {

    public VenteImmediateService() {
        super(VenteImmediate.class);
    }
    
}
