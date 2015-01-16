/**
 * function to send ajax request to the resource v1/parameters to get parameter details
 * @param callback
 */

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

			var count=0;
    		getParameters(function(values){
    		var pTable = $('#para_table').dataTable();
    		var nEditing = null;
    		count= values.length;
    		console.log("count="+count);
    		for(var i=0; i<count;i++){
    	
    		//adding the data to the data table
    		pTable.fnAddData( [ values[i].ID, values[i].Parameter_Name, values[i].Parameter_Value, '<input type="button" class="edit" value="Edit">'] );
    	
   			
    		}
    		
    		

          /**
           * Reference: Inline Editing 
           *            available at http://datatables.net/blog/2012-05-31
           */
    		
    		  
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
      	
            		
    	});
    	
   /**
    * This function will restore the row if editing is not done 
    */		

	function restoreRow ( pTable, nRow )
	{ 
		var aData = pTable.fnGetData(nRow);
		var jqTds = $('>td', nRow);
		
		for ( var i=0; i<jqTds.length; i++ ) {
			pTable.fnUpdate( aData[i], nRow, i, false );
			
		}
	   
		
	}

	/**
	 * this function will edit the row at client side to the newly entered value
	 */
	function editRow ( pTable, nRow )
	{
		
	    var pData = pTable.fnGetData(nRow);
	    var jqTds = $('>td', nRow);
	   
				jqTds[1].innerHTML = '<input type="text" value="'+pData[1]+'">';
				jqTds[2].innerHTML = '<input type="text" value="'+pData[2]+'">';
				jqTds[3].innerHTML = '<input type="button" class="save" value="Save">';
				  
	}

	// this function sends the edited column values to the the server through the api URI
	function saveRow ( pTable, nRow )
	{
		var data=pTable.fnGetData(nRow);
		var id= data[0];
	    var jqInputs = $('input', nRow);
	    pTable.fnUpdate( jqInputs[0].value, nRow, 1, false );
	    pTable.fnUpdate( jqInputs[1].value, nRow, 2, false );
	   
	    pTable.fnUpdate( '<input type="button" class="edit" value="Edit">', nRow, 3, false );
	    pTable.fnDraw();
	    // defining ajax object for the ajax request send to server to update the parameter value
	    ajaxObj = {  
				type: "PUT",
				url: "http://localhost:7001/com.sheltonmachines.service/api/v1/parameters/update/" + id + "/" + jqInputs[0].value+  "/" + jqInputs[1].value, 
				cache: false,
				success: function(data) {
					alert( data[0].MSG );
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("Error!!"+ jqXHR.responseText);
				}
				
			};
			
	   $.ajax(ajaxObj);
	    
	}

} ); 

