<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>学生子系统</title>
 
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
	#tab5 input{
		 
		height:20px;
		width:150px;margin:10px;
	}


	
	
	
	
	
	 

</style>
<script type="text/javascript">
function validate() {
    var pwd1 = document.getElementById("pwd1").value;
    var pwd2 = document.getElementById("pwd2").value;
 	
    if(pwd1 == pwd2) {
        document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
        document.getElementById("submit").disabled = false;
    }
    else {
        document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
      document.getElementById("submit").disabled = true;
    }
}

</script>

</head>
<body>
		<div id="main">
			<div id="main_d1">
					<div style="margin:0 auto;border:1px solid #F5F5F5;width:700px;height:100px;">
					<p id="top_p1">欢迎${stuSel.name}来到学生子系统
					<input style="margin-left:300px;"
					 type="button" value="退出" onclick="exit();" /> </p></div>
				</div>
			<div id="main_d2">
					<ul  class="tabs">
						<li class="active"> 
							<a href="#tab1" class="active">我的个人信息</a></li>
						<li><a href="#tab2">我的就业信息</a></li>
						<li><a href="#tab3" >公司信息</a></li>
						<li><a href="#tab4" >招聘会信息</a></li>
						<li style="border-right:1px solid #6495ED; "><a href="#tab5" >修改密码</a></li>
					</ul>
			</div>
			 
			
		</div>
		<div id="bottom">
			
			<div id="tab1" class="tab_content" style="display: block;">
				<p>姓名：${stuSel.name}</p>
				<p>年龄：${stuSel.age }</p>
				<p>学分：${stuSel.grade}</p>
				<p>学院：${stuSel.aca }</p>
				<p>帐号：${stuSel.acc }</p>
				<p>密码：${stuSel.pwd }</p>
				 
				
			</div>
			<div id="tab2" class="tab_content" style="display: none; ">
				<table>
					<tr>
						<th>签订期</th>
						<th>薪资</th>
						<th>合约期</th>
						<th>公司名称</th>
						<th>公司地址</th>
						<th>电话</th>
						<th>详解</th>
					</tr>
					<tr>
						<td>${stuJobSel1.time}</td>
						<td>${stuJobSel1.salary }</td>
						<td>${stuJobSel1.contract}</td>
						<td>${stuJobSel2.name }</td>
						<td>${stuJobSel2.add }</td>
						<td>${stuJobSel2.tel }</td>
						<td>${stuJobSel2.detail }</td>
					</tr>
				</table>
			</div>
			<div id="tab3" class="tab_content" style="display: none; ">
				<table >
				<tr>
					<th>名称</th>
					<th>地址</th>
					<th>电话</th>
					<th>详介</th>
				</tr>
			
				<c:forEach items="${comList}" var="Company">
					<tr >
						<td  >${Company.name} </td>
						<td>${Company.add} </td>
						<td >${Company.tel} </td>
						<td  >${Company.detail} </td>
						
						
					</tr>
				</c:forEach>
				</table>
			
			
			
			</div>
			<div id="tab4" class="tab_content" style="display: none; ">
				<table >
				<tr>
					<th>招聘会名称</th>
					<th>时间</th>
					<th>招聘简介</th>
					<th>公司/地址/电话/公司类别</th>
				</tr>
			
				<c:forEach items="${jbList}" var="JobFair">
					<tr >
						<td >${JobFair.add} </td>
						<td>${JobFair.date} </td>
						<td>${JobFair.detail} </td>
						<td >${JobFair.com} </td>
						
						
						
					</tr>
				</c:forEach>
				</table>
			
			
			
			</div>
			<div id="tab5" class="tab_content" style="display: none; ">
				<form action="AccChange" method="post" >
					你的 帐号：<input  type="text" name="acc" value="${acc}"  ><br>
					请输入密码：<input type="password" id="pwd1"><br>
					请确认密码：<input type="password" name="pwd" id= "pwd2"  onblur="validate()"> <br>
					<input type="hidden" value="Student" name="table">
					<input type="hidden" value="AccChange" name="flag">
					 
					<input type="submit" value="保存" style="width:50px;" id="submit"  ><span id="tishi"></span>
				</form>
				 
			</div>
			
		</div>

	
</body>
</html>