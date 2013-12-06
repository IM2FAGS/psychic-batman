package abey.services;

import abey.entities.Categorie;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class CategorieService extends AbstractService<Categorie> {

	public CategorieService() {
		super(Categorie.class);
	}
	
}
