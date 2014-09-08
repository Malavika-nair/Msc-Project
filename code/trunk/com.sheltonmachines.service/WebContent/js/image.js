/*$(document).ready(function() {
	//console.log("ready");
	
	var $display_image = $('#display_image');
	
	
	$('#show_image').click(function(e) {
		//console.log("submit button has been clicked");
		e.preventDefault(); //cancel form submit
		
		var jsObj = $display_image.serializeObject()
			, ajaxObj = {};
		
		
		//console.log(jsObj);
		
		ajaxObj = {  
			type: "POST",
			url: "http://localhost:7001/com.sheltonmachines.service/api/manage/display/", 
			data: "Image4", 
			contentType:"text/html",
			error: function(jqXHR, textStatus, errorThrown) {
				console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			success: function(data) { 
				console.log("data as below");
				console.log(data);
				
					$('#div_ajaxResponse').text( data.MSG );
				
			},
			complete: function(XMLHttpRequest) {
				console.log( XMLHttpRequest.getAllResponseHeaders() );
			}, 
			dataType: "html" //request JSON/// html
		};
		
		$.ajax(ajaxObj);
	});
	
});
*/


$(document).ready(function(){
	
	var $images = $('#images');
	
	ajaxGetObj = {
			type: 'GET',
			url : "http://localhost:7001/com.sheltonmachines.service/api/manage/display/",
			success: function(images){
				$.each(images,function(i,image) {
					$images.append('<li>name: '+image.IMAGE_NAME +', path: '+image.IMAGE_PATH + '</li>');
					                              });
				                      },
			error:function(){
				alert('error loading image');
			}
			
	            };
	
	$.ajax(ajaxGetObj);
	
	
	$('#display-image').on('click',function(){

	  var $name= $('#name');
     /* var image = {
    		  name: $name.val(),
      };
		*/
	  
	
		ajaxPostObj = {
				type: 'POST',
				url : "http://localhost:7001/com.sheltonmachines.service/api/manage/display/",
				data: $name.val(),
				contentType: "application/json",
				success: function(images){
					$.each(images,function(i,image) {
			        //var imgElt = image.IMAGE_PATH;
					//var imgData = JSON.stringify(imgElt);
						$images.append('<img src="'+image.IMAGE_PATH+'"/>');
					console.log("image data=" +image.IMAGE_PATH);
					 });
				},
				error:function(){
					alert('error loading image');
				}
				
		            };
		
		$.ajax(ajaxPostObj);
		
		
	});
	
/*	function getBase64Image(imgElem) {
		// imgElem must be on the same server otherwise a cross-origin error will be thrown "SECURITY_ERR: DOM Exception 18"
		    var canvas = document.createElement("canvas");
		    canvas.width = imgElem.clientWidth;
		    canvas.height = imgElem.clientHeight;
		    var ctx = canvas.getContext("2d");
		    ctx.drawImage(imgElem, 0, 0);
		    var dataURL = canvas.toDataURL("image/png");
		    return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
		}
	*/
});