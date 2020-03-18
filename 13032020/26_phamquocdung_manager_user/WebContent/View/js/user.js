/**
 * 
 */
function backPage() {
	window.history.back();
}
var isHide = true;
function toggleJp () {
    var jpLevel = document.getElementsByClassName("jp-level");
    if (jpLevel[0].style.visibility != undefined && jpLevel[0].style.visibility == 'hidden') {
    	isHide = false;
    }
    if(isHide)
    {	
        for(var i = 0; i < jpLevel.length ; i++){
            jpLevel[i].style.visibility  = 'hidden';
        }
        isHide= false;
    }
    else {
        for(var i = 0; i < jpLevel.length ; i++){
            jpLevel[i].style.visibility = 'visible';
        }
        isHide = true;
    }
    // jpLevel.style.display = "none";
}