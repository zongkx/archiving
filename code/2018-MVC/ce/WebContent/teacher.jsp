<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>教师</title>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script language="JavaScript" src="h5/tab.js"></script>
<style type="text/css">

	body{min-width:1000px;background-color:#F5F5F5; }a{	text-decoration: none;color:#5F9EA0; }
 	input{background-color:#F5F5F5;color:#6495ED;border:1px solid #6495ED; }	
 	.tabs  li{
 		list-style: none;
 		float: left;
 		
 		padding: 10px;border-bottom:1px solid #6495ED; border-top:1px solid #6495ED; border-left:1px solid #6495ED; 
 	}
 	.tab_content{margin:0 auto;width:700px; }
 
 	#main{ 
 		width:100%;
 		height: 200px;
 	 }
	#main_d1{
		 height:70px;
		}
	#main_d2{
		width:800px;height:100px;
		font-size:22px;margin:0 auto;
		}
	
	#top_p1{
		 font-size:26px;color:#6495ED;
	}
	#bottom{ height:400px}
	#tab1 p{font-size:26px;}
	
	table  {
    font-family: verdana,arial,sans-serif;
    font-size:14px;
    color:#333333;
    border-width: 1px;
    border-color: #999999;
    border-collapse: collapse;
	}
	table th {
    	background-color:#c3dde0;
    	border-width: 1px;
    	padding:12px;
    	border-style: solid;
    	border-color: #E0FFFF;
	}

	table  td {
   		border-width: 1px;
    	padding: 12px;
    	border-style: solid;
    	border-color:#E0FFFF;
    	background-color:#c3dde0;
	}
	#tab4 input ,#tab3 input{
		 
		height:20px;
		width:150px;margin:10px;
	}
</style>
</head>
<body>
	<div id="main">
			<div id="main_d1">
					<div style="margin:0 auto;border:1px solid #F5F5F5;width:700px;height:100px;">
					<p id="top_p1">欢迎${acc}来到教师子系统
					<input style="margin-left:300px;"
					 type="button" value="退出" onclick="exit();" /> </p></div>
				</div>
			<div id="main_d2">
					<ul  class="tabs">
					<li class="active"> 
							<a href="#tab1" class="active">学生信息</a></li>
						<li><a href="#tab2">本院学生就业情况</a></li>
						<li><a href="#tab3" >修改学生信息</a></li>
						<li style="border-right:1px solid #6495ED; "><a href="#tab4" >修改密码</a></li>
						 
					</ul>
			</div>
			 
			
		</div>
		 
		<div id="bottom">
			<div id="tab1" class="tab_content" style="display: block;">
				
				<table >
						<tr>
							<th>学号</th><th>name</th><th>age</th><th>grade</th><th>学院</th>
						</tr>
					<c:forEach items="${StuAllSel}" var="Student">
						<tr >
							<td >${Student.acc} </td>
							<td >${Student.name} </td>
							<td>${Student.age} </td>
							<td >${Student.grade} </td>
							<td >${Student.aca} </td>
							 
						</tr>
					</c:forEach>
				</table><br>
			
				
				
				
			</div>
			
			
			
			<div id="tab2" class="tab_content" style="display: none; ">
		 
				<table >
					<tr>
						<th>就业学生学号</th>
						<th>学院/学生姓名/公司名称</th>
						<th>时间</th>
						 
						<th>合约期</th>
					
					</tr>
					<c:forEach items="${JobAllSel}" var="Job">
						<tr >
							<td>${Job.stu_acc} </td>
							<td >${Job.jobd} </td>
							<td>${Job.time} </td>
							 
							<td >${Job.contract} </td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div id="tab3" class="tab_content" style="display: none; ">
					<form action="InfoChange" method="post">
					请输入所要修改学生的信息<br>
					学生学号:<input type="text" name ="stu_acc"><br>
					姓名：<input type="text" name ="stu_name"><br>
					年龄:<input type="text" name ="stu_age"><br>
					学分:<input type="text" name ="stu_grade"><br>
					学院:<input type="text" name ="stu_aca"><br>
					<input type="hidden" name ="flag" value="changeStu1">
					<input type="submit" style="width:50px;">
				
				</form>
			
			
			
			</div>
			<div id="tab4" class="tab_content" style="display: none; ">
				<form action="AccChange" method="post" >
					你的 	帐号：<input  type="text" name="acc" value="${acc}" disabled="disabled"><br>
					请输入密码：<input type="password"><br>
					请确认密码：<input type="password" name="pwd"><br>
					<input type="hidden" value="Teacher" name="table">
					<input type="hidden" value="AccChange" name="flag">
					<input type="submit" value="保存" style="width:50px;">
				</form>
			
			
			
			</div>
			 
		</div>


</body>
</html>