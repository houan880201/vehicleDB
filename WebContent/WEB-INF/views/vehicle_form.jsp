<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New / Edit Vehicle</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script>
	
</script>
</head>
<body>
	<div align='center'>
		<h1>New/Edit Vehicle</h1>
		<form:form action='save' method='post' modelAttribute='vehicle'>
			<table cellpadding='5'>
				<form:hidden path="id" />
				<tr>
					<td>Year</td>
					<td><form:input id='year' path='year'/></td>
				</tr>
				<tr>
					<td>Make</td>
					<td><form:input id='make' path='make'/></td>
				</tr>
				<tr>
					<td>Model</td>
					<td><form:input id='make' path='model'/></td>
				</tr>
				<tr>
					<td colspan='2' align='center'><input id='submit_btn' type='submit' value='Save'/></td>
				</tr>
			</table>
		</form:form>
	
	</div>

</body>
</html>