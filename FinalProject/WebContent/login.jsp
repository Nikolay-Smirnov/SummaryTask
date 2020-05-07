<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="javax.servlet.jsp.jstl.core.Config"%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<form id="login_form" action="controller" method="post">
		
		<table id="main-container">

			<tr>
				<td class="content center"><br> <input type="hidden"
					name="command" value="login" />
					<fieldset>
						<legend>
							<fmt:message key="login_jsp.label.login_login_page" />
						</legend>
						<input name="login" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key="login_jsp.label.password_login_page" />
						</legend>
						<input type="password" name="password" />
					</fieldset> <br /> <input type="submit" value="Take order"> <br>
					<br> <a href="http://localhost:8080/FinalProject/reg.jsp"><fmt:message
							key="login_jsp.label.registration_login_page" /></a></td>
			</tr>
		</table>
	</form>
</body>
</html>