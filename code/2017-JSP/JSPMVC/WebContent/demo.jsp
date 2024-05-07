<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	<style  type="text/css">
		
		#p1{
			padding-left:20px;
		}
		
		#d2{
			margin-top:40px;
		}
		
		#t1,#t2 {
			
			align:center;
		}
		#t2{
			
		}
		#t2 td{
			border:none;
			text-align:center;
		}
		#t1  td{
			border:1px solid #808080;
			text-align:center;
			
			
		}
	</style>
	<script type="text/javascript">
	
	
	
	 function ShowElement(element)
	{
		
	var oldhtml = element.innerHTML;
	var newobj = document.createElement('input');
	//创建新的input元素
	newobj.type = 'text';
	newobj.value = oldhtml;
	//为新增元素添加类型
	newobj.onblur = function(){
		element.innerHTML = this.value ? this.value : oldhtml;
		//当触发时判断新增元素值是否为空，为空则不修改，并返回原有值 
		}
	element.innerHTML = '';
	element.appendChild(newobj);
	newobj.focus();
	
	} 
	 function myfun(){  
		     	document.form1.method="post";  
		        
		        document.form1.action="addCourse";  
		        document.form1.submit();  
		     }  
	 function myfun2(){
		 
		 var id=document.getElementById("td1").innerHTML ;
		 document.getElementById("in1").value=id;
		 document.getElementById("in2").value=document.getElementById("td2").innerHTML ;
		 
	 }
	 
	</script>
	

</head>
<body>	
	
	<div id="d1">
		<h2>所有课程信息</h2>
		
		<p id="p1">
		
		<c:if test="${pageNo>=1 }">
			<a href="listUser?pageNo=1">第一页</a>
			<a href="listUser?pageNo = ${pageNo-1 }">上一页</a>
			
		</c:if>
		<c:if test="${pageNo!=pageCount }">
			<a href="listUser?pageNo=${pageNo+1}">下一页</a>
			<a href="listUser?pageNo=${pageCount }">最后一页</a>
		</c:if>
		</p>
		<table id="t1">
			<tr>
				<th>id</th><th>name</th><th>date</th> <th>teacher</th>
			</tr>
			
				<c:forEach items="${courseList}" var="course">
					<tr id="mytr">
						<td id="td1" style="width:30px">${course.id} </td>
						<td id="td2"style="width:60px" >${course.name} </td>
						<td id="td3" style="width:100px">${course.date} </td>
						<td id="td4"style="width:60px" ondblclick="ShowElement(this)">${course.teacher} </td>
						<td><a href="deleteCourse?delNo=${course.id } " > 删</a></td>
						
					</tr>
				</c:forEach>
		</table>
		<form id="form1"name="form1" action="changeCourse" method="post"  style="padding-left:-10px;">
			<table id="t2">
				<tr>
					<td style="width:30px"><input  type="text" name="cid"style="width:30px"></td>
					<td style="width:60px"><input  type="text" name ="cname"style="width:60px" ></td>
					<td style="width:100px"><input type="text" name= "cdate" style="width:100px"></td>
					<td style="width:60px"><input  type="text" name="cteacher"style="width:60px"></td>
					<td style="width:10px"><input style="background:transparent;border:0" type="submit" value="change" /> </td>
					<td style="width:10px"><input style="background:transparent;border:0" type="submit" value="add"  onclick="myfun()"/></td>
				</tr>
			</table>
			
			
			
			
			
			
		</form>
		
		共有${pageCount} 页,这是第${pageNo}页
		
	</div>	
	
	
	<div id="d2">
		
		
	</div>	
		


	
	
</body>
</html>