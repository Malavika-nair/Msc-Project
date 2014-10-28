package com.sheltonmachines.service.util;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


import java.sql.ResultSet;

import org.owasp.esapi.ESAPI;

public class ToJSON {
	public JSONArray toJSONArray(ResultSet rs) throws Exception {

		//JSON array to be returned
        JSONArray json = new JSONArray(); 
        String temp = null;

        try {

        	// rtrieving all the column names
             java.sql.ResultSetMetaData rsmd = rs.getMetaData();

             //looping through the ResultSet
             while( rs.next() ) {
            	 
            	 // number of columns
                 int columnCount = rsmd.getColumnCount();
                 
                 //each row in the ResultSet is converted to a JSON Object
                 JSONObject obj = new JSONObject();

                 //looping through all the columns and placing them into the JSON Object
                 for (int i=1; i<columnCount+1; i++) {

                     String col_name = rsmd.getColumnName(i);

                   
                    if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                    	 obj.put(col_name, rs.getInt(col_name));
                    
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
                    	 obj.put(col_name, rs.getNString(col_name));
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                    	 
                    	 temp = rs.getString(col_name); //saving column data to temp variable
                    	 temp = ESAPI.encoder().canonicalize(temp); //decoding data to base state
                    	 temp = ESAPI.encoder().encodeForHTML(temp); //encoding to be browser safe
                    	 obj.put(col_name, temp); //putting data into JSON object
                    	 
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
                    	 obj.put(col_name, rs.getInt(col_name));
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
                    	 obj.put(col_name, rs.getInt(col_name));
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                    	 obj.put(col_name, rs.getDate(col_name));
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                    	 obj.put(col_name, rs.getTimestamp(col_name));
                    	;
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
