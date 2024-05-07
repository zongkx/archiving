function change_list(om) {

	var mSubObj = om.getElementsByTagName("div")[0];
	mSubObj.style.display = "block";

}

function change_list1(om) {
	var mSubObj = om.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";

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
