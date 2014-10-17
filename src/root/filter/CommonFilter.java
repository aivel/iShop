package root.filter;

import root.db.dao.Factory;
import root.db.model.Cart;
import root.db.model.User;
import root.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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
        final HttpSession session = request.getSession(true);

        if (session.isNew()) {
            final String localeString = Utils.fulfillLocale(request.getLocale()).toString();
            session.setAttribute("currentLocale", localeString);
            session.setAttribute("currentTab", context.getInitParameter("defaultTab"));
            session.setAttribute("defaultTab", context.getInitParameter("defaultTab"));
        }

        final String localeString = Utils.fulfillLocale(Utils.stringToLocale((String) session.getAttribute("currentLocale"))).toString();

        if (session.getAttribute("cart") != null) {
            ((Cart)session.getAttribute("cart")).setCurrentLocale(localeString);
        }

        if (localeString != null) {
            Locale locale = Utils.fulfillLocale(Utils.stringToLocale(localeString));
            request.setAttribute("currentLocale", locale);
        }

        if (request.isUserInRole("user")) {
            session.setAttribute("auth", true);
            final String username = request.getUserPrincipal().getName();
            session.setAttribute("userName", username);

            User user = null;

            try {
                user = Factory.getInstance().getUserDAO().getUserByName( username );
            } catch (SQLException e) {
                e.printStackTrace();
            }

            session.setAttribute("user", user);
        } else {
            request.setAttribute("auth", false);
            session.setAttribute("userName", "");
        }

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
