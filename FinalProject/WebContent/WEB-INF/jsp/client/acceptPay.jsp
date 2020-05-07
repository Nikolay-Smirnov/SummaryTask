<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>
<body>
	<form id="cabinet_form" action="controller" method="get">
		<input type="hidden" name="command" value="acceptPay" />
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
				<li class=action_button><input type="submit" name = "acceptPay" value="<fmt:message key='manager_jsp.label.accept_menu_page'/>"></li>
			</ul>
		</nav>
		</action>
		<fieldset>
			<legend>
				CVV :
			</legend>
			<input name="CVV" /><br />
		</fieldset>
		<br />
			<fieldset>
			<legend>
				Number cart:
			</legend>
			<input name="numberCart" /><br />
		</fieldset>
	</form>
</body>
</html>