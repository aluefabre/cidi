<%@ include file="/parts/include.jsp" %>
<html>
<body>
<form id="form" action="${model.appContext}/user/addUserSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Name</b></td>
			<td><b>Password</b></td>
			<td><b>Retype Password</b></td>
		</tr>
		<tr>
			<td><input name="name" value=""></input></td>
			<td><input name="password" value=""></input></td>
			<td><input name="repassword" value=""></input></td>
		</tr>
	</table>
	<input type="submit" value="Add"></input>
</form>
<%@ include file="/parts/links.jsp" %>
</body>
</html>