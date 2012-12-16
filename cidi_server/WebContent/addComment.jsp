<%@ include file="/parts/include.jsp" %>
<html>
<body>
<form id="form" action="${model.appContext}/comment/addCommentSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Writer</b></td>
			<td><b>Body</b></td>
			<td><b>ItemId</b></td>
		</tr>
		<tr>
			<td><input name="writer" value=""></input></td>
			<td><input name="body" value=""></input></td>
			<td><input name="itemId" value="${model.itemId}"></input></td>
		</tr>
	</table>
	<input type="submit" value="Add"></input>
</form>
<%@ include file="/parts/links.jsp" %>
</body>
</html>