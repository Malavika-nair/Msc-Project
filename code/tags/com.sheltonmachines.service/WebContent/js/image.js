
$(document).ready(function(){
	var count=0;
	$('#start').attr("disabled", false);
	$('#stop').attr("disabled", true);
	function calculateCount(callback){
	//an Ajax request send to the URI of the resource to get the count of the image paths  
	 $.ajax({type: 'POST',
		 url: "http://localhost:7001/com.sheltonmachines.service/api/v1/images/count/",
         cache: false,
         success: function(data){
        	       callback(data[0].number);
        	       
         			},
         // handling the error if request fails
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
			     // Ajax request is send to the URI of the resource to get the image path
			      $.ajax({type: 'POST',
		
			              url: "http://localhost:7001/com.sheltonmachines.service/api/v1/images/path/"+index,
			              cache: false,
			              success: function(images){ 
			            	  
			            	  $.each(images,function(i,image)
							{ 
							       //changing the img element in the html code
									$("img").attr("src", image.IMAGE_PATH);
									
							
							}) ;
			            	 
			            	 if(index<count){
			            		 index++;}
			            	 else{
			            		 index= count;
			            		 
			            	 }
			            	
			            	 
	        	  },
	        	  //handling error
	        	  error: function(jqXHR, textStatus, errorThrown) {
						console.log("Error!!"+ jqXHR.responseText);
					}
			     
							
							}); 
			      
			      //On clicking the stop button intervalid is cleared
			      $('#stop').on('click',function(){
			    	  $('#start').attr("disabled", false);
			    	  $('#stop').attr("disabled", true);
						clearInterval(intervalId);
						index=1;
						count=0;
					});
			      
		// Ajax calls are done every 200 milliseconds for polling the database
		},200) ;
		
		
		$('#start').attr("disabled", false);
	           
				
		
	 });
	
	
});
