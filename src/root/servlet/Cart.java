package root.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Max on 9/28/2014.
 */
public class Cart extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession(true);

        if (session.isNew()) {
            final root.db.model.Cart cart =  new root.db.model.Cart(session.getAttribute("currentLocale") != null
                    ? (String) session.getAttribute("currentLocale")
                    : request.getLocale().toString());

            session.setAttribute("cart", cart);
        }

        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
