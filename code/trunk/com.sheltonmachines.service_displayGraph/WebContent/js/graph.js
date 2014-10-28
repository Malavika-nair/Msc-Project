$(document).ready(function(){

	$('#view').on('click',function(){
   
	var points=[];
	var XAxisPoints =[];
	
	ajaxGetObj = {
			type: 'POST',
			url : "http://localhost:7001/com.sheltonmachines.service/api/manage/display/graph/",
			datatype:'json',
			success: function(data){
				
				for(var i=3; i<data.length;i++)
				  {
				  points.push(parseFloat(data[i].VectorData));
				  }
				
				
				
				for(var j=0;j<=data[0].VectorData;j++)
					{
					XAxisPoints.push(j);
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
				            categories: XAxisPoints
				        },
				        yAxis: {
				        	min: data[1].VectorData,
				        	max:data[2].VectorData,
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
				
				
			                       }
			
	            };
	
	$.ajax(ajaxGetObj);
	
	});

});

