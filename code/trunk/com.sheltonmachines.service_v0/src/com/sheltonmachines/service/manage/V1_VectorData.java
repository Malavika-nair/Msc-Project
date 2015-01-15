package com.sheltonmachines.service.manage;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sheltonmachines.service.dao.SchemaSqlDB;

@Path("/v1/vectordata/")
public class V1_VectorData {
	/**
	 * This method returns vector data from the MSSQL database
	 * @return MediaType.APPLICATION_JSON 
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
   			System.out.println("string"+ returnString);
   			
   		}
   		catch (Exception e){
   			e.printStackTrace();
   			return Response.status(500).entity("Server was not able to process your request").build();
   		}
   		
   		
   		
   		return Response.ok(returnString).build();
      }
       
    
}
