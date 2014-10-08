package root.filter;

import root.db.model.Cart;
import root.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Max on 10/3/2014.
 */
public class CommonFilter implements Filter {
    private  ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
    }

    private void doFilter(final HttpServletRequest request, final HttpServletResponse response,
                          final FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        if (session.isNew()) {
            session.setAttribute("currentLocale", request.getLocale().toString());
            session.setAttribute("currentTab", context.getInitParameter("defaultTab"));
        }

        if (session.getAttribute("cart") != null)
            ((Cart)session.getAttribute("cart")).setCurrentLocale(
                    Utils.fulfillLocale(
                            Utils.stringToLocale((String) session.getAttribute("currentLocale"))
                    ).toString());

        String localeString = (String) session.getAttribute("currentLocale");

        if (localeString != null) {
            Locale locale = Utils.fulfillLocale(Utils.stringToLocale(localeString));
            request.setAttribute("currentLocale", locale);
        }

        if (request.isUserInRole("user"))
            session.setAttribute("auth", true);
        else
            request.setAttribute("auth", false);

        filterChain.doFilter(request, response);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);

    }

    @Override
    public void destroy() {

    }
}
