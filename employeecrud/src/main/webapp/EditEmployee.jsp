<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Edit Employee</h1>
	
		<form action="SaveEmployee?employeeId=${employee.employeeId}"
			method="post">
			<table border="1">
				<tr>
					<td>Employee Name:</td>
					<td><input type="text" name="employeeName"
						value="${employee.employeeName}" required=""></td>
				</tr>
				<tr>
					<td>Employee Salary:</td>
					<td><input type="text" name="employeeSalary"
						value="${employee.employeeSalary}" required=""></td>
				</tr>
			</table>
			<input type="hidden" name="flag"
						value="Update"> <input type="submit" value="Update">
		</form>
	
</body>
</html>