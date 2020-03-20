/**
 * 
 */
function backPage() {
	window.location.href = 'backToList.do';
}
function toggleJp () {
    var jpLevel = document.getElementsByClassName("jp-level");
    var style = jpLevel[0].style.visibility;
    if (style == 'hidden' && style != undefined) {
        for(var i = 0; i < jpLevel.length ; i++){
            jpLevel[i].style.visibility = 'visible';
        }
    } else {
        for(var i = 0; i < jpLevel.length ; i++){
            jpLevel[i].style.visibility  = 'hidden';
        }
    }
}