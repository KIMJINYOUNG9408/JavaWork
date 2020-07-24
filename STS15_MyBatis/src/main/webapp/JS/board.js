$(document).ready(function(){
	// 게시판 목록 1페이지 로딩
	
	var a = $.trim($('#a').html());
	
	
	loadPage(a);
	
	$("#frmWrite").submit(function(){
		
		return chkWrite();
	});
	
	

	
	
	
	
});

function loadPage(page){
	
	$.ajax({
		
		url : "list.ajax?uid=" + page
		, type : "GET"
		, cache : false
		, success : function(data, status){
			
			if(status == "success"){
				
				if(updateList(data)){
					
					$(".deletebtn").click(function(){
						
						deleteUid($(this).attr('data-uid'));
						
						
					});
					// 업데이트된 list 에 필요한 이벤트 가동
					
					// ★ 만약 위 코드를 $(document).ready() 에 두면 동작 안할것이다.!
				}
				
			}			
		}
	});

}

function updateList(jsonObj){
	result = ""; 
	
	if(jsonObj.status == "OK"){
		
		var count = jsonObj.count;
		
		var i;
		var items = jsonObj.data;  
		for(i = 0; i < count; i++){
			result += "<tr>\n";
			result += "<td>" +"<i class='fas fa-user-tie'></i>" + "</td>\n";			
			

			result += "<td>" + "익명 " +  (i+1)+ "]</td>\n";			

			result += "<td>" + items[i].content + "</td>\n";			

			result += "<td>" + items[i].regdate + "</td>\n";
			result += "<td>" + "<button class='updatebtn'" + " type='button'>수정</button> "+  "</td>\n";
			result += "<td>" + "<button class='deletebtn' data-uid='"+ items[i].uid + "' type='button'>삭제</button> "+  "</td>\n";

			
			
			result += "</tr>\n";
		} // end for
		$("#list tbody").html(result);
		
		return true;
	}
	
} // end updateList()


function chkWrite(){
	var data = $("#frmWrite").serialize();
	var c = $.trim($('#a').html());
	$.ajax({
		url : "writeOk.ajax",
		type : "POST",
		cache : false,
		data : data,  // POST 로 ajax request 하는 경우 parameter 담기
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
				loadPage(c);
				
				} else {
					alert("INSERT 실패 " + data.status + " : " + data.message);
				}
			}
		}
	});
	
	
	$('#frmWrite')[0].reset();
	
	
	
	return false;
	
} // end chkWrite()

function deleteUid(uid){
	var c = $.trim($('#a').html());
	if(!confirm(uid + "글을 삭제 하시겠습니까?")) return false;
	
	// POST 방식 
	$.ajax({
		url : "deleteOk.ajax",
		type : "POST",
		data : "uid=" + uid,
		cache : false,
		success : function(data, status){
			if(status == "success"){
				if(data.status == "OK"){
					alert("DELETE 성공" + data.count + "개");
					loadPage(c); // 현재 페이지 리로딩
				} else {
					alert("DELETE 실패" + data.message);
					
				}
			}
		}
		
	});
	
	return false;
		
		
	
} // end deleteUid(uid)
