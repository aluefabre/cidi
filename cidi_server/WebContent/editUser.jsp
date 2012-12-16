<%@ include file="/parts/include.jsp" %>
<html>
<body>

<form id="form" action="${model.appContext}/user/editUserSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Id</b></td>
			<td><b>Name</b></td>
			<td><b>Password</b></td>
			<td><b>Retype Password</b></td>
		</tr>
		<tr>
			<td><input name="userId" readonly="readonly" value="${model.user.id}"></input></td>
			<td><input name="name" value="${model.user.name}"></input></td>
			<td><input name="password" value="${model.user.password}"></input></td>
			<td><input name="repassword" value="${model.user.password}"></input></td>
		</tr>
	</table>
	<input type="submit" value="Update"></input>
</form>

<%@ include file="/parts/links.jsp" %>

</body>
</html>