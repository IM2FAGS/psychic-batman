package abey.tasks;

import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author nicolas
 */

@Singleton
public class Encheres {
    
    @Schedule(second = "0", hour = "*", minute = "*")
    public void task() {
        System.out.println(new Date());
    }
    
}
