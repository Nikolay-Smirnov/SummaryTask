<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="cabinet_form" action="controller" method="get">
		<input type="hidden" name="command" value="oldClientOrders" />
		<jsp:useBean id="oc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.OrderController" />

		<jsp:setProperty name="oc" property="index" value="${indexClient}" />
		
		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="menu"
						value="<fmt:message key='menu_jsp.label.menu_page'/>" /></li>
							<li class="button"><input type="submit" name="clientCabinet"
						value="<fmt:message key='menu_jsp.label.cabinet_login_page'/>" /></li>
				</ul>
			</nav>
		</header>
		<table border=1>

			<c:forEach var="order" items="${oc.getClientOldOrders()}">

				<tr>
					<td>${order.idOrder}</td>
					<td>${order.model}</td>
					<td>${order.nameManager}</td>
					<td>${order.loginManager}</td>
					<td>${order.function}</td>
					<td>${order.startDate}</td>
					<td>${order.finishDate}</td>
					<td>${order.orderPrice}&nbsp;&#8372</td>
					<td>${order.numberManager}</td>
				</tr>


			</c:forEach>
		</table>
	</form>
</body>
</html>