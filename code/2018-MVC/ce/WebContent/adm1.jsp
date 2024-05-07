<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	input{background-color:#F5F5F5;color:#6495ED;border:1px solid #6495ED;height:20px;
		width:150px;margin:10px; }	

</style>
</head>
<body>	
	<form action="InfoChange" method="post">
					请输入所要修改招聘会的信息<br>
					地址:<input type="text" name ="add" value="<%=request.getParameter("add")%>"><br>
					日期：<input type="text" name ="date" value="<%=request.getParameter("date")%> "><br>
					详细:<input type="text" name ="detail" value="<%=request.getParameter("detail")%> "><br>
					 
					<input type="hidden" name="jf_id" value="<%=request.getParameter("jf_id")%>" >  <br>
					<input type="hidden" name="flag" value="changeJobFair" >  <br>
    				
					<input type="submit">
				
	 </form>
	
</body>
</html>