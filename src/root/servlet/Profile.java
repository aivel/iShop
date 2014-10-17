package root.servlet;

import root.db.dao.Factory;
import root.db.model.User;
import root.db.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Max on 10/8/2014.
 */
public class Profile extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if ((Boolean)request.getSession(true).getAttribute("auth")) {
                final HttpSession session = request.getSession(true);
                final User user = (User)session.getAttribute("user");
                final List<Order> orders = Factory.getInstance().getOrderDAO().getAllOrdersByBuyerId(user.getId());

                request.setAttribute("orders", orders);

                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
