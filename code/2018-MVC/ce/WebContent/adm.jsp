<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script language="JavaScript" src="h5/tab.js"></script>

<title>管理员</title>
<style type="text/css">
		body{min-width:1000px;background-color:#F5F5F5; }a{	text-decoration: none;color:#5F9EA0; }
 	input{background-color:#F5F5F5;color:#6495ED;border:1px solid #6495ED;height:20px;
		width:150px;margin:10px; }	
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
	 

</style>
</head>
<body>
	 
	<div id="main">
			<div id="main_d1">
					<div style="margin:0 auto;border:1px solid #F5F5F5;width:700px;height:100px;">
					<p id="top_p1">欢迎${acc}来到管理员子系统
					<input style="margin-left:300px;width:40px;"
					 type="button" value="退出" onclick="exit();" /> </p></div>
				</div>
			<div id="main_d2">
					<ul  class="tabs">
						<li class="active"><a href="#tab1" class="active">招聘会</a></li>
					<li><a href="#tab2">教师</a></li>
					<li><a href="#tab3" >公司</a></li>
					<li><a href="#tab4" >学生信息</a></li>
					<li><a href="#tab5" >就业信息</a></li>
					<li  ><a href="#tab6" >帐号管理</a></li>
					<li style="border-right:1px solid #6495ED; "><a href="#tab7" >修改密码</a></li>	 
						
					</ul>
			</div>
			 
			
		</div>
	<div id="bottom">
			<div id="tab1" class="tab_content" style="display: block;">
				<p id="p1">
		
					<c:if test="${pageNo>=1 }">
						<a href="admSel?pageNo=1">第一页</a>
						<a href="admSel?pageNo = ${pageNo-1 }">上一页</a>
			
					</c:if>
					<c:if test="${pageNo!=pageCount }">
						<a href="admSel?pageNo=${pageNo+1}">下一页</a>
						<a href="admSel?pageNo=${pageCount }">最后一页</a>
					</c:if>
					共有${pageCount} 页,这是第${pageNo}页
				</p>
				
				<table >
				<tr>
					<th>招聘会名称</th>
					<th>地址</th>
					<th>电话</th>
					<th>公司(名称/地址 /联系电话/公司类型)</th>
					<th> </th>
				</tr>
			
				<c:forEach items="${jbList}" var="JobFair">
					<tr >
						 
						<td  >${JobFair.add} </td>
						<td>${JobFair.date} </td>
						<td>${JobFair.detail} </td>
						<td >${JobFair.com} </td>
						<td><a href="InfoDel?jf_id=${JobFair.id}&flag=jfDel" >删除</a></td>
						<td><a href="adm1.jsp?jf_id=${JobFair.id}&add=${JobFair.add}&date=${JobFair.date} &detail=${JobFair.detail} ">修改</a></td>
					</tr>
				</c:forEach>
				</table>
				
				
				
				
			</div>
			
			
			
			
			
			<div id="tab2" class="tab_content" style="display: none; ">
				<table >
						<tr>
							<th>工号</th><th>姓名</th><th>地址</th><th>电话</th><th>学院</th>
						</tr>
					<c:forEach items="${teaList}" var="Teacher">
						<tr >
							<td >${Teacher.acc} </td>
							<td >${Teacher.name} </td>
							<td>${Teacher.add} </td>
							<td >${Teacher.tel} </td>
							<td >${Teacher.aca} </td>
							 
						</tr>
					</c:forEach>
				</table><br>
				<form action="InfoChange" method="post">
					请输入所要修改教师的信息<br>
					工号:<input type="text" name ="tea_acc"><br>
					姓名：<input type="text" name ="tea_name"><br>
					地址:<input type="text" name ="tea_add"><br>
					电话:<input type="text" name ="tea_tel"><br>
					学院:<input type="text" name ="tea_aca"><br>
					<input type="hidden" name="flag" value="changeTea" >  <br>
    				
					<input type="submit">
				
				</form>
				
				
			</div>
			
			
			
			
			
			
			<div id="tab3" class="tab_content" style="display: none; ">
				<table >
					<tr>
						<th>公司帐号</th>
						<th>公司名</th>
						<th>地址</th>
						<th>phone</th>
						<th>detail</th>
					</tr>
					<c:forEach items="${comList}" var="Company">
					<tr >
						<td >${Company.acc} </td>
						<td >${Company.name} </td>
						<td>${Company.add} </td>
						<td >${Company.tel} </td>
						<td  >${Company.detail} </td>
					</tr>
					</c:forEach>
				</table>
				<form action="InfoChange" method="post">
					请输入所要修改公司的信息<br>
					公司帐号:<input type="text" name ="com_acc"><br>
					公司名：<input type="text" name ="com_name"><br>
					地址:<input type="text" name ="com_add"><br>
					电话:<input type="text" name ="com_tel"><br>
					详细介绍:<input type="text" name ="com_detail"><br>
					<input type="hidden" name="flag" value="changeCom" > <br>
    				 
					<input type="submit">
				
				</form>
				
			</div>
			
			
			
			
			<div id="tab4" class="tab_content" style="display: none; ">
				<table >
						<tr>
							<th>学号</th><th>姓名</th><th>年龄</th><th>学分</th><th>学院</th><th> </th>
						</tr>
					<c:forEach items="${StuAllSel}" var="Student">
						<tr  > 
							<td >${Student.acc} </td>
							<td >${Student.name} </td>
							<td>${Student.age} </td>
							<td >${Student.grade} </td>
							<td >${Student.aca} </td>
							 
							<td><a href="InfoDel?stu_acc=${Student.acc}&flag=stuDel" >删除</a></td>
							 
						</tr>
					</c:forEach>
				</table>
				 <br/><br/>
				<form action="InfoChange" method="post">
					请输入所要修改学生的信息<br>
					学号:<input type="text" name ="stu_acc"><br>
					姓名:<input type="text" name ="stu_name"><br>
					年龄:<input type="text" name ="stu_age"><br>
					学分:<input type="text" name ="stu_grade"><br>
					学院:<input type="text" name ="stu_aca"><br>
					<input type="hidden" name="flag" value="changeStu"   > 
    				 
					<input type="submit">
				
				</form>
			</div>
			
			<div id="tab5" class="tab_content" style="display: none; ">
				<table >
				<tr>
					<th>公司号</th>
					<th>学生学号</th>
					<th>签约日期</th>
					<th>薪资</th>
					<th>合约期</th>
					<th>  </th>
				</tr>
			
				<c:forEach items="${jobList}" var="Job">
					<tr >
						 
						<td >${Job.com_acc} </td>
						<td >${Job.stu_acc} </td>
						<td>${Job.time} </td>
						<td>${Job.salary} </td>
						<td >${Job.contract} </td>
						<td ><a href="InfoChange?jid=${ Job.jid}&flag=delJob">删除</a></td>
					</tr>
				</c:forEach>
				</table>
				
				<form action="InfoChange" method="post">
					请输入所要添加就业的信息<br>
					学生学号:<input type="text" name ="job_sacc"><br>
					公司号：<input type="text" name ="job_cacc"><br>
					时间:<input type="date" name ="job_time"  ><br> 
					 薪资:<input type="text" name ="job_salary"><br>
					合约期:<input type="text" name ="job_contract"><br>
					<input type="hidden" name="flag" value="addJob" > <br>
    				
					<input type="submit">
				
				</form>
				
			</div>
			
			<div id="tab6" class="tab_content" style="display: none; ">
				<form action="InfoChange" method="post">
					请输入所要添加或者删除帐号的信息<br>
					帐号:<input type="text" name ="acc" style="magin:0;"><br>
					<input type="radio" name="table" value="Company" style="margin:0;width:20px;">Company 
    				<input type="radio" name="table" value="Teacher" style="margin:0;width:20px;">Teacher  
    				<input type="radio" name="table" value="Student" style="margin:0;width:20px;">Student <br>  <br> 
    				<input type="radio" name="flag1" value="AccDel" style="margin:0;width:20px;">删除
    				<input type="radio" name="flag1" value="AccAdd" style="margin:0;width:20px;">添加 <br>
					<input type="hidden" name="flag" value="addAcc" > <br>
    				
					<input type="submit">
				
				</form>
				
			</div>
			
			<div id="tab7" class="tab_content" style="display: none; ">
				 
				<form action="AccChange" method="post" >
					你的 	帐号：<input  type="text" name="acc" value="${acc}" ><br>
					请输入密码：<input type="password"><br>
					请确认密码：<input type="password" name="pwd"><br>
					<input type="hidden" value="AccChange" name="flag">
					<input type="hidden" value="Adm" name="table">
					<input type="submit" value="保存" style="width:50px;">
				</form>
				<p>添加管理员</p>
				<form action="AccChange" method="post" >
					请输入帐号：<input  type="text" name="acc"   ><br>
					请输入密码：<input type="password"><br>
					请确认密码：<input type="password" name="pwd"><br>
					请输入姓名：<input type="text" name="name"><br>
					<input type="hidden" value="Adm" name="table">
					<input type="hidden" value="AccAdd" name="flag">
					<input type="submit" value="保存" style="width:50px;">
				</form>
			
			</div>
		 
	
	</div>

</body>
</html>