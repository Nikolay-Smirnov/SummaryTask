<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="freeOrders" />
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
		<action>
		<nav class="navBar">
			<ul class="sort_buttons">
				<li class=action_button><input type="submit"
					name="acceptOrder" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>" /></li>
			</ul>
		</nav>
		</action>
		<jsp:useBean id="oc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.OrderController" />
		<table border=1>

			<c:forEach var="order" items="${oc.getManagerFreeOrders()}">

				<tr>
					<td>${order.idOrder}</td>
					<td>${order.nameClient}</td>
					<td>${order.surnameClients}</td>
					<td>${order.loginClient}</td>
					<td>${order.numberClient}</td>
					<td>${order.idCar}</td>
					<td>${order.model}</td>
					<td>${order.function}</td>
					<td>${order.startDate}</td>
					<td>${order.finishDate}</td>
					<td>${order.price}</td>
					<td><input type="checkbox" name="id" value="${order.idOrder}" /></td>
				</tr>
			</c:forEach>
		</table>


	</form>
</body>
</html>