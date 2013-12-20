package abey;

import abey.entities.Surenchere;
import abey.services.SurenchereService;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Anthony
 */
@ManagedBean
@ViewScoped
public class LireSurenchereControler extends AbstractController {

    @EJB
    SurenchereService surenchereService;

    public String setSurenchereConsultee(Surenchere s, boolean consultee) {
        s.setConsultee(consultee);
        surenchereService.edit(s);
        
        return null;
    }
}
