<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="UpdateDescriptionOrder" />
		<jsp:useBean id="oc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.OrderController" />
		<jsp:setProperty name="oc" property="idOrder" value="${idOrder}" />
		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="menu"
						value="<fmt:message key='menu_jsp.label.menu_page'/>" /></li>
					<li class="button"><input type="submit" name="managersCabinet"
						value="<fmt:message key='menu_jsp.label.cabinet_login_page'/>" />
					</li>
				</ul>
			</nav>
		</header>
		<table border=1>
			<c:forEach var="order" items="${oc.getOrderById()}">

				<tr>
					<td>${order.nameClient}</td>
					<td>${order.idOrder}</td>
					<td>${order.brandCar}</td>
					<td>${order.surnameClients}</td>
					<td>${order.loginClient}</td>
					<td>${order.index}</td>
					<td>${order.idCar}</td>
					<td>${order.model}</td>
					<td>${order.function}</td>
					<td>${order.startDate}</td>
					<td>${order.finishDate}</td>
					<td>${order.price}</td>
				</tr>


			</c:forEach>
		</table>
		<fieldset>
			<legend>
				<fmt:message key='admin_jsp.label.descCar_page' />
			</legend>
			<input name="desc" value="${order.descripton}" /><br />
		</fieldset>
		<input type="submit" name="acceptReturn"
			value="<fmt:message key='manager_jsp.label.return_menu_page'/>">
	</form>

</body>
</html>