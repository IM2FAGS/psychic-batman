package abey.services;

import abey.entities.EnchereGagnee;
import javax.ejb.Stateless;

/**
 *
 * @author disavinr
 */
@Stateless
public class EnchereGagneeService extends AbstractService<EnchereGagnee> {

    public EnchereGagneeService() {
        super(EnchereGagnee.class);
    }

}
