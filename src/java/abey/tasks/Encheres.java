package abey.tasks;

import abey.entities.Enchere;
import abey.entities.Surenchere;
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

    @Schedule(second = "*/15", hour = "*", minute = "*")
    public void task() {
        System.out.println(new Date());
        System.out.println("Vérification des enchères à clore...");
        for (Enchere enchere : enchereService.getEncheresAClore()) {
            System.out.println("à clore : enchere " + enchere.getId() + " (produit " + enchere.getProduit().getNom() + ")");

            Surenchere gagnante = enchere.getDerniereSurenchere();
            if (gagnante != null) {
                System.out.println("surenchere gagnante : " + gagnante);
                enchere.setSurenchereGagnante(gagnante);
            } else {
                System.out.println("aucune surenchere");
            }
            enchere.setTerminee(true);
            enchereService.edit(enchere);
            System.out.println("enchère close");
        }
        System.out.println("Fin de vérification");
    }

    public void setEnchereService(EnchereService enchereService) {
        this.enchereService = enchereService;
    }

}
