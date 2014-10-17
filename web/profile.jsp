<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10/8/2014
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${currentLocale}"/>
<fmt:setBundle basename="root.i18n.lang" var="lang"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
    <script src="<c:url value="resources/js/ajax.js" />"></script>
    <title>Bookstore - Profile</title>
</head>
<body>
<%@include file="parts/commonHeader.jsp"%>
<main class="container">
    <c:if test="${auth eq true}">
        <p><b><fmt:message key="USERNAME" bundle="${lang}"/>:</b> ${user.username}</p>
        <p><b><fmt:message key="DEFAULT_TAB" bundle="${lang}"/>:</b> ${defaultTab}</p>
        <fmt:setLocale value="${user.balanceLocale}"/>
        <p><b><fmt:message key="BALANCE" bundle="${lang}"/>:</b> <fmt:formatNumber value="${user.balance}" type="currency"/></p>
        <fmt:setLocale value="${currentLocale}"/>
    </c:if>
    <br/>
    <%@include file="parts/ordersList.jsp"%>
</main>
<%@include file="parts/commonFooter.jsp"%>
</body>
</html>
