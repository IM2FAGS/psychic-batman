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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@SessionScoped
public class CategorieController extends AbstractController {

    private Categorie current;
    @EJB
    private CategorieService categorieService;

    public CategorieController() {
    }

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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoriesDeleted"));
            return "List";
        } catch (Exception e) {
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

    public List<Categorie> getAllOrdered() {
        return categorieService.findAllOrderedByName();
    }

    @FacesConverter(forClass = Categorie.class)
    public static class CategoriesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategorieController controller = (CategorieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categorieController");
            return controller.getCategorie(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Categorie) {
                Categorie o = (Categorie) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categorie.class.getName());
            }
        }
    }
}
