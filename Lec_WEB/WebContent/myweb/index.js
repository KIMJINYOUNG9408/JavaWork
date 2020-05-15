// header에 마우스커서가면 숨김말 생겼다 사라졌다하기.
$(document).ready(function(){
	$("#z").hover(function(){
		$("#logo_hide").css("display", "block");
    },
    function(){
		$("#logo_hide").css("display", "none");
    });
});


// 클릭했을때 1번~10번까지 확대시키고 변경시키려는 func
function ranksee(i){
    var name = "rank" + i;
    var name2 = "rank" + i + "_img";
    var name3 = "rank" + i + "_p";
    document.getElementById(name).style.height = "100px";   
    document.getElementById(name2).style.height = "100px"; 
    document.getElementById(name).style.fontSize = "20px";
    document.getElementById(name3).innerHTML = "twice ";
       
}

// 클릭했을때 1번~10번까지 확대된걸 다시 되돌리려는 func
function rankhind(i){
    var name = "rank" + i;
    var name2 = "rank" + i + "_img";
    var name3 = "rank" + i + "_p";
    document.getElementById(name).style.height = "50px";   
    document.getElementById(name2).style.height = "50px"; 
    document.getElementById(name).style.fontSize = "15px";
    document.getElementById(name3).innerHTML = "heart";
       
}

 // 실검 ajax func
$(document).ready(function(){
		 
  // JSON
  url = "search_rank.json"
  $.ajax({
      url : url, 
      type : "GET",
      cache : false,
      success : function(data, status){
          if(status == "success") parseJSON(data);
      }
  });		

});

function parseJSON(jsonObj) {
var row = jsonObj.search_rank.row;

var word = new Array();


for(i=0; i < row.length; i++){
     word[i] =   (i+1) + "위 : " +  row[i].name
 }
 
var i = 0;

var interval = setInterval(function(){	
if(i>20){		
 i=0;		
}
     $("#ranking").html(word[i])
 i++;	  
}, 1500);			//15초마다 갱신


}

$(document).ready(function() {
    //사용할 배너
    var $banner = $(".banner").find("ul");

    var $bannerWidth = $banner.children().outerWidth();//배너 이미지의 폭
    var $bannerHeight = $banner.children().outerHeight(); // 높이
    var $bannerLength = $banner.children().length;//배너 이미지의 갯수
    var rollingId;

    //정해진 초마다 함수 실행
    rollingId = setInterval(function() { rollingStart(); }, 3000);//다음 이미지로 롤링 애니메이션 할 시간차


    
    function rollingStart() {
        $banner.css("width", $bannerWidth * $bannerLength + "px");
        $banner.css("height", $bannerHeight + "px");
        //alert(bannerHeight);
        //배너의 좌측 위치를 옮겨 준다.
        $banner.animate({left: - $bannerWidth + "px"}, 1500, function() { //숫자는 롤링 진행되는 시간이다.
            //첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
            $(this).append("<li>" + $(this).find("li:first").html() + "</li>");
            //뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
            $(this).find("li:first").remove();
            //다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
            $(this).css("left", 0);
            //이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
        });
    }
}); 




