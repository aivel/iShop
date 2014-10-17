package root.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Max on 9/28/2014.
 */
public class Auth extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        final String username = request.getParameter("j_username");
//        final String password = request.getParameter("j_password");
//
//        if (username != null && password != null) {
//            CallBackHandlerImpl callBackHandler = new CallBackHandlerImpl(username, password);
//            Utils.request = request;
//            boolean authed = true;
//
//            try {
//                LoginContext loginContext  = new LoginContext("iShop", callBackHandler);
//
//                loginContext.login();
//            } catch (LoginException e) {
//                authed = false;
//                e.printStackTrace();
//            }
//
//            if (authed) {
//                final String url = (String) request.getSession().getAttribute("requestURL");
//
//                if (url.startsWith("/auth")) {
//                    request.getRequestDispatcher("/").forward(request, response);
//                    return;
//                }
//
//                response.sendRedirect("/");
//            } else {
//                response.sendRedirect("/login/login_failed.jsp");
//            }
//        } else {
//            if (request.getRequestURI().startsWith("/unauth")) {
//                request.getSession().setAttribute("auth", false);
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath().concat("/"));
//            } else {
//                request.getSession().setAttribute("requestURL", request.getRequestURI());
//                request.getRequestDispatcher("/auth.jsp").forward(request, response);
//            }
//        }
//        request.getRequestDispatcher("/auth.jsp").forward(request, response);
        if (request.getRequestURI().startsWith("/auth"))
                request.getRequestDispatcher("/auth.jsp").forward(request, response);
        else {
            request.getSession().setAttribute("auth", false);
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath().concat("/"));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
