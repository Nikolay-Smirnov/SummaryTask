<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>

<body>
	<form id="menu_form" action="controller" method="get">

		<jsp:useBean id="cc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.CarController" />
		<jsp:setProperty name="cc" property="carList" value="${carList}" />
		<jsp:setProperty name="cc" property="carBrand" value="${carBrand}" />
		<jsp:setProperty name="cc" property="levelCar" value="${carLevel}" />
		<jsp:setProperty name="cc" property="carModel" value="${carModel}" />
		<jsp:setProperty name="cc" property="carModelAndBrand"
			value="${carModelAndBrand}" />
		<input type="hidden" name="command" value="Menu" />

		<% if(session.isNew()) {
			String log = "Вход";
		}%>

		<header>

			<nav>
				<ul class="nav_buttons">
					<li class="button"></li>
					<li class="button"><input type="submit" name="first"
						value="<fmt:message
							key='menu_jsp.label.cabinet_login_page'/>"></li>
					<li class=button><input type="submit" name="chooseLanguage"
						value="<fmt:message
							key='login_jsp.label.take_language_login_page'/>">
					</li>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<li class="findLine"><fieldset>
							<input class="find" name="brandCar" />
						</fieldset></li>
					<li class="findLine"><input type="submit" name="findCar"
						value="<fmt:message
							key='menu_jsp.label.find_menu_page'/>"></li>
					<li class="button2"><input type="submit" name="exit"
						value="${log}"></li>
				</ul>
			</nav>
		</header>
		<subheader>
		<nav>
			<ul class="sort_buttons">
				<li>
					<fieldset>
						<legend>
							<fmt:message key='menu_jsp.label.levelQ_menu_page' />
							:
						</legend>
						⠀⠀⠀⠀ <select name="carLevel">
							<option></option>
							<option>A</option>
							<option>B</option>
							<option>SUV</option>
						</select>
					</fieldset>
				</li>
				
				<li><input type="checkbox" id="sortToLow" name="sort" value="sortToHigh">
				<label class="sortToLow" for="sortToLow"><fmt:message
						key='menu_jsp.label.sortMin_menu_page' /></label>
						
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				<input type="checkbox" id="sortToHigh" name="sort" value="sortToLow">
				<label for="sortToHigh" class="sortToHigh"><fmt:message
						key='menu_jsp.label.sortMax_menu_page' />
						</label>
						</li>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

				<li class="acceptSortButton"><input type="submit"
					name="acceptSort"
					value="<fmt:message key='menu_jsp.label.setSort_menu_page' />"></li>

			</ul>

		</nav>
		</subheader>
		
		<subheader>
		<nav>
			<ul class="sort_buttonsTwo">
				<fmt:message key='menu_jsp.label.date_menu_page' />
				:
				<input type="date" name="calendar">

				<li class="acceptSortButtonTwo"><input type="submit" name="second"
					value="<fmt:message
							key='menu_jsp.label.take_order_menu_page'/>"></li>

			</ul>

		</nav>
		</subheader>





		<c:set var="date" value="${date}" />

		<c:if test="${date == null}">

			<c:set var="date" value="${1}" />

		</c:if>
		<table border=1>
			<td><fmt:message key='admin_jsp.label.brandCar_page' /></td>
			<td><fmt:message key='admin_jsp.label.modelCar_page' /></td>
			<td><fmt:message key='admin_jsp.label.levelCar_page' /></td>
			<td><fmt:message key='admin_jsp.label.priceCar_page' /></td>
			<c:forEach var="car" items="${cc.getSortCarMenu()}">


				<tr>
					<td><input type="checkbox" name="id" value="${car.idCars}" /></td>
					<td>${car.carBrand}</td>
					<td>${car.model}</td>
					<td>${car.carLevel}</td>
					<td>${car.price*date}&nbsp;&#8372</td>
					<td><a href="controller?command=LinkOrderCar&id=${car.idCars}">Edit</a></td>
				</tr>

			</c:forEach>
		</table>

	</form>
</body>

</html>