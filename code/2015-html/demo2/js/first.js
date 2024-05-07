function show_hidden(obj) {

							　　
							if(obj.style.display == 'block') {

								　　
								obj.style.display = 'none';

								　　
							} else {

								　　　　　
								obj.style.display = 'block';

								　　
							}

						}

						var sh = document.getElementById("showHidden");

						sh.onclick = function() {

							var div1 = document.getElementById("div1");

							var div2 = document.getElementById("div2");

							show_hidden(div1);

							show_hidden(div2);

							return false;

						}