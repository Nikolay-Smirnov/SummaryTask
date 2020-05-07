<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<form id="delete_form" action="controller" method="post">
		<input type="hidden" name="command" value="deleteCars" />
		<jsp:useBean id="mc" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.ManagerController" />
		<jsp:setProperty name="mc" property="idManager" value="${idManager}" />
		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit" name="transferOrder"
						value="Главное меню" /> <input type="submit" name="menu"
						value="Главное меню" />
				</ul>
			</nav>
		</header>
		<div id="page">
			<p class="text">
				<br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<fmt:message key='admin_jsp.label.textInFiredTwo_page'/> <br> <br>

			</p>
		</div>
		<table>
			<c:forEach var="manager" items="${mc.getManagerById()}">
				<tr>
					<td>${manager.id}</td>
					<td>${manager.login}</td>
					<td>${manager.name}
					<td>${manager.surname}</td>
					<td>${manager.numberManager}</td>
				</tr>
			</c:forEach>

		</table>

		<br>
		<div id="page">
			<p class="text">
				<br> <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key='admin_jsp.label.textInFiredThree_page'/>
			</p>
		</div>
		<br> <br>
		<table>
			<c:forEach var="manager" items="${mc.getWorkedManagers()}">
				<tr>
					<c:set var="passport" scope="session" value="${manager.passport}" />
					 <c:out value = "${passport}"/>
					<td>${manager.id}</td>
					<td>${manager.login}</td>
					<td>${manager.name}
					<td>${manager.surname}</td>
					<td>${manager.numberManager}</td>
					<td><input type="checkbox" name="passport" value="${manager.passport}" /></td>
					<td><input type="submit" name="transferOrder" value="Выбрать" /></td>
				</tr>
			</c:forEach>

		</table>
	</form>
</body>
</html>