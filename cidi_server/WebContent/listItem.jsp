<%@ include file="/parts/include.jsp" %>
<html>
<body>
<table border="1">
	<tr>
		<td><b>Id</b></td>
		<td><b>Writer</b></td>
		<td><b>Body</b></td>
		<td><b>Address</b></td>
		<td><b>Latitude</b></td>
		<td><b>Longitude</b></td>
		<td><b>Edit</b></td>
		<td><b>Delete</b></td>
	</tr>
	<core:forEach items="${model.items}" var="item">
	<tr>
		<td><core:out value="${item.id}"/></td>
		<td><core:out value="${item.writer}"/></td>
		<td><core:out value="${item.body}"/></td>
		<td><core:out value="${item.address}"/></td>
		<td><core:out value="${item.latitude}"/></td>
		<td><core:out value="${item.longitude}"/></td>
		<td><a href="${model.appContext}/item/editItem.htm?itemId=${item.id}">Edit</a></td>
		<td><a href="javascript:confirmDelete('${model.appContext}/item/deleteItem.htm?itemId=${item.id}')">Delete</a></td>
	</tr>
	</core:forEach>
</table>

<a href="${model.appContext}/item/addItem.htm">Add</a>

<%@ include file="/parts/links.jsp" %>
</body>
</html>