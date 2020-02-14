<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vehicle Manager Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
	<div align='center'>
		<h1>Vehicle Manager Application</h1>
		<h3><a href="new">New Vehicle</a></h3>
		<table border="1" cellpadding="5">
			<tr>
				<th>Id</th>
				<th>Year</th>
				<th>Make</th>
				<th>Mode</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${listVehicle}" var="vehicle" varStatus="status">
				<tr>
<%-- 					<td>${status.index + 1}</td> --%>
					<td>${vehicle.id}</td>
					<td>${vehicle.year}</td>
					<td>${vehicle.make}</td>
					<td>${vehicle.model}</td>
					<td>
						<a href="edit?id=${vehicle.id}">Edit</a>
						<a href="delete?id=${vehicle.id}">Delete</a>
					</td>
				</tr>
			
			</c:forEach>
			
		</table>	
	</div>
</body>
</html>