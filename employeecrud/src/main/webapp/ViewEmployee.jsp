<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>All Employees </h1>
	<table border="1">
		<tr>
			<th>Employee Id</th>
			<th>Employee Name</th>
			<th>Employee Salary</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${sessionScope.employeelist}" var="employee">
			<tr>
				<td>${employee.employeeId}</td>
				<td>${employee.employeeName}</td>
				<td>${employee.employeeSalary}</td>
				<td>
					<form action="SaveEmployee?employeeId=${employee.employeeId}"
						method="post">
						<input type="submit" value="UpdateEmployee"> <input
							type="hidden" name="flag" value="UpdateEmployee">
					</form>
					</td>
					<td>
					<form action="SaveEmployee?employeeId=${employee.employeeId}"
						method="post">
						<input type="submit" value="DeleteEmployee"> <input
							type="hidden" name="flag" value="DeleteEmployee">
					</form>
				</td>
			</tr>
		</c:forEach>
		<form action="SaveEmployee" method="post">
			<a href="index.jsp"> <input type="submit" value="HomePage">
				<input type="hidden" name="flag" value="HomePage">
			</a>
		</form>
	</table>
</body>
</html>