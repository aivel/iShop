package root.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 *
 * @author Max
 */
public class Utils {
    static private ResourceBundle resourceBundle;
    
    static public Locale stringToLocale(final String locStr)  {
        if (locStr == null)
            return new Locale("RU");
        
        StringTokenizer tempStringTokenizer = new StringTokenizer(locStr, "_");
        String l = null;
        String c = null;
        
        if (tempStringTokenizer.hasMoreTokens())
            l = (String) tempStringTokenizer.nextElement();
        
        if (tempStringTokenizer.hasMoreTokens())
            c = (String) tempStringTokenizer.nextElement();

        if (l == null)
            return new Locale(c);

        if (c == null)
            return new Locale(l);

        return new Locale(l, c);
    }

    static public Locale fulfillLocale(final Locale locale) {
        String l = locale.getLanguage();
        String c = locale.getCountry();

        if (l == null || l.equals("") && c != null && !c.equals(""))
            l = c.toLowerCase();

        if (c == null || c.equals("") && l != null && !l.equals(""))
            c = l.toLowerCase();

        if ((c == null || c.equals("")) && (l == null || l.equals("")))
            throw new NullPointerException("Can't lengthen empty locale!");

        return new Locale(l, c);
    }
    
    static public ResourceBundle getResourceBundle(final HttpServletRequest request) {
        if (resourceBundle == null ||
                (request.getSession()!= null && 
                !resourceBundle.getLocale().equals(
                    stringToLocale((String)request.getSession().getAttribute("currentLocale"))))) {

            final Locale locale = fulfillLocale(request.getSession() != null ?
                    Utils.stringToLocale((String) request.getSession().getAttribute("currentLocale")) :
                    request.getLocale());

            resourceBundle = ResourceBundle.getBundle("root.i18n.lang", locale);
        }
        
        if (resourceBundle == null)
            throw new NullPointerException("ResourceBundle doesn't exist!");

        return resourceBundle;
    }

    static public String convertLocaleToString(final Locale locale) {
        return locale.toString();
    }
}
