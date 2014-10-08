package root.servlet;

import root.db.dao.Factory;
import root.db.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Max on 9/27/2014.
 */
public class ProductDescription extends javax.servlet.http.HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession(true);

        try {
            final Long id = Long.parseLong(request.getParameter("id"));

            final Book book = (Book) Factory.getInstance().getBookDAO().getBookById(id);

            if (book == null)
                throw new Exception("Page not found!");

            request.setAttribute("pageTitle", "Bookstore - " + book.getTitle());
            request.setAttribute("productPhoto", book.getCoverUrl());
            request.setAttribute("productTitle", book.getTitle());
            request.setAttribute("productPrice", book.getPrice());
            request.setAttribute("productShortDescription", "Book's short description.");

            request.getRequestDispatcher("/product.jsp").forward(request, response);

        } catch (Exception ex) {
            request.getRequestDispatcher("/e404.html").forward(request, response);
            Logger.getLogger(ProductDescription.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }
}
