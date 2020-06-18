$(document).ready(function(){
   data = "parent=0&depth=1"; 
   loadPage();
   
   
   $(".cate span:nth-child(1) select").change(function(){
	  if($(".cate span:nth-child(1) select").val() == '--선택하세요--'){
	      $('#second').attr("disabled", true);
	      $('#second').html("");
	      $('#third').attr("disabled", true);
	      $('#third').html("");
	  }
	  
	
	  
      data = "parent="+$('#first option:selected').attr('data-uid')+"&depth="+(parseInt($('#first option:selected').attr('data-depth'))+1);      
      loadPage();
   });
   
   $(".cate span:nth-child(2) select").change(function(){
	   if($(".cate span:nth-child(2) select").val() == '--선택하세요--'){
		      
		      $('#third').attr("disabled", true);
		      $('#third').html("");
		  }
	   
	   data = "parent="+$('#second option:selected').attr('data-uid')+"&depth="+(parseInt($('#second option:selected').attr('data-depth'))+1);     
      loadPage();
   });
});

function loadPage(){
   $.ajax({
      url : "cate_list.ajax",     
      type : "POST",
      data : data,
      cache : false,
      success : function(data, status){
         if(status == "success"){           
        	         	 
        	 
            updateList(data);
      
         }
      }
   })
}

function updateList(jsonObj){
   result = "<option>--선택하세요--</option>"; 
   
   if(jsonObj.status == "OK"){
      
      var count = jsonObj.count;
      
      
      var items = jsonObj.data;   
      
      for(i = 0; i < count; i++){
         $('.cate span:nth-child('+ items[0].depth + ') select').attr("disabled", false);
         result += "<option data-uid='"+ items[i].uid+"' data-depth='"+ items[i].depth+"'>" + items[i].name + "</option>"
         
      } 
      $('.cate span:nth-child('+ items[0].depth + ') select').html(result);
      $('.cate span:nth-child('+ (items[0].depth + 1) + ') select').attr("disabled", true);
      $('.cate span:nth-child('+ (items[0].depth + 1) + ') select').html("");
      return true;
   } 
   return false;
} 