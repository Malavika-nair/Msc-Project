$(document).ready(function(){
	
	var checkSumValue1 = null;
	
	//function to send ajax request to the resource/vectordata/checksum 
	function checkDBChange(callback){
		
		//an Ajax request send to the URI of the resource to check whether there is database change  
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
	
   //an array to store the points of the graph
	var points=[];
	var XAxisMin =0;
	
	//setting up the ajaxobject for the ajax request to the resource /vectordata 
	ajaxGetObj = {
			type: 'POST',
			url : "http://localhost:7001/com.sheltonmachines.service/api/v1/vectordata/",
			cache: false,
			datatype:'json',
			// the points of the graph are pushed into  a points array from the json object
			success: function(obj){
				points=[];
				// storing the values of the points from the json array to another array
				for(var i=3; i<obj.length;i++)
				  {
					
					points.push(parseFloat(obj[i].VectorData1));
				  
				  }
			//calculating XAxis minimum
			for(var i=3; i<obj.length;i++)
					{
					
					if(obj[i].VectorData1 < XAxisMin)
					    {
							XAxisMin= obj[i].VectorData1;
					
					    }
					}
				
		/**
		 * The below function to plot the graph is referenced from
		 * http://www.highcharts.com/demo/line-basic 
		 */		
				
				
				
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
				    });
				});
				
				
			                    
				},
		//error handling
		error: function(jqXHR, textStatus, errorThrown) {
					console.log("Error!!"+ jqXHR.responseText);
				}
			
	            };
	
	obj=[]; 
	
	
	// the ajax request every 1500 milliseconds
	 setInterval(function(){ 
											checkDBChange(function(value2){
																		   if(checkSumValue1 != value2)
																	    	{      checkSumValue1 = value2;
																	    			$.ajax(ajaxGetObj);
																	    	}
																		});
										    
											},1500);

});



