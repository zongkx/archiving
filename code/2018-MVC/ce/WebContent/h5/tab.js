function exit(){
    window.open("first.jsp",'_top')
      }
function editMethod(tag){// tag就是<a>标签对象
    var tdTag = tag.parentNode;// parentNode父节点，此处得到<td>节点
    var pwdTdTag = tdTag.previousSibling;// 得到包含密码的<td>标签
    var nameTdTag = pwdTdTag.previousSibling;// 得到包含用户名的<td>标签
    alert("I am an alert box!!")
    var pwdValue = pwdTdTag.firstChild.nodeValue;//firstChild第一个子标签，nodeValue是取得文本值，此处得到password
    var nameValue = nameTdTagnameTdTag.firstChild.nodeValue;//此处得到name
    document.getElementById("hi1").innerHTML=pwdValue;
   // location.href="${pageContext.request.contextPath}/EditServlet?username="+nameValue+"&password="+pwdValue;
}
 
function delMethod(tag){
    var tdTag = tag.parentNode;// parentNode父节点，此处得到<td>节点
    var pwdTdTag = tdTag.previousSibling;// 得到包含密码的<td>标签
    var nameTdTag = pwdTdTag.previousSibling;// 得到包含用户名的<td>标签
     
    var pwdValue = pwdTdTag.firstChild.nodeValue;//firstChild第一个子标签，nodeValue是取得文本值，此处得到password
    var nameValue = nameTdTagnameTdTag.firstChild.nodeValue;//此处得到name
     
    //location.href="${pageContext.request.contextPath}/DelServlet?username="+nameValue+"&password="+pwdValue;
}



$(document).ready(function() {

	
	$(".tab_content").hide(); //Hide all content  
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab  
	$(".tab_content:first").show(); //Show first tab content  

	
	$("ul.tabs li").click(function() {
		 
		$("ul.tabs li").removeClass("active"); //Remove any "active" class  
		$(this).addClass("active"); //Add "active" class to selected tab  
		$(".tab_content").hide(); //Hide all tab content  
		var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content  
		
		$(activeTab).fadeIn(); //Fade in the active content
		
		return false;
	});

});
