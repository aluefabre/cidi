<%@ include file="/parts/include.jsp" %>
<html>
<body>
<form id="form" action="${model.appContext}/item/addItemSubmit.htm" method="post" >
	<table border="1">
		<tr>
			<td><b>Writer</b></td>
			<td><b>Body</b></td>
			<td><b>Address</b></td>
			<td><b>Latitude</b></td>
			<td><b>Longitude</b></td>
		</tr>
		<tr>
			<td><input name="writer" value=""></input></td>
			<td><input name="body" value=""></input></td>
			<td><input name="address" value=""></input></td>
			<td><input name="latitude" value=""></input></td>
			<td><input name="longitude" value=""></input></td>
		</tr>
	</table>
	<input type="submit" value="Add"></input>
</form>
<%@ include file="/parts/links.jsp" %>
</body>
</html>