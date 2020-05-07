<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="deleteCars" />
		<jsp:useBean id="db" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.CarController" />

		<header>
			<nav> 
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="RegNewManager"
						value="<fmt:message key='admin_jsp.label.menu_page'/>" /> <input type="submit"
						name="RegNewAdmin" value="<fmt:message key='admin_jsp.label.admReg_page'/>" /> <input
						type="submit" name="RegNewCar" value="<fmt:message key='admin_jsp.label.carReg_page'/>" /><input
						type="submit" name="BlockClient" value="<fmt:message key='admin_jsp.label.blockClient_page'/>" /><input
						type="submit" name="dismissalManager" value="<fmt:message key='admin_jsp.label.dismassal_page'/>" /> <input
						type="submit" name="menu" value="<fmt:message key='menu_jsp.label.menu_page'/>" />
				</ul>
			</nav>
		</header>
		<hr>


		<table>
			<td><fmt:message key='admin_jsp.label.idCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.brandCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.modelCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.levelCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.vidCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.priceCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.descCar_page'/></td>
			<td>&#128504;</td>
			<td><fmt:message key='admin_jsp.label.changeCar_page'/></td>
			<td><fmt:message key='admin_jsp.label.deleteCar_page'/></td>
			<c:forEach var="car" items="${db.getAllCars()}">
				<tr>
					<td>${car.idCars}</td>
					<td>${car.carBrand}</td>
					<td>${car.model}
					<td>${car.carLevel}</td>
					<td>${car.vin}</td>
					<td>${car.price}&nbsp;&#8372</td>
					<td>${car.conditionCar}</td>
					<td><input type="checkbox" name="id" value="${car.idCars}" /></td>
					<td><a
						href="controller?command=LinkUpdateCar&id=${car.idCars}"><fmt:message key='admin_jsp.label.changeCar_page'/></a></td>
					<td><input type="submit" name="deleteCar"
						value="<fmt:message key='admin_jsp.label.deleteCar_page'/>" /></td>
				</tr>
			</c:forEach>

		</table>
	</form>
</body>
</html>