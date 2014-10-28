

$(document).ready(function(){

	$('#start').on('click',function(){
		
	var $images = $('#images');
	
	ajaxGetObj = {
			type: 'POST',
			url : "http://localhost:7001/com.sheltonmachines.service/api/manage/display/",
			success: function(images){
				$.each(images,function(i,image) {
					$('#div_ajaxResponse').append('<br>');
					$('#div_ajaxResponse').append('<img src="'+image.IMAGE_PATH+'" height="200" width="400"/>');
					                              });
				                      },
			error:function(){
				alert('error loading image');
			}
			
	            };
	
	$.ajax(ajaxGetObj);
	
	});

});


