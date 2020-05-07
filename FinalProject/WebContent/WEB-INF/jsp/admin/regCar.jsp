<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<c:set var="title" value="Car" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<form id="login_form" action="controller" method="get">
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
				<td class="content center"><input type="hidden" name="command"
					value="regCar" />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.brandCar_page' />
						</legend>
						<input name="carBrand" />
					</fieldset>
					<br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.modelCar_page' />
						</legend>
						<input name="model" />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.levelCar_page' />
						</legend>
						<input name="carLevel" />
					</fieldset>
					<br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.priceCar_page' />
						</legend>
						<input name="price" />
					</fieldset>
					<br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.vidCar_page' />
						</legend>
						<input name="vin" />
					</fieldset>
					<br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.descCar_page' />
						</legend>
						<input name="carCondition" />
					</fieldset> <br /> <input type="submit" name="AcceptRegCar"
					value="<fmt:message key='admin_jsp.label.changeCar_page'/>">
					<br> <br></td>
			</tr>
		</table>
	</form>
</body>
</html>