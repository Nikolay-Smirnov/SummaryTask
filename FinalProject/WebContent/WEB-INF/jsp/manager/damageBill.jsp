<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
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
		<input type="hidden" name="command" value="DamageBill" />
		<table border=1>
			<tr>
				<td>${order.nameClient}</td>
				<td>${order.idOrder}</td>
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
		</table>
		<fieldset>
			<legend><fmt:message key='manager_jsp.label.text1_menu_page'/></legend>
			<input name="price" /><br />
		</fieldset>
		<fieldset>
			<legend><fmt:message key='manager_jsp.label.text2_menu_page'/></legend>
			<input name="desc"/><br />
		</fieldset>

		<input type="submit" name="acceptDamage" value="<fmt:message key='manager_jsp.label.accept_menu_page'/>">
	</form>
</body>
</html>