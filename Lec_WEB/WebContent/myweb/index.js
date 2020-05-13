// header에 마우스커서가면 숨김말 생겼다 사라졌다하기.
$(document).ready(function(){
	$("#z").hover(function(){
		$("#logo_hide").css("display", "block");
    },
    function(){
		$("#logo_hide").css("display", "none");
    });
});


// 클릭했을때 1번~10번까지 확대시키고 변경시키려는 펑 ~~~ 션
function ranksee(i){
    var name = "rank" + i;
    var name2 = "rank" + i + "_img";
    var name3 = "rank" + i + "_p";
    document.getElementById(name).style.height = "100px";   
    document.getElementById(name2).style.height = "100px"; 
    document.getElementById(name).style.fontSize = "20px";
    document.getElementById(name3).innerHTML = "twice ";
       
}

// 클릭했을때 1번~10번까지 확대된걸 다시 되돌리려는 펑 ~~~ 션
function rankhind(i){
    var name = "rank" + i;
    var name2 = "rank" + i + "_img";
    var name3 = "rank" + i + "_p";
    document.getElementById(name).style.height = "50px";   
    document.getElementById(name2).style.height = "50px"; 
    document.getElementById(name).style.fontSize = "15px";
    document.getElementById(name3).innerHTML = "heart";
       
}

