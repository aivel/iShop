<%-- 
    Document   : productsList
    Created on : Sep 26, 2014, 4:20:55 PM
    Author     : max
--%>
<%@page isELIgnored="false"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="root.utils.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="root.db.dao.Factory"%>
<%@page import="java.util.List"%>
<%@page import="root.db.model.Book"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    final Cookie[] cookies = request.getCookies();
    String filterFromCookies = null;

    if (cookies != null)
        for (int i = 0; i < cookies.length; i++)
            if (cookies[i].getName().equals("filter")) {
                filterFromCookies = URLDecoder.decode(cookies[i].getValue(), "UTF-8");;
                break;
            }

    final String by = request.getParameter("by") != null
            ? request.getParameter("by").toLowerCase()
            : filterFromCookies != null ? filterFromCookies.toLowerCase() : null;


    List<Book> allBooks = Factory.getInstance().getBookDAO().getAllBooks();
    List<Book> books;

    if (by == null)
        books = allBooks;
    else {
        response.addCookie(new Cookie("filter", URLEncoder.encode(by, "UTF-8")));
        books = new ArrayList<Book>();

        for(Book book: allBooks)
            if (book.getAuthorName().toLowerCase().contains(by) ||
                book.getTitle().toLowerCase().contains(by))
                    books.add(book);
    }

    request.setAttribute("books", books);
%>
<div>
<c:forEach items="${books}" var="book">
    <%@include file="itemProductList.jsp" %>
</c:forEach>
<div class="floating-block-right margin-top-5">
    <a class="button" href="/cart"><%=Utils.getResourceBundle(request).getString("ORDER")%></a>
</div>
</div>