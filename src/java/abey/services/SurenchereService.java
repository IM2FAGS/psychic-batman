package abey.services;

import abey.entities.Surenchere;
import javax.ejb.Stateless;

/**
 *
 * @author nicolas
 */
@Stateless
public class SurenchereService extends AbstractService<Surenchere> {

    public SurenchereService() {
        super(Surenchere.class);
    }
    
}
