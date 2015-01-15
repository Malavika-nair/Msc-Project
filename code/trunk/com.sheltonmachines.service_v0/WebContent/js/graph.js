$(document).ready(function(){
	
	var checkSumValue1 = null;

	function checkDBChange(callback){
		
		 
		  $.ajax({type: 'POST',
				 url: "http://localhost:7001/com.sheltonmachines.service/api/v1/vectordata/checksum/",
		         cache: false,
		         success: function(data){
		        	       callback(data[0].Result);
		         			},
		        error: function(jqXHR, textStatus, errorThrown) {
		    					console.log("Error!!"+ jqXHR.responseText);
		    				}
		         
				               });
		  
		}
	

//	$('#startGraph').on('click',function(){
   
	var points=[];
	var XAxisMin =0;
	
	
	ajaxGetObj = {
			type: 'POST',
			url : "http://localhost:7001/com.sheltonmachines.service/api/v1/vectordata/",
			cache: false,
			datatype:'json',
			success: function(obj){
				points=[];
				console.log(obj.length);//debug
				for(var i=3; i<obj.length;i++)
				  {
					
					points.push(parseFloat(obj[i].VectorData1));
				  
				  }
				console.log(points.length);//debug
			
				for(var i=3; i<obj.length;i++)
					{
					
					if(obj[i].VectorData1 < XAxisMin)
					    {
							XAxisMin= obj[i].VectorData1;
					
					    }
					}
				
				
				
				
				
				$(function () {
				    $('#container').highcharts({
				    	
				        title: {
				            text: 'Vector Data',
				            x: -20 //center
				        },
				        subtitle: {
				            text: 'Source: DataBase',
				            x: -20
				        },
				        
				        xAxis: {
				        	min: XAxisMin,
				        	max: obj[0].VectorData1,
				        	gridLineWidth: 2,
				        	title:{
				        		text: 'X Axis'
				        	},
				            
				        },
				        yAxis: {
				        	min:obj[1].VectorData1,
				        	max:obj[2].VectorData1,
				        	gridLineWidth: 2,
				            title: {
				                text: 'Y Axis'
				            },
				            plotLines: [{
				                value: 0,
				                width: 1,
				                color: '#808080'
				            }]
				        },
				        tooltip: {
				            valueSuffix: 'point'
				        },
				        legend: {
				            layout: 'vertical',
				            align: 'right',
				            verticalAlign: 'middle',
				            borderWidth: 0
				        },
				       series: [{
				            name: 'Vector data',
				            data: points
				        }]
				    });//debug
				});
				
				
			                    
				},
		error: function(jqXHR, textStatus, errorThrown) {
					console.log("Error!!"+ jqXHR.responseText);
				}
			
	            };
	
	obj=[]; 
	
	
	
	 setInterval(function(){ 
											checkDBChange(function(value2){
																		   if(checkSumValue1 != value2)
																	    	{      checkSumValue1 = value2;
																	    			$.ajax(ajaxGetObj);
																	    	}
																		});
										    
											},1500);
	
						
	
	
	//});

});



