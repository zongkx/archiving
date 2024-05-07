<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<form action="${pageContext.request.contextPath}/reg" method="post">
		<input type="text" name="username"> 
		<input type="text"name="password"> 
		<input type="submit" >

	</form>
	${msg}
</body>
</html>