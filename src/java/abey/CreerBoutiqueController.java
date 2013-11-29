/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import abey.entities.Boutique;
import abey.services.BoutiqueService;
import abey.util.JsfUtil;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author disavinr
 */
@ManagedBean
@SessionScoped
public class CreerBoutiqueController implements Serializable {

    private Boutique current;
    @ManagedProperty(value = "#{boutiqueService}")
    private BoutiqueService boutiqueService;


    public Boutique getSelected() {
        if (current == null) {
            current = new Boutique();
        }
        return current;
    }

    public String create() {
        try {
            boutiqueService.create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("BoutiquesCreated"));
            current=null;
            return "Create";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void setBoutiqueService(BoutiqueService boutiqueService) {
        this.boutiqueService = boutiqueService;
    }
    
    

}
