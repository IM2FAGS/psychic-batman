package abey;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LangueSession implements Serializable {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private static final Map<String, String> languages;

    static {
        languages = new LinkedHashMap<>();
        languages.put("Français", "fr");
        languages.put("English", "en");
        languages.put("Español", "es");
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = Locale.forLanguageTag(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public Map<String, String> getLanguagesAvailable() {
        return languages;
    }

    public String getLocaleDate(Date date) {
        if (date == null) {
            return null;
        } else {
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, locale);
            return df.format(date);
        }
    }
}
