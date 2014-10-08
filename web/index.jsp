<%-- 
    Document   : index.jsp
    Created on : Sep 19, 2014, 12:17:31 AM
    Author     : max
--%>
<%@page isELIgnored="false"%>
<%@page import="java.util.List"%>
<%@page import="root.db.dao.Factory"%>
<%@page import="root.db.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
    <script src="<c:url value="resources/js/ajax.js" />"></script>
    <title>Bookstore</title>
</head>
<body>
    <%@include file="parts/commonHeader.jsp" %>
    <main class="container">
        <%--<div><h2>Available books:</h2></div>--%>
        <br/>
        
        <div id="productsList">
            <% request.setAttribute("books", Factory.getInstance().getBookDAO().getAllBooks()); %>
            <%@include file="parts/productsList.jsp" %>
        </div>
    </main>
    <%@include file="parts/commonFooter.jsp" %>
</body>
</html>
