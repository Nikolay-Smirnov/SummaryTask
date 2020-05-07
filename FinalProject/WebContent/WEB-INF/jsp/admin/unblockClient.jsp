<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="delete_form" action="controller" method="get">
		<input type="hidden" name="command" value="UnblockClient" />

		<jsp:useBean id="cl" scope="application"
			class="ua.nure.smirnov.finalproject.Controller.ClientController" />
		<jsp:setProperty name="cl" property="passportClient"
			value="${passportClient}" />
			<jsp:setProperty name="cl" property="loginClient"
			value="${loginClient}" />
		<header>
			<nav>
				<ul class="nav_buttons">
					<li class="button"><input type="submit"
						name="BlockClient" value="<fmt:message key='menu_jsp.label.block_page'/>" /> <input
						type="submit" name="menu" value="<fmt:message key='menu_jsp.label.menu_page'/>" />
				</ul>
			</nav>
		</header>

		<action>
		<nav class="navBar">
			<ul class="sort_buttons">
				<li class=action_button><input type="submit"
					name="unblockClient" value="<fmt:message key='admin_jsp.label.unblock_page'/>" />
				<li class="findLine"><fieldset>
						<input class="find" name="passportClient" />
					</fieldset></li>
				<li class="acceptSortButton"><input type="submit"
					name="findUnblockClient" value="<fmt:message key='menu_jsp.label.find_menu_page'/>"></li>
			</ul>
		</nav>

		</action>
		<table>
			<c:forEach var="client" items="${cl.getUnblockedClient()}">
				<tr>
					<td>${client.loginClient}
					<td>${client.nameClient}</td>
					<td>${client.surname}</td>
					<td><input type="checkbox" name="id"
						value="${client.idClient}" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>

</body>
</html>