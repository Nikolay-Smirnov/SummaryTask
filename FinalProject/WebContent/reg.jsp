<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<form id="reg_form" action="controller" method="post">
		<table id="main-container">

			<tr>
				<td class="content center"><input type="hidden" name="command"
					value="reg" />
					<fieldset>
						<legend>Логин</legend>
						<input name="login" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>Пароль</legend>
						<input type="password" name="password" />
					</fieldset>
					<fieldset>
						<legend>Имя</legend>
						<input name="username" /><br />
					</fieldset> <br />
					<fieldset>
						<legend>Фамилия</legend>
						<input name="surname" /><br />
					</fieldset>

					<fieldset>
						<legend>Серия паспорта</legend>
						<input name="passport" /><br />
					</fieldset>

					<fieldset>
						<legend>Номер телефона</legend>
						<input name="number" /><br />
					</fieldset>
					<fieldset>
						<legend>Дата рождения</legend>
						<input name="age" /><br />
					</fieldset>
					<fieldset>
						<legend>Пол</legend>
						<select name="sex">
							<option>M</option>
							<option>W</option>
							<option>N</option>
						</select>
					</fieldset> <input type="submit" value="Login"></td>
			</tr>
		</table>

	</form>
</body>
</html>