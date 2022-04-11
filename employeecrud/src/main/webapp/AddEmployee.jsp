<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
<h1>Add new employee</h1>
	<form action="SaveEmployee" method="post">
		<table align="center">
			<tr>
				<td>Employee name</td>
				<td><input type="text" name="employeeName"
					placeholder="Enter name"></td>
			</tr>
			<tr>
				<td>Salary</td>
				<td><input type="text" name="employeeSalary"
					placeholder="Enter salary"></td>
			</tr>
			<tr>
				<td colspan="2">
			<input type="hidden" name="flag" value="AddEmployee">
			<input type="submit" value="AddEmployee"></td>
			</tr>
		</table>
	</form>
</body>
</html>