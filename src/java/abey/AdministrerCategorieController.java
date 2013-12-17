package abey;

import abey.entities.Categorie;
import abey.services.CategorieService;
import abey.util.JsfUtil;
import abey.util.LangString;
import java.util.List;

import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Deprecated
@ManagedBean
@SessionScoped
public class AdministrerCategorieController extends AbstractController {

    private Categorie current;
    @EJB
    private CategorieService categorieService;

    public Categorie getSelected() {
        if (current == null) {
            current = new Categorie();
        }
        return current;
    }

    public String edit(Categorie categorie) {
        current = categorie;
        return "Edit";
    }
    
    public String prepareCreate() {
        current = new Categorie();
        return "Create";
    }

    public String create() {
        try {
            categorieService.create(current);
            JsfUtil.addSuccessMessage(LangString.params(ResourceBundle.getBundle("/Bundle").getString("CategoryCreated"), current.getNom()));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryCreatedError"));
            return null;
        }
    }

    public String update() {
        try {
            categorieService.edit(current);
            JsfUtil.addSuccessMessage(LangString.params(ResourceBundle.getBundle("/Bundle").getString("CategoryUpdated"), current.getNom()));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryUpdatedError"));
            return null;
        }
    }

    public String destroy(Categorie categorie) {
        try {
            categorieService.remove(categorie);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryDeleted"));
            return "List";
        } catch (Exception e) {
            System.err.println(e);
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryDeletedError"));
            return null;
        }
    }

    public Categorie getCategorie(java.lang.Long id) {
        return categorieService.find(id);
    }

    public List<Categorie> getAll() {
        return categorieService.findAll();
    }

}