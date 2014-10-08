<%-- 
    Document   : product
    Created on : Sep 11, 2014, 5:59:21 AM
    Author     : Max
--%>
<%@page isELIgnored="false"%>
<%@page import="root.db.dao.Factory"%>
<%@page import="root.db.model.Book"%>
<%@page import="root.utils.Utils"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ResourceBundle" %>

<c:if test="${lang==null}">
 <fmt:setBundle basename="co" var="lang" scope="session"/>
</c:if>
<%@page language="java" %>
<%
    String currentTab = request.getSession()!= null && request.getSession().getAttribute("currentTab") != null
            ? (String) request.getSession().getAttribute("currentTab")
            : session.getServletContext().getInitParameter("defaultTab");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, inital-scale=1.0">
        <link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
        <script src="<c:url value="resources/js/ajax.js" />"></script>
        <script>
            window.onload = function () {
                setContent('<%=currentTab%>');
            };
        </script>
        <title><%=request.getAttribute("pageTitle")%></title>
    </head>
    <body>
        <%@include file="parts/commonHeader.jsp" %>
        <main class="container">
            <div id="base-info" class="row">
                <div class="cols col-4-1">
                    <img id="product-photo" src="<%=request.getAttribute("productPhoto")%>">
                </div>
                <div class="cols col-4-3" id="product-short-description-col">
                    <h2 id="product-title"><%=request.getAttribute("productTitle")%></h2>
                    <h4>
                        <%=Utils.getResourceBundle(request).getString("PRICE")%>:
                        <span id="product-price">
                            <fmt:setLocale value="${product.priceLocale}"/>
                            <fmt:formatNumber value="${requestScope.productPrice}" type="currency"/>
                            <fmt:setLocale value="${sessionScope.currentLocale}"/>
                        </span>
                    </h4>
                    <br/>
                    <a id="btnAddToCart" class="bordered button" href="javascript: addToCart(1);"><%=Utils.getResourceBundle(request).getString("ADD_TO_CART")%></a>
                    <p id="product-short-description">
                        <%=request.getAttribute("productShortDescription")%>
                    </p>
                </div>
            </div>
            <div id="tabbed-pane" class="clear">
                <ul id="tabs" class="row">
                    <li><a href="javascript: setContent('reviews');"><%=Utils.getResourceBundle(request).getString("REVIEWS")%></a></li>
                    <li><a href="javascript: setContent('comments');"><%=Utils.getResourceBundle(request).getString("COMMENTS")%></a></li>
                </ul>
                <div id="content">
                </div>
            </div>
        </main>
        <%@include file="parts/commonFooter.jsp" %>
    </body>
</html>
