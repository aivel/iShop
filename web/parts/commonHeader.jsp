<%--
    Document   : head
    Created on : Sep 19, 2014, 12:10:26 AM
    Author     : max
--%>
<%@page isELIgnored="false"%>
<%@page import="root.utils.Utils"%>
<%@page import="java.util.ResourceBundle"%>

<header class="menu-bar">
    <div class="container">
        <div class="row">
            <div class="floating-block-left">
                <ul class="topNavButtons">
                    <li><a href="/"><%=Utils.getResourceBundle(request).getString("MAIN_PAGE")%></a></li>
                    <c:if test="${auth eq true}">
                        <li><a href="/unauth">E&XIT</a></li>
                    </c:if>
                    <li><a href="/auth"><%=Utils.getResourceBundle(request).getString("SIGN_IN")%></a></li>
                    <li><a href="/cart"><%=Utils.getResourceBundle(request).getString("CART")%></a></li>
                    <li><a href="#"><%=Utils.getResourceBundle(request).getString("SHOPPING_HISTORY")%></a></li>
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