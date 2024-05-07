<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		 
		<title>就业管理系统</title>

		<link rel="stylesheet" href="h5/pageSwitch.min.css">
		 
		 
		<style type="text/css">
			* {
				padding: 0;
				margin: 0;
			}
			 body {
				height: 100%;
			}
			
			#container {
				width: 100%;
				height: 700px;
				overflow: hidden;
			}
			
			.sections,
			.section {
				height: 100%;
			}
			
			#container,
			.sections {
				position: relative;
			}
			
			.section {
				background-color: #000;
				background-size: cover;
				background-position: 50% 50%;
				text-align: center;
				color: white;
			}
			
			#section0 {
				background-image: url('img/bg1.jpg');
			}
			
			#section1 {
				background-image: url('img/bg2.jpg');
			}
			
			#section2 {
				background-image: url('img/bg3.jpg');
			}
			
			#section3 {
				background-image: url('img/bg4.jpg');
			}
			#div2 {
			
			
			}
			 #div2 input{
			 	 background-color:	#FDF5E6;
			 	 border:1px solid #6495ED;
			 }
			 
			 
		</style>
		 

	</head>

	<body>
		<div id="container">
			<div class="sections">
				<div class="section" id="section0">
				
				</div>
				<div class="section" id="section1">
					
				</div>
				<div class="section" id="section2">
					
				</div>
				<div class="section" id="section3">
					
				</div>
			</div>

		</div>
		<div id="hidden0">
			 
			<div id="main" style="	border-radius:3px;
				background-image: url('img/33.png');width:350px;height:300px;
				position: absolute;top:150px;right:200px;background-color: white; " >
				 
					<div id="div2" >
							 
							<form action="LoginCheck" method="post" style="margin-left:40px;">
								 <br> <br> <br>  
								 帐号：<input type="text" name = "acc" required  style="width:200px;height: 30px; ">
								<br>
								 密码：<input type="password" name = "pwd"  required   style="width:200px;height: 30px; " >
								<br>
								<input type="radio" name="table" value="Student"checked  style="margin-top: 30px;">学生  
    							<input type="radio" name="table" value="Company"  >公司  
    							<input type="radio" name="table" value="Teacher"  >教师  
    							<input type="radio" name="table" value="Adm"  >管理员 <br><br>
								<input type = "submit" 
									style="height:30px;width:100px;margin-left: 30px ;margin-top: 20px; "value="登录">
								<input type="reset" style="height:30px;width:100px; "name="重置">
							</form><br>
								<p style="margin-left: 120px ;">${info}</p><br>
					 </div>
				 

			</div>
			 
			 

		</div>

		<script src="h5/jquery-1.11.0.min.js" type="text/javascript"></script>

		<script src="h5/pageSwitch.min.js"></script>
		<script>
			$("#container").PageSwitch({
				direction: 'horizontal',
				easing: 'ease-in',
				duration: 1000,
				autoPlay: true,
				loop: 'false'
			});
		</script>

	</body>
</html>