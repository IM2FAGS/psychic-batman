package abey.util;

/**
 *
 * @author nicolas
 */
public class LangString {
    
    public static String params(String str, String... params) {
        for (int i = 0; i < params.length; i++) {
            str = str.replace("{" + i + "}", params[i]); // probleme de securite !!
        }
        return str;
    }
    
}
