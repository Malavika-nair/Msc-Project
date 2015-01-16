package com.sheltonmachines.service.manage;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.codehaus.jettison.json.JSONArray;

import com.sheltonmachines.service.dao.SchemaSqlDB;

/**
 * 
 * This is the root path to the resource - VectorData.
 * V1 is used in the URL path for versioning.
 *
 * Example URI to get to the root of this api resource:
 * http://localhost:7001/com.sheltonmachines.service/api/v1/vectordata/
 */

@Path("/v1/vectordata/")
public class V1_VectorData {
	/**
	 * This method returns vector data from the MSSQL database
	 * @return MediaType.APPLICATION_JSON 
	 * @throws Exception
	 */
   
	@POST
	@Produces(MediaType.APPLICATION_JSON)

	public Response returnAllVectorData() throws Exception {
		
        String returnString = null;
        JSONArray json = new JSONArray();
		
		
		try{
			
			
			SchemaSqlDB dao = new SchemaSqlDB();
			
			json = dao.queryReturnVectorData();
			returnString = json.toString();
			
		}
		catch (Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		
		return Response.ok(returnString).build();
   }
 
    
    /**
   	 * This method returns the result of checksum query on the VectorData table
   	 * @return MediaType.APPLICATION_JSON 
   	 */
    @Path("/checksum/") 
   	@POST
   	@Produces(MediaType.APPLICATION_JSON)
       
   	public Response returnCheckSumOfVectorData() throws Exception {
   		
           String returnString = null;
           JSONArray json = new JSONArray();
   		
   		
   		try{
   			
   			
   			SchemaSqlDB dao = new SchemaSqlDB();
   			
   			json = dao.queryReturnCheckSumVectorData();
   			returnString = json.toString();
   			
   		}
   		catch (Exception e){
   			e.printStackTrace();
   			return Response.status(500).entity("Server was not able to process your request").build();
   		}
   		
   		
   		
   		return Response.ok(returnString).build();
      }
       
    
}
