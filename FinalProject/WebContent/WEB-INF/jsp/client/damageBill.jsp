<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="cabinet_form" action="controller" method="get">
		<input type="hidden" name="command" value="damageBillClient" />
		<jsp:useBean id="dbc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.DamageBillController" />

		<jsp:setProperty name="dbc" property="indexClient"
			value="${indexClient}" />

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
		<action>
		<nav class="navBar">
			<ul class="sort_buttons">
				<li class=action_button><input type="submit" name="payBill"
					value="Pay"></li>
			</ul>
		</nav>
		</action>
		<table border=1>

			<c:forEach var="order" items="${dbc.getDamageBillForClient()}">

				<tr>
					<td>${order.carBrand}</td>
					<td>${order.modelCar}</td>
					<td>${order.nameManager}</td>
					<td>${order.loginManager}</td>
					<td>${order.price}&nbsp;&#8372</td>
					<td>${order.numberManager}</td>
				</tr>


			</c:forEach>
		</table>
	</form>
</body>
</html>