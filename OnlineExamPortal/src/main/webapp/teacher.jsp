<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("username")==null && session.getAttribute("password")==null){
		response.sendRedirect("index.html");
	}
%>
<h3>Welcome to teacher portal</h3>

<table>
<tr>
	<th>Test Id</th>
	<th>Test name</th>
</tr>
<c:forEach items="${tests}" var="test">
<tr>
	<td>${test.testId}</td>
	<td>${test.tName}</td>
	<td><form action="removeTest?id=${test.testId}" method="post" name="id" >
	<input type="submit" value="Delete">
	</form></td>
	<td><form action="editTest?id=${test.testId}" method="post" name="id">
	<input type="submit" value="Edit">
	</form></td>
</tr>
</c:forEach>
</table><br>
<button onclick="window.location.href='addTest.jsp'">Add Test</button>
<form action="logout">
<input type="submit" value="Logout">
</form>
</body>
</html>