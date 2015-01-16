package com.sheltonmachines.service.util;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


import java.sql.ResultSet;



public class ToJSON {
	public JSONArray toJSONArray(ResultSet rs) throws Exception {

		//JSON array to be created and returned
        JSONArray json = new JSONArray(); 
       
        try {

        	// retrieving all the column names from the database ResultSet
             java.sql.ResultSetMetaData rsmd = rs.getMetaData();

             
             while( rs.next() ) {
            	 
            	 // number of columns in the ResultSet
                 int columnCount = rsmd.getColumnCount();
                 
                 //Initializing a JSONObject for each of the rows
                 JSONObject obj = new JSONObject();

                 //each column is places into the JSON Object
                 for (int i=1; i<columnCount+1; i++) {

                	 //getting the column name
                     String col_name = rsmd.getColumnName(i);
                  
                     /*mapping the sql data type to the json object
                      * this handles the different datatypes
                      */
                     
                   
                    if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                    	 obj.put(col_name, rs.getInt(col_name));
                    
                     }
                    
                    if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                    	obj.put(col_name, rs.getFloat(col_name));
                    }
                    
                    if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
                      	 obj.put(col_name, rs.getArray(col_name));
                      	
                       }
                     else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
                    	 obj.put(col_name, rs.getNString(col_name));
                    	
                     }
                    
                     else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                    	 obj.put(col_name, rs.getString(col_name));  	
                     }
                    
                 
                     else if(rsmd.getColumnType(i)==java.sql.Types.NUMERIC){
                    	 obj.put(col_name, rs.getBigDecimal(col_name));
                    	 
                      }
                     else {
                    	 obj.put(col_name, rs.getObject(col_name));
                    	 
                     } 

                    }
                 
                 json.put(obj);

             }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json; //return JSON array
	}
}
