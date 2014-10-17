<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 10/16/2014
  Time: 11:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<%@page import="root.utils.Utils"%>
<div class="bordered">
  <div class="row">
    <div class="floating-block-left">
      <span><fmt:message key="ORDER" bundle="${lang}"/> #${order.id}</span>
      <h4><fmt:message key="ADDRESS" bundle="${lang}"/>: ${order.deliveryAddress}</h4>
      <h4>
        <fmt:message key="DELIVERY_TYPE" bundle="${lang}"/>:
        <c:if test="${order.courier eq true}">
          <fmt:message key="DELIVERY_TYPE_COURIER" bundle="${lang}"/>
        </c:if>
        <c:if test="${order.courier eq false}">
          <fmt:message key="DELIVERY_TYPE_SELF" bundle="${lang}"/>
        </c:if>
      </h4>
      <h4><fmt:message key="AMOUNT" bundle="${lang}"/>: ${order.amount}</h4>
      <h4><fmt:message key="TOTAL_PRICE" bundle="${lang}"/>:
        <span id="product-price">
          <fmt:setLocale value="${order.costLocale}"/>
          <fmt:formatNumber value="${order.cost}" type="currency"/>
          <fmt:setLocale value="${currentLocale}"/>
        </span>
      </h4>
      <h4><fmt:message key="ORDER_TIMESTAMP" bundle="${lang}"/>:
        <jsp:useBean id="dateValue" class="java.util.Date" />
        <jsp:setProperty name="dateValue" property="time" value="${order.whenOrdered}" />
        <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" /></p>
      </h4>
    </div>
  </div>
</div>