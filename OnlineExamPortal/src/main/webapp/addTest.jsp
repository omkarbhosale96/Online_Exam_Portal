<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
</head>
<body>
<%
	if(session.getAttribute("username")==null && session.getAttribute("password")==null){
		response.sendRedirect("index.html");
	}

%>
<form action="addQuestion" method="post">
Question : <input type="text" name="question"><br>
Option A : <input type="text" name="optionA"><br>
Option B : <input type="text" name="optionB"><br>
Option C : <input type="text" name="optionC"><br>
Option D : <input type="text" name="optionD"><br>
Answer   : <select name="answer" >
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
			</select>
<input type="submit" value="Add">
</form>


<table>
<tr>
	<th>Question</th>
	<th>Option A</th>
	<th>Option B</th>
	<th>Option C</th>
	<th>Option D</th>
	<th>Answer</th>
</tr>
<c:forEach items="${qList}" var="question" varStatus="loop">
<tr>
	<td>${question.qName}</td>
	<td>${question.optionA}</td>
	<td>${question.optionB}</td>
	<td>${question.optionC}</td>
	<td>${question.optionD}</td>
	<td>${question.answer}</td>
	<td><form action="removeFromList?index=${loop.index}" method="post" name="id" >
	<input type="submit" value="Remove">
	</form></td>
</tr>
</c:forEach>
</table>


<form action="saveTest" method="post">
Name of Test : <input type="text" name="testName"><br>
<input type="submit" value="Save">
</form><br>


</body>
</html>