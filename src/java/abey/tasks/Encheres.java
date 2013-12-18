package abey.tasks;

import abey.entities.Enchere;
import abey.entities.EnchereGagnee;
import abey.entities.ModePaiement;
import abey.entities.Surenchere;
import abey.services.EnchereGagneeService;
import abey.services.EnchereService;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author nicolas
 */
@Singleton
public class Encheres {

    @EJB
    EnchereService enchereService;

    @EJB
    private EnchereGagneeService enchereGagneeService;

    @Schedule(second = "*/15", hour = "*", minute = "*")
    public void task() {
        System.out.println(new Date());
        for (Enchere enchere : enchereService.getEncheresAClore()) {
            System.out.println(enchere);

            Surenchere gagne = enchere.getDerniereSurenchere();
            if (gagne != null) {
                EnchereGagnee eg = new EnchereGagnee();
                eg.setAcheteur(gagne.getEncherisseur());
                eg.setDateSurenchere(gagne.getDateEnchere());
                eg.setEnchere(enchere);
                eg.setModePaiement(ModePaiement.CB);//TODO mode paiement
                eg.setMontant(gagne.getMontant());

                enchereService.create(enchere);
                enchereGagneeService.create(eg);
                enchere.setEnchereGagnee(eg);
                enchereService.edit(enchere);
            } else {
                //TODO clore l'enchère sans enchérisseur
            }
        }

    }

    public void setEnchereService(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

}
