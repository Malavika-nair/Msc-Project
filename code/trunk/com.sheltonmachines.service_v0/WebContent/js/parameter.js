

function getParameters(callback)
{
	$.ajax({type: 'POST',
 		 url: "http://localhost:7001/com.sheltonmachines.service/api/v1/parameters/",
          cache: false,
          success: function(data){
       	           callback(data);
          			            },
          error: function(jqXHR, textStatus, errorThrown) {
          						console.log("Error!!"+ jqXHR.responseText);
          					}
          
 		    });
 
}





$(document).ready(function() {

		
    console.log("fetching values...");
    //var parameters =null;
    var count=0;
    	getParameters(function(values){
    		var pTable = $('#para_table').dataTable();
    		var nEditing = null;
    		count= values.length;
    		console.log("count="+count);
    		for(var i=0; i<count;i++){
    	
    			
    		pTable.fnAddData( [ values[i].ID, values[i].Parameter_Name, values[i].Parameter_Value, '<input type="button" class="edit" value="Edit">'] );
    	
   			
    		}
    		
    		


    		
    		  
   	 	 $('#para_table input.edit').on('click', function (e) {
   	 	 
   	 	        e.preventDefault();
   	 	 
   	 	      
   	 	        var nRow = $(this).parents('tr')[0];
   	 	      
   	 	    
   	 	       if ( nEditing !== null && nEditing != nRow ) {
   	 	        	console.log("calling restore and edit ");//debug
   	 	            restoreRow( pTable, nEditing );
   	 	            editRow( pTable, nRow );
   	 	        
   	                nEditing = nRow;
   	 	           
   	 	        }
   	 	      
   	 	        
   	 	             
   	 	        
   	 	     else {
   	 	        	console.log("calling edit only.. ");
   	 	            editRow( pTable, nRow );
   	 	            nEditing = nRow;
   	 	            
   	 	        }
   	 	        
   	 	   $('#para_table input.save').on('click', function (f){
	   	   	 	   
 	 	         f.preventDefault();
 	 	         console.log("calling save ");
             saveRow( pTable, nEditing );
             nEditing = null;
 	 	    
 	 	    });
   	 	    
	 	       
   	   	 	   
   	 	 
   	 	        
   	 	    } );
      	
            	
    		
    		
    		
    	}); // closing getPArameters
    	
   		

    

	
	
	
    
    

	function restoreRow ( pTable, nRow )
	{ console.log("restorerow");//debug
		var aData = pTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
		
		for ( var i=0; i<jqTds.length; i++ ) {
			pTable.fnUpdate( aData[i], nRow, i, false );
			
		}
	   
		
		//pTable.fnDraw();
		
		
	}

	function editRow ( pTable, nRow )
	{
		
	    var pData = pTable.fnGetData(nRow);
	    var jqTds = $('>td', nRow);
	   
			
			console.log("editrow");//debug
			
				//jqTds[0].innerHTML = '<input type="text" value="'+pData[0]+'">';
				jqTds[1].innerHTML = '<input type="text" value="'+pData[1]+'">';
				jqTds[2].innerHTML = '<input type="text" value="'+pData[2]+'">';
				jqTds[3].innerHTML = '<input type="button" class="save" value="Save">';
				  
				
				
		
			
	}

	function saveRow ( pTable, nRow )
	{
		console.log("saverow");//debug
		var data=pTable.fnGetData(nRow);
		var id= data[0];
	    var jqInputs = $('input', nRow);
	  //  pTable.fnUpdate( jqInputs[0].value, nRow, 0, false );
	    pTable.fnUpdate( jqInputs[0].value, nRow, 1, false );
	    pTable.fnUpdate( jqInputs[1].value, nRow, 2, false );
	   
	    pTable.fnUpdate( '<input type="button" class="edit" value="Edit">', nRow, 3, false );
	    pTable.fnDraw();
	    ajaxObj = {  
				type: "PUT",
				url: "http://localhost:7001/com.sheltonmachines.service/api/v1/parameters/update" + id + "/" + jqInputs[0].value+  "/" + jqInputs[1].value, 
				cache: false,
				success: function(data) {
					//console.log(data);
					alert( data[0].MSG );
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("Error!!"+ jqXHR.responseText);
				}
				
			};
			
	   $.ajax(ajaxObj);
	    
	}

    
    
    
    
    
    
    
    
} ); // closing of doc ready


/**		$('#para_table').find('tbody')
.append($('<tr>')
		 .append($('<td>')
			.text(values[i].Parameter_Name)	 	)
		 .append($('<td>')
			 .text(values[i].Parameter_Value) )
		 .append($('<td>')
				 .append($('<a>')
						 .attr('class','edit')
						 .attr('href','""')
						 .text('Edit')))
	    
		        
		); 
		  
**/

