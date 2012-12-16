<%@ include file="/parts/include.jsp" %>
<html>
<body>

<form id="form" action="${model.appContext}/item/editItemSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Id</b></td>
			<td><b>Writer</b></td>
			<td><b>Body</b></td>
		</tr>
		<tr>
			<td><input name="itemId" readonly="readonly" value="${model.item.id}"></input></td>
			<td><input name="writer" value="${model.item.writer}"></input></td>
			<td><input name="body" value="${model.item.body}"></input></td>
		</tr>
	</table>
	<input type="submit" value="Update"></input>
</form>

<br/>

<table border="1">
	<tr>
		<td><b>Id</b></td>
		<td><b>Writer</b></td>
		<td><b>Body</b></td>
		<td><b>Edit</b></td>
		<td><b>Delete</b></td>
	</tr>
	<core:forEach items="${model.comments}" var="comment">
	<tr>
		<td><core:out value="${comment.id}"/></td>
		<td><core:out value="${comment.writer}"/></td>
		<td><core:out value="${comment.body}"/></td>
		<td><a href="${model.appContext}/comment/editComment.htm?commentId=${comment.id}">Edit</a></td>
		<td><a href="javascript:confirmDelete('${model.appContext}/comment/deleteComment.htm?commentId=${comment.id}')">Delete</a></td>
	</tr>
	</core:forEach>
</table>

<a href="${model.appContext}/comment/addComment.htm?itemId=${model.item.id}">Add</a>

<%@ include file="/parts/links.jsp" %>

</body>
</html>