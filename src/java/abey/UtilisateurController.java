package abey;

import abey.entities.Utilisateur;
import abey.facades.UtilisateurFacade;
import abey.util.JsfUtil;
import abey.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean
@Named("utilisateurController")
@ViewScoped
public class UtilisateurController implements Serializable {

    private Utilisateur current;
    private DataModel items = null;
    @EJB
    private abey.facades.UtilisateurFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UtilisateurController() {
    }

    public Utilisateur getSelected() {
        if (current == null) {
            current = new Utilisateur();
            selectedItemIndex = -1;
        }
        return current;
    }

    private UtilisateurFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Utilisateur) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Utilisateur();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.setSalt("salt");
            System.out.println("eiofzjqmifmezoiqjf");
            getFacade().create(current);
            System.out.println("reussite");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UtilisateursCreated"));
            return prepareCreate();
//            System.out.println("Coucou les amis");
//            Calendar cal = Calendar.getInstance();
//            cal.set(date.getYear(), date.getMonth()-1, date.getDay());
//            Date d = cal.getTime();
//            if (cal.get(Calendar.DAY_OF_MONTH) != date.getDay()) {
//                System.out.println("date invalide");
//                JsfUtil.addErrorMessage("Date invalide");
//                return null;
//            } else {
//                System.out.println("" + d);
//                current.setDateNaissance(d);
//                current.setSalt("salt");
//                getFacade().create(current);
//                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UtilisateursCreated"));
//                return prepareCreate();
//            }
        } catch (Exception e) {
            System.out.println("LABITE");
            JsfUtil.addErrorMessage("Le nom d'utilisateur exite deja");//ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Utilisateur) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UtilisateursUpdated"));
            return "View";
        } catch (Exception e) {
            System.out.println("prout");
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Utilisateur) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UtilisateursDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Utilisateur getUtilisateurs(java.lang.Long id) {
        return ejbFacade.find(id);
    }
    


    @FacesConverter(forClass = Utilisateur.class)
    public static class UtilisateursControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UtilisateurController controller = (UtilisateurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "utilisateursController");
            return controller.getUtilisateurs(getKey(value));
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
            if (object instanceof Utilisateur) {
                Utilisateur o = (Utilisateur) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Utilisateur.class.getName());
            }
        }
    }
}