<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="ManagerCabinet" />
		<jsp:useBean id="oc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.OrderController" />
		<jsp:setProperty name="oc" property="index" value="${indexManager}" />
		<jsp:setProperty name="oc" property="indexClient" value="${indexClient}"/>
		<jsp:setProperty name="oc" property="loginClient"
			value="${loginClient}" />

		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="allOrders"
						value="<fmt:message key='manager_jsp.label.freeOrders_menu_page'/>" /> <input type="submit" name="myOldOrders"
						value="<fmt:message key='manager_jsp.label.oldOrders_menu_page'/>" /> <input type="submit" name="menu"
						value="<fmt:message key='menu_jsp.label.menu_page'/>" /> <input type="submit" name="damageOrders"
						value="<fmt:message key='menu_jsp.label.damage_menu_page'/>" />

				</ul>
			</nav>
		</header>

		<action>
		<nav class="navBar">
			<ul class="sort_buttons">
				<li class=action_button><input type="submit" name="returnOrder"
					value="<fmt:message key='manager_jsp.label.returnOrders_menu_page'/>" /> <input type="submit" name="damage"
					value="<fmt:message key='manager_jsp.label.damageBill_menu_page'/>" /> <input type="submit"
					name="rejectOrder" value="<fmt:message key='manager_jsp.label.rejectOrder_menu_page'/>" /> <input type="submit"
					name="acceptPay" value="<fmt:message key='manager_jsp.label.acceptPay_menu_page'/>" />
				<li class="findLine"><fieldset>
						<input class="find" name="passportClient" />
					</fieldset></li>
				<li class="acceptSortButton"><input type="submit"
					name="findClientOrder" value="<fmt:message key='menu_jsp.label.find_menu_page'/>"></li>



			</ul>
		</nav>
		</action>
		
		<table border=1>
			<td></td>
			<td><fmt:message key='admin_jsp.label.name_page'/></td>
			<td><fmt:message key='admin_jsp.label.idOrder_page'/></td>
			<td><fmt:message key='admin_jsp.label.brandCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.surname_page'/></td>
			<td><fmt:message key='login_jsp.label.login_login_page'/></td>
			<td><fmt:message key='admin_jsp.label.passport_page'/></td>
			<td><fmt:message key='admin_jsp.label.idCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.modelCar_page'/></td>
			<td><fmt:message key='manager_jsp.label.addFunction_menu_page'/></td>
			<td><fmt:message key='manager_jsp.label.dateOrder_menu_page'/></td>
			<td><fmt:message key='manager_jsp.label.dateEndOrder_menu_page'/></td>
			<td><fmt:message key='admin_jsp.label.priceCar_page'/></td>
			<jsp:setProperty name="oc" property="index" value="${indexManager}" />
			
			<c:forEach var="order" items="${oc.getManagerOrders()}">

				<tr>
					<td><input type="checkbox" name="id" value="${order.idOrder}" /></td>
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
	</form>
</body>
</html>