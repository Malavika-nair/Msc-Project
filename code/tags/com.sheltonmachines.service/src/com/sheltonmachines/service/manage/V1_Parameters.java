package com.sheltonmachines.service.manage;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sheltonmachines.service.dao.SchemaSqlDB;

/**
 * 
 * This is the root path to the resource - Parameters.
 * V1 is used in the URL path for versioning.
 *
 * Example URI to get to the root of this api resource:
 * http://localhost:7001/com.sheltonmachines.service/api/v1/parameters/
 */

@Path("/v1/parameters/")
public class V1_Parameters {

	/**
	 * This method is nested one down from the root 
	 * and it returns the parameter details
	 * @return MediaType.APPLICATION_JSON 
	 * @throws Exception
	 */

   	@POST
   	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
   	@Produces(MediaType.APPLICATION_JSON)

   	public Response returnParameters() throws Exception {
   		
           String returnString = null;
           JSONArray json = new JSONArray();
   		
   		
   		try{
   			
   			
   			SchemaSqlDB dao = new SchemaSqlDB();
   			
   			json = dao.queryReturnParameters();
   			returnString = json.toString();
   			
   		}
   		catch (Exception e){
   			e.printStackTrace();
   			return Response.status(500).entity("Server was not able to process your request").build();
   		}
   		
   		
   		
   		return Response.ok(returnString).build();
      }
       
    
    
    /**
	 * This method returns a message after updating the parameter table.
	 * @return MediaType.APPLICATION_JSON 
	 * @param id
	 * @param name
	 * @param value
	 */
    @Path("/update/{id}/{name}/{value}")
    // indicates the HTTP verb to get to this method
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //indicates the type of output
	@Produces(MediaType.APPLICATION_JSON)

	public Response updateParameter(@PathParam("id") int id,
			                        @PathParam("name") String name,
			                         @PathParam("value") String value) throws Exception {
		
      
    	String returnString = null;
    	int http_code;
    	// instances to create the json array and database schema
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaSqlDB dao = new SchemaSqlDB();
		
		try{
			
			//call the SchemaSqlDB method to update the parameter table and pass the arguments
			http_code = dao.queryUpdateParameter(id,name,value);
			
			//if the method returns a http code of 200, put a success message to the json object to be returned
			if(http_code == 200) {
				//put method is used to add data to json object
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} 
			//handling error
			else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
   }
   

}
