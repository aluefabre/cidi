<%@ include file="/parts/include.jsp" %>
<html>
<body>
<table border="1">
	<tr>
		<td><b>Id</b></td>
		<td><b>Name</b></td>
		<td><b>Password</b></td>
		<td><b>Edit</b></td>
		<td><b>Delete</b></td>
	</tr>
	<core:forEach items="${model.users}" var="user">
	<tr>
		<td><core:out value="${user.id}"/></td>
		<td><core:out value="${user.name}"/></td>
		<td><core:out value="${user.password}"/></td>
		<td><a href="${model.appContext}/user/editUser.htm?userId=${user.id}">Edit</a></td>
		<td><a href="javascript:confirmDelete('${model.appContext}/user/deleteUser.htm?userId=${user.id}')">Delete</a></td>
	</tr>
	</core:forEach>
</table>

<a href="${model.appContext}/user/addUser.htm">Add</a>

<%@ include file="/parts/links.jsp" %>
</body>
</html>