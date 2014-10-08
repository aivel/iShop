<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 9/28/2014
  Time: 3:37 AM
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
    <title>Bookstore - Sign in</title>
</head>
<body>
    <%@include file="parts/commonHeader.jsp"%>
    <main class="container">
        <form method="post" action="j_security_check">
            <table>
                <tr>
                    <td>
                        <fmt:message key="USERNAME" bundle="${lang}"/>:
                    </td>
                    <td>
                        <input name="j_username"/> <br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="PASSWORD" bundle="${lang}"/>:
                    </td>
                    <td>
                        <input name="j_password" type="password"/> <br/>
                    </td>
                </tr>
            </table>
            <input type="submit" value='<fmt:message key="ENTER" bundle="${lang}"/>'/>
        </form>
    </main>
    <%@include file="parts/commonFooter.jsp"%>
</body>
</html>
