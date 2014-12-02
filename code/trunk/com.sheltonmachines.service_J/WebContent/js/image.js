$(document).ready(function(){
	var count =0;
	$('#start').attr("disabled", false);
	$('#stop').attr("disabled", true);
	function calculateCount(callback){
	 $.ajax({type: 'POST',
		 url: "http://localhost:7001/com.sheltonmachines.service/api/manage/display/count/images/",
         cache: false,
         success: function(data){
        	       callback(data[0].number);
        	       ;
         			}
         
		               });
  
	}
	 
	
	 $('#start').on('click',function(){
		 $('#start').attr("disabled", true);
		 $('#stop').attr("disabled", false);
		 var intervalId =1000;
			var index=1;
		intervalId = setInterval ( function(){
			
			calculateCount(function(value){
				 count = value;
				 console.log("count="+count);
			 });
			
			      $.ajax({type: 'POST',
		
			              url: "http://localhost:7001/com.sheltonmachines.service/api/manage/display/images/"+index,
			              cache: false,
			              success: function(images){ 
			            	  $.each(images,function(i,image)
							{  console.log(image.IMAGE_PATH);
							   
									$("img").attr("src", image.IMAGE_PATH);
									$("img").fadeIn(1000);
											
										
							
							}) ;
			            	  console.log("index="+index);
			            	 if(index<=count){
			            		 index++;}
			            	 else{
			            		 index= count;
			            	 }
	        	  }
			     
							
							}); 
			      
			      $('#stop').on('click',function(){
			    	  console.log("stop clicked");
						clearInterval(intervalId);
					});
		
		},3000) ;
		
		
	         
	           
				
		
	 });
	
	
});
