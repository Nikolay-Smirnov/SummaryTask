<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="managerOldOrders" />
		<jsp:useBean id="oc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.OrderController" />
		<jsp:setProperty name="oc" property="index" value="${indexManager}" />
		<jsp:setProperty name="oc" property="indexClient"
			value="${indexClient}" />
			<jsp:setProperty name="oc" property="loginClient"
			value="${loginClient}" />


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
		<subheader>
		<nav>
			<ul class="sort_buttons">
				<li class="acceptSortButton"><input type="submit"
					name="allOldOrders"
					value="<fmt:message key='manager_jsp.label.oldOrders_menu_page'/>" /></li>
				<li class="acceptSortButton"><input type="submit"
					name="myOldOrders"
					value="<fmt:message key='admin_jsp.label.MyOldOrder_page'/>" /></li>


				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<li class="findLine"><fieldset>
						<input class="find" name="pasportClient" />
					</fieldset></li>
				<li class="acceptSortButton"><input type="submit"
					name="findClient"
					value="<fmt:message key='menu_jsp.label.find_menu_page'/>"></li>

			</ul>

		</nav>
		</subheader>



		<table border=1>

			<c:forEach var="order" items="${oc.getManagerOldOrders()}">

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
					<td>${order.description}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>