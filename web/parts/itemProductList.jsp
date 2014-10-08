<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
    Document   : itemProductList
    Created on : Sep 19, 2014, 1:48:54 AM
    Author     : max
--%>
<%@page isELIgnored="false"%>
<%@page import="root.utils.Utils"%>
<%@page import="java.util.ResourceBundle"%>
<div class="bordered">
    <div class="row">
        <img class="little-cover floating-block-left" src="${book.coverUrl}"/>
        <div class="floating-block-left">
            <span><a href="/product?id=${book.id}">${book.title}</a></span>
            <h4>${book.authorName}</h4>
            <h5><%=Utils.getResourceBundle(request).getString("PRICE")%>:
                <span id="product-price">
                    <fmt:setLocale value="${book.priceLocale}"/>
                    <fmt:formatNumber value="${book.price}" type="currency"/>
                    <fmt:setLocale value="${currentLocale}"/>
                </span>
            </h5>
        </div>
        <div class="floating-block-right margin-top-5">
            <a class="button" href="javascript: addToCart(1, '${book.id}');"><%=Utils.getResourceBundle(request).getString("ADD_TO_CART")%></a>
        </div>
    </div>
</div>