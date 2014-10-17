<%--
    Document   : head
    Created on : Sep 19, 2014, 12:10:26 AM
    Author     : max
--%>
<%@page isELIgnored="false"%>
<%@page import="root.utils.Utils"%>
<%@page import="java.util.ResourceBundle"%>
<jsp:useBean id="cart" scope="session" class="root.db.model.Cart" />

<header class="menu-bar">
    <div class="container">
        <div class="row">
            <div class="floating-block-left">
                <ul class="topNavButtons">
                    <c:if test="${auth eq true}">
                        <li><%=Utils.getResourceBundle(request).getString("USERNAME")%>: <i>${userName}</i></li>
                    </c:if>
                    <li><a href="/"><%=Utils.getResourceBundle(request).getString("MAIN_PAGE")%></a></li>
                    <c:if test="${auth eq false}">
                        <li><a href="/iauth"><%=Utils.getResourceBundle(request).getString("SIGN_IN")%></a></li>
                    </c:if>
                    <li><a href="/cart"><%=Utils.getResourceBundle(request).getString("CART")%></a></li>
                    <c:if test="${auth eq true and cart.notEmpty}">
                        <li><a href="/order"><%=Utils.getResourceBundle(request).getString("ORDER")%></a></li>
                    </c:if>
                    <c:if test="${auth eq true}">
                        <li><a href="/profile"><%=Utils.getResourceBundle(request).getString("PROFILE")%></a></li>
                    </c:if>
                    <c:if test="${auth eq true}">
                        <li><a href="/unauth"><%=Utils.getResourceBundle(request).getString("SIGN_OUT")%></a></li>
                    </c:if>
                </ul>
            </div>
                <input class="filter-input floating-block-left" onchange="filterProducts(this.value)"/>
            <div class="lang-bar">
                <a href="javascript: setLocale('ru_RU');">
                    <img src="resources/img/ru.png" class="flag-icon"/>
                </a>
                <a href="javascript: setLocale('en_US');">
                    <img src="resources/img/us.png" class="flag-icon"/>
                </a>
                <a href="javascript: setLocale('es_ES');">
                    <img src="resources/img/es.png" class="flag-icon"/>
                </a>
            </div>
        </div>
    </div>
</header>