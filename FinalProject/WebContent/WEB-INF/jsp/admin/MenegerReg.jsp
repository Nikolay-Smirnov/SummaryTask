<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<body>
	<form id="manager_reg_form" action="controller" method="post">
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
					value="regManager" />
					<fieldset>
						<legend>
							<fmt:message key='login_jsp.label.login_login_page' />
						</legend>
						<input name="login" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='login_jsp.label.password_login_page' />
						</legend>
						<input type="password" name="password" />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.name_page' />
						</legend>
						<input name="username" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.surname_page' />
						</legend>
						<input name="surname" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.passport_page' />
						</legend>
						<input name="passport" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.dataOfBorn_page' />
							:
						</legend>
						<input name="age" />
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.sex_page' />
						</legend>
						<select name="sex">
							<option>M</option>
							<option>W</option>
							<option>N</option>
						</select>
					</fieldset> <br />
					<fieldset>
						<legend>
							<fmt:message key='admin_jsp.label.numberPhone_page' />
						</legend>
						<input name="number" /><br />
					</fieldset> <br /> <input type="submit" name="acceptManagerReg"
					value="<fmt:message key='admin_jsp.label.reg_page'/>"></td>
			</tr>
		</table>

	</form>
</body>
</html>