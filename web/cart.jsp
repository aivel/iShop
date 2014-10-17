<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 9/28/2014
  Time: 12:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="root.i18n.lang" var="lang"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
    <script src="<c:url value="resources/js/ajax.js" />"></script>
    <title>Bookstore - Cart</title>
</head>
<body>
    <%@include file="parts/commonHeader.jsp" %>
    <main id="container" class="container">
        <c:if test="${cart.notEmpty}">
            <h2><fmt:message key="CART" bundle="${lang}"/>:</h2>
            <br/>
            <c:forEach items="${cart.products}" var="product">
                <div class="bordered row">
                    <img class="little-cover floating-block-left" src="${product.coverUrl}"/>
                    <div class="floating-block-left">
                        <span><a href="/product?id=${product.id}">${product.title}</a></span>
                        <h4>${product.authorName}</h4>
                        <h5><fmt:message key="PRICE" bundle="${lang}"/>:
                            <span id="product-price">
                                <fmt:setLocale value="${product.priceLocale}"/>
                                <fmt:formatNumber value="${product.price}" type="currency"/>
                                <fmt:setLocale value="${currentLocale}"/>
                            </span>
                        </h5>
                        <h5><fmt:message key="AMOUNT" bundle="${lang}"/>: ${product.amount}</h5>
                    </div>
                </div>
            </c:forEach>
            <br/>
            <div class="floating-block-right">
                <span>
                    <fmt:message key="TOTAL_PRICE" bundle="${lang}"/>:
                        <fmt:setLocale value="${currentLocale}"/>
                        <fmt:formatNumber value="${cart.totalPrice}" type="currency"/>
                    </span>
                </span>
                <a class="button" href="/order">
                    <fmt:message key="ORDER" bundle="${lang}"/>
                </a>
            </div>
        </c:if>
    </main>
    <%@include file="parts/commonFooter.jsp" %>
</body>
</html>
