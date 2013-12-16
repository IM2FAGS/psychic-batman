package abey.services;

import abey.entities.Categorie;
import javax.ejb.Stateless;

@Stateless
public class CategorieService extends AbstractService<Categorie> {

    public CategorieService() {
        super(Categorie.class);
    }
}
