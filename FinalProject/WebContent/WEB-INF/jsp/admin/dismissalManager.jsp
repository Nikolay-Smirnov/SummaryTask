<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="dissmialManager" />
		<jsp:useBean id="mc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.ManagerController" />

		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="menu"
						value="<fmt:message key='menu_jsp.label.menu_page'/>" /> <input
						type="submit" name="deleteCars"
						value="<fmt:message key='menu_jsp.label.cabinet_login_page'/>" /></li>
				</ul>
			</nav>
		</header>
		<hr>
		<br>
		<div id="page">
			<p class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<fmt:message key='admin_jsp.label.textInFired_page' />
			</p>
			<br>
			<table>
				<c:forEach var="manager" items="${mc.getAllManagers()}">
					<tr>
						<td>${manager.id}</td>
						<td>${manager.login}</td>
						<td>${manager.name}
						<td>${manager.surname}</td>
						<td>${manager.numberManager}</td>
						<td><input type="checkbox" name="id" value="${manager.id}" /></td>
						<td><input type="submit" name="firedManager"
							value="<fmt:message key='settings_jsp.form.submit_save_locale'/>" /></td>
					</tr>
				</c:forEach>

			</table>
	</form>
</body>
</html>