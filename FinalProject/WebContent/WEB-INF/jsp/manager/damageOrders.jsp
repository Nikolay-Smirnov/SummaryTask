<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="damageOrders" action="controller" method="get">
		<input type="hidden" name="command" value="damageOrders" />

		<jsp:useBean id="dbc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.DamageBillController" />
		<jsp:setProperty name="dbc" property="damageBillList"
			value="${damageBillList}" />
		<jsp:setProperty name="dbc" property="index" value="${index}" />
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
			<ul class=nav_action>

				<li class=action_button><input type="submit"
					name="acceptPaidBill" value="<fmt:message key='manager_jsp.label.acceptPay_menu_page'/>" /></li>
			</ul>
		</nav>

		</action>
		<c:if test="${not empty requestScope.errorMessage}">
			<h3>${requestScope.errorMessage}</h3>
		</c:if>

		<table border=1>

			<c:forEach var="damageBill" items="${dbc.getDamageBillForManager()}">

				<tr>
				<td><input type="checkbox" name="id" value="${damageBill.idPayment}" /></td>
					<td>${damageBill.idOrder}</td>
					<td>${damageBill.nameClient}</td>
					<td>${damageBill.surnameClient}</td>
					<td>${damageBill.loginClient}</td>
					<td>${damageBill.numberClient}</td>
					<td>${damageBill.carId}</td>
					<td>${damageBill.model}</td>
					<td>${damageBill.date}</td>
					<td>${damageBill.price}</td>
					<td>${damageBill.description}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>