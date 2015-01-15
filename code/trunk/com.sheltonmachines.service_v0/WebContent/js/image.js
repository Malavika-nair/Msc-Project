$(document).ready(function(){
	var count =0;
	$('#start').attr("disabled", false);
	$('#stop').attr("disabled", true);
	function calculateCount(callback){
	 $.ajax({type: 'POST',
		 url: "http://localhost:7001/com.sheltonmachines.service/api/v1/images/count/",
         cache: false,
         success: function(data){
        	       callback(data[0].number);
        	       
         			},
          error: function(jqXHR, textStatus, errorThrown) {
    					console.log("Error!!"+ jqXHR.responseText);
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
		
			              url: "http://localhost:7001/com.sheltonmachines.service/api/v1/images/path/"+index,
			              cache: false,
			              success: function(images){ 
			            	  
			            	  $.each(images,function(i,image)
							{  console.log(image.IMAGE_PATH);//debug
							   
									$("img").attr("src", image.IMAGE_PATH);
									//$("img").fadeIn(1000);
											
										
							
							}) ;
			            	  console.log("index="+index); //debug
			            	 if(index<count){
			            		 index++;}
			            	 else{
			            		 index= count;
			            		 
			            	 }
			            	
			            	 
	        	  },
	        	  error: function(jqXHR, textStatus, errorThrown) {
						console.log("Error!!"+ jqXHR.responseText);
					}
			     
							
							}); 
			      
			      $('#stop').on('click',function(){
			    	  $('#start').attr("disabled", false);
			    	  $('#stop').attr("disabled", true);
						clearInterval(intervalId);
						index=1;
					});
			      
		
		},200) ;
		
		
		$('#start').attr("disabled", false);
	           
				
		
	 });
	
	
});
