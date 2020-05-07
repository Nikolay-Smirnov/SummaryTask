<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="login_form" action="controller" method="get">
		<input type="hidden" name="command" value="UpdateCar" />
		<header>
						<nav>
							<ul class="nav_buttons">
								<li class="button"><input type="submit" name="menu"
									value="<fmt:message key='menu_jsp.label.menu_page'/>" /></li>
								<li class="button"><input type="submit" name="deleteCars"
									value="<fmt:message key='menu_jsp.label.cabinet_login_page'/>" /></li>

							</ul>
						</nav>
					</header>
		<table id="main-container">

			<tr>
				<td class="content center">
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.idCar_page' />
						</legend>
						<input name="id" value="${car.idCars}" /><br />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.brandCar_page' />
						</legend>
						<input name="carBrand" value="${car.carBrand}" /><br />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.modelCar_page' />
						</legend>
						<input name="model" value="${car.model}" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.levelCar_page' />
						</legend>
						<input name="carLevel" value="${car.carLevel}" />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.statusCar_page' />
						</legend>
						<input name="statusCar" value="${car.status}" />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.idAdmin_page' />
						</legend>
						<input name="idAdmin" value="${car.idAdmin}" />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.priceCar_page' />
						</legend>
						<input name="price" value="${car.price}" />&nbsp;&#8372
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.descCar_page' />
						</legend>
						<input name="conditionCar" value="${car.conditionCar}" />
					</fieldset>
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.vidCar_page' />
						</legend>
						<input name="vin" value="${car.vin}" />
					</fieldset> <br /> <input type="submit" name="updateCar" value="<fmt:message key='manager_jsp.label.accept_menu_page' />">
					<br> <br></td>
			</tr>
		</table>
	</form>

</body>
</html>