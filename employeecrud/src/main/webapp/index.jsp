<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<form action="SaveEmployee" method="post">
		<a href="AddEmployee.jsp"><input type="button"
			value="AddEmployee" /></a> <input type="submit" name="flag"
			value="ViewEmployee" />
	</form>
</body>
</html>