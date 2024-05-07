
function change_list(om) {

	var mSubObj = om.getElementsByTagName("div")[0];
	
	mSubObj.style.display = "block";
	mSubObj.style.backgroundColor="#f0f0f0 ";
	
	var s = document.getElementById("d2");
	d2.style.backgroundColor="#f0f0f0 ";
	
	
	var s1 = document.getElementById("a0");
	s1.style.backgroundColor="#f0f0f0 ";
	s1.style.color="black";
}

function change_list1(om) {
	var mSubObj = om.getElementsByTagName("div")[0];
	mSubObj.style.display = "none";
	
	var s = document.getElementById("d2");
	d2.style.backgroundColor="white";
	
	var s1 = document.getElementById("a0");
	s1.style.backgroundColor="#3385ff";
	s1.style.color="white";

}
