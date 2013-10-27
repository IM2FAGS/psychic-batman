/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abey;

import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author toinou
 */
@Named("datePick")
@SessionScoped
public class DatePick implements Serializable {

    private Integer day;
    private Integer month;
    private Integer year;

    public ArrayList<Integer> getDays() {
        ArrayList<Integer> l;
        l = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            l.add(i);
        }
        return l;
    }

    public ArrayList<Integer> getMonths() {
        ArrayList<Integer> l;
        l = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            l.add(i);
        }
        return l;
    }

    public ArrayList<Integer> getYears() {
        ArrayList<Integer> l;
        l = new ArrayList<>();
        for (int i = 2013; i > 1900; i--) {
            l.add(i);
        }
        return l;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}