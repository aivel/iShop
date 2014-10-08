<%-- 
    Document   : comments
    Created on : Sep 16, 2014, 12:51:18 AM
    Author     : Max
--%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="root.db.model.User"%>
<%@page import="root.db.model.Comment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" %>
<c:if test="${fn:length(usernames) > 0}">
    <div class="container">
    <c:forEach var="i" begin="0" end="${fn:length(usernames)-1}">
        <div class="row bordered">
            <span class="floating-block-left"><b><c:out value="${usernames[i]}"/></b></span>
                <jsp:useBean id="commentTimestamp" class="java.util.Date"/>
                <jsp:setProperty name="commentTimestamp" property="time" value="${timestamps[i]}"/>
            <span class="floating-block-right"><i><fmt:formatDate value="${commentTimestamp}"/></i></span>
            <br/>
            <div class="commentText"><c:out value="${commentTexts[i]}"/></div>
        </div>
    </c:forEach>
    </div>
</c:if>