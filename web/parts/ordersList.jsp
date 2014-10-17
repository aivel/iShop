<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10/16/2014
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
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
<div>
  <c:forEach items="${orders}" var="order">
    <%@include file="itemOrdersList.jsp" %>
  </c:forEach>
</div>