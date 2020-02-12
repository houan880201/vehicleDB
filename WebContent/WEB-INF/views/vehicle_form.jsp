<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New / Edit Vehicle</title>
</head>
<body>
	<div align='center'>
		<h1>New/Edit Contact</h1>
		<form:form action='save' method='post' modelAttribute='vehicle'>
			<table cellpadding='5'>
				<tr>
					<td>Year</td>
					<td><form:input path='year'/></td>
				</tr>
				<tr>
					<td>Make</td>
					<td><form:input path='make'/></td>
				</tr>
				<tr>
					<td>Model</td>
					<td><form:input path='model'/></td>
				</tr>
				<tr>
					<td colspan='2' align='center'><input type='submit' value='Save'/></td>
				</tr>
			</table>
		</form:form>
	
	</div>

</body>
</html>