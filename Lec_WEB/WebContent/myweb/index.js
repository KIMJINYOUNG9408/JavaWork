// header에 마우스커서가면 숨김말 생겼다 사라졌다하기.
$(document).ready(function(){
	$("#z").hover(function(){
		$("#logo_hide").css("display", "block");
    },
    function(){
		$("#logo_hide").css("display", "none");
    });
});


// // 클릭했을때 1번~10번까지 확대시키고 변경시키려는 func
// function ranksee(i){
//     var name = "rank" + i;
//     var name2 = "rank" + i + "_img";
//     var name3 = "rank" + i + "_p";
//     document.getElementById(name).style.height = "100px";   
//     document.getElementById(name2).style.height = "100px"; 
//     document.getElementById(name).style.fontSize = "20px";
//     document.getElementById(name3).innerHTML = "twice ";
       
// }

// // 클릭했을때 1번~10번까지 확대된걸 다시 되돌리려는 func
// function rankhind(i){
//     var name = "rank" + i;
//     var name2 = "rank" + i + "_img";
//     var name3 = "rank" + i + "_p";
//     document.getElementById(name).style.height = "50px";   
//     document.getElementById(name2).style.height = "50px"; 
//     document.getElementById(name).style.fontSize = "15px";
//     document.getElementById(name3).innerHTML = "heart";
       
// }

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

    var $banner = $(".banner").find("ul");

    var $bannerWidth = $banner.children().outerWidth();
    var $bannerHeight = $banner.children().outerHeight(); 
    var $bannerLength = $banner.children().length;
    var rollingId;

   
    rollingId = setInterval(function() { rollingStart(); }, 3000);

    
    function rollingStart() {
        $banner.css("width", $bannerWidth * $bannerLength + "px");
        $banner.css("height", $bannerHeight + "px");
        
        $banner.animate({left: - $bannerWidth + "px"}, 1500, function() { 
            $(this).append("<li>" + $(this).find("li:first").html() + "</li>");            
            $(this).find("li:first").remove();          
            $(this).css("left", 0);
            
        });
    }
}); 

$(document).ready(function(){
		 
    // JSON
    url = "chart_rankmain.json"
    $.ajax({
        url : url, 
        type : "GET",
        cache : false,
        success : function(data, status){
            if(status == "success") parseSON(data);
        }
    });		
 
});

function parseSON(jsonObj) {
var row = jsonObj.chart_rank.row;
var music_10 = new Array();




music_10[0] = "<tr style='height: 50px;'><th style='width: 5%;'>R</th><th style='width: 10%;'>앨범</th>";	
music_10[0] += "<th style='width: 20%;'>가수</th><th style='width: 45%;'>제목</th><th style='width: 10%;'>Heart</th><th style='width: 10%;'>Play</th></tr>" 
music_10[1] = "<tr style='height: 50px;'><th style='width: 5%;'>R</th><th style='width: 10%;'>앨범</th>";	
music_10[1] += "<th style='width: 20%;'>가수</th><th style='width: 45%;'>제목</th><th style='width: 10%;'>Heart</th><th style='width: 10%;'>Play</th></tr>" 


for (i = 0; i < row.length; i++) {
    if(i<10){
   music_10[0] += "<tr>"; 
   music_10[0] += "<td>"+ (i+1) + "</td>";
   music_10[0] += "<td style='width: 10%; height:50px'> <img src='" + row[i].Image  +  "' width='100%' height='100%' alt='' style='float: left; padding: 0px; margin: 0px; margin-top: 7px;'></td>";
   music_10[0] += "<td>" + row[i].Artist + "</td>";
   music_10[0] += "<td>" + row[i].Name + "</td>";
   
   music_10[0] += "<td>" + "<i class='fas fa-heart' id='heart" + (i+1)  + "'style= 'color:gray;' ondblclick=" 
       + '"'
       + "if(document.getElementById('heart" + (i+1) + "').style.color == 'gray'){"                   
       + "this.style.color = 'red'}"
       + "else{ this.style.color = 'gray'}"
       + '"'
       +"></i>" + "</td>";
       music_10[0] += "<td>" + "<i class='fas fa-play search_button'></i>" + "</td>";
       music_10[0] += "</tr>"; 
    } else {
        music_10[1] += "<tr>"; 
        music_10[1] += "<td>"+ (i+1)  + "</td>";
        music_10[1] += "<td style='width: 10%; height:50px'> <img src='" + row[i].Image  +  "' width='100%' height='100%' alt='' style='float: left; padding: 0px; margin: 0px; margin-top: 7px;'></td>";
        music_10[1] += "<td>" + row[i].Artist + "</td>";
        music_10[1] += "<td>" + row[i].Name + "</td>";
        
        music_10[1] += "<td>" + "<i class='fas fa-heart' id='heart" + (i+1)  + "'style= 'color:gray;' ondblclick=" 
            + '"'
            + "if(document.getElementById('heart" + (i+1) + "').style.color == 'gray'){"                   
            + "this.style.color = 'red'}"
            + "else{ this.style.color = 'gray'}"
            + '"'
            +"></i>" + "</td>";
            music_10[1] += "<td>" + "<i class='fas fa-play search_button'></i>" + "</td>";
            music_10[1] += "</tr>";  
    }

    $("#chart20_main").html(music_10[0])
}   

var i = 1;
var interval = setInterval(function(){
    	
    if(i>1){		
     i=0;		
    }
         $("#chart20_main").html(music_10[i])
     i++;	  
    }, 4000);
     

  



}





