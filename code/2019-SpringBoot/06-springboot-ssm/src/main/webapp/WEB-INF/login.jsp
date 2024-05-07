<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



</head>

<body>
	${msg }
	<form action="${pageContext.request.contextPath}/login.action" method="post">
		<input type="text" name="usercode"><br>
		<input type="password" name="password"><br>
		<input type="submit" value="go">
	</form>
	
	<a href="logout.action">out</a>



</body>
</html>