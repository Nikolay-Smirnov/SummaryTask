<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="OrderCar" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<form id="login_form" action="controller" method="post">
		<input type="hidden" name="command" value="OrderCar" />
		Choice Date: <input type="date" name="calendar">
		<fieldset>
			<legend>Add Function</legend>
			<select name="function">
				<option>Personal Driver</option>
				<option>Without Function</option>
			</select>
		</fieldset>
		<br /> <input type="submit" value="Take order"> <br> <br>
	</form>
</body>
</html>	