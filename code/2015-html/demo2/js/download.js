function change(id) {
//	if(id == 1) {
//		var obj = document.getElementById("li1");
//		//  	if (obj.getAttribute("src") == "img/l11.png") {
//		obj.setAttribute("src", "img/download/l12.png");
//		//  	} else { 
//		//      	obj.setAttribute("src", "img/l11.png"); 
//		//  }
//	}
//	if(id == 2) {
//		document.getElementById("li2").setAttribute("src", "img/download/l22.png");
//	}
	if(id == 11) {
		document.getElementById("i11").setAttribute("src", "img/download/window_download2.png");
	}
	if(id == 12) {
		document.getElementById("i12").setAttribute("src", "img/download/window_download2.png");
	}

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

