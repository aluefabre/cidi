<%@ include file="/parts/include.jsp" %>
<html>
<body>

<form id="form" action="${model.appContext}/comment/editCommentSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Id</b></td>
			<td><b>Writer</b></td>
			<td><b>Body</b></td>
		</tr>
		<tr>
			<td><input name="commentId" readonly="readonly" value="${model.comment.id}"></input></td>
			<td><input name="writer" value="${model.comment.writer}"></input></td>
			<td><input name="body" value="${model.comment.body}"></input></td>
		</tr>
	</table>
	<input type="submit" value="Update"></input>
</form>

<%@ include file="/parts/links.jsp" %>

</body>
</html>