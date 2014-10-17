<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10/12/2014
  Time: 12:15 AM
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
  <link href="<c:url value="../resources/css/style.css"/>" rel="stylesheet">
  <script src="<c:url value="../resources/js/ajax.js" />"></script>
  <script>
    window.onload = function () {
      window.location = '/';
    }
  </script>
  <title>Bookstore - Sign in</title>
</head>
<body>
<%@include file="../parts/commonHeader.jsp"%>
<main class="container">
  <h2>Log in..</h2>
</main>
<%@include file="../parts/commonFooter.jsp"%>
</body>
</html>