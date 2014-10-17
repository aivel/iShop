package root.servlet;

import root.db.dao.Factory;
import root.db.model.*;
import root.db.model.Cart;
import root.db.model.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Max on 9/27/2014.
 */
public class Ajax extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String req = request.getParameter("req");

        try {
            switch (req) {
                case "comments": {
                    final long id = Long.parseLong(request.getParameter("id"));
                    final List<Comment> comments = Factory.getInstance().getCommentDAO().getAllCommentsByProductId(id);

                    final List<String> usernames = new ArrayList<>();
                    final List<String> commentTexts = new ArrayList<>();
                    final List<Long> timestamps = new ArrayList<>();

                    for (final Comment comment: comments) {
                        usernames.add(Factory.getInstance().getUserDAO().getUserById(comment.getAuthorId()).getUsername());
                        commentTexts.add(comment.getText());
                        timestamps.add(comment.getTimestamp());
                    }

                    request.getSession(true).setAttribute("currentTab", "comments");

                    request.setAttribute("usernames", usernames);
                    request.setAttribute("commentTexts", commentTexts);
                    request.setAttribute("timestamps", timestamps);

                    request.getRequestDispatcher("/comments.jsp").forward(request, response);
                    break;
                }
                case "reviews": {
                    //final long id = Long.parseLong(request.getParameter("id"));
                    request.getSession(true).setAttribute("currentTab", "reviews");

                    response.getWriter().println("Reviews: " + new Date().getTime());

                    request.getRequestDispatcher("/reviews.jsp").forward(request, response);
                    break;
                }
                case "locale": {
                    final HttpSession session = request.getSession(true);
                    session.setAttribute("currentLocale", request.getParameter("type"));
                    if (session.getAttribute("cart") != null)
                        ((Cart)session.getAttribute("cart")).setCurrentLocale((String) session.getAttribute("currentLocale"));
                    break;
                }
                case "filter": {
                    request.getRequestDispatcher("/parts/productsList.jsp").forward(request, response);
                    break;
                }
                case "order": {
                    final long id = Long.parseLong(request.getParameter("id"));
                    final HttpSession session = request.getSession(true);

                    Cart cart;

                    if (session.isNew()) {
                        cart = new Cart(session.getAttribute("currentLocale") != null
                                ? (String) session.getAttribute("currentLocale")
                                : request.getLocale().toString());
                        session.setAttribute("cart", cart);
                    } else {
                        cart = (Cart) session.getAttribute("cart");

                        if (cart == null)
                            cart = new Cart(session.getAttribute("currentLocale") != null
                                    ? (String) session.getAttribute("currentLocale")
                                    : request.getLocale().toString());

                        session.setAttribute("cart", cart);
                    }

                    final long amount = Long.parseLong(request.getParameter("amount"));
                    final Book book = Factory.getInstance().getBookDAO().getBookById(id);

                    if (book != null)
                        cart.addProduct(id, amount, book.getTitle(), book.getAuthorName(),
                                book.getCoverUrl(), book.getPrice(), book.getPriceLocale());
                    break;
                }
                case "makeOrder": {
                    final HttpSession session = request.getSession(true);
                    final Cart cart = (Cart)session.getAttribute("cart");
                    final User user = (User) session.getAttribute("user");
                    final List<Product> products = cart.getProducts();
                    final Boolean courier = Boolean.parseBoolean(request.getParameter("courier"));
                    final String deliveryAddress = request.getParameter("deliveryAddress");
                    final Long when = System.currentTimeMillis();
                    final Order order = new Order();

                    long totalAmount = 0;

                    for (final Product product: products) {
                        totalAmount += product.getAmount();
                    }

                    order.setAmount(totalAmount);
                    order.setBuyerId(user.getId());
                    order.setCost(cart.getTotalPrice());
                    order.setCourier(courier);
                    order.setDeliveryAddress(deliveryAddress);
                    order.setWhenOrdered( when );
                    order.setCostLocale( cart.getCurrentLocale() );

                    Factory.getInstance().getOrderDAO().addOrder(order);

                    cart.clear();
                    break;
                }
                default:
                    break;
            }
        } catch (SQLException se) {

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
