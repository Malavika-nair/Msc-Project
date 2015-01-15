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

@Path("/v1/parameters/")
public class V1_Parameters {

    /**
   	 * This method returns a parameter details
   	 * @return MediaType.APPLICATION_JSON 
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
	 * This method returns a parameter value to be updated
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/update/{id}/{name}/{value}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)

	public Response updateParameter(@PathParam("id") int id,
			                        @PathParam("name") String name,
			                         @PathParam("value") String value) throws Exception {
		
      
    	String returnString = null;
    	int http_code;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SchemaSqlDB dao = new SchemaSqlDB();
		
		try{
			
			//call the correct sql method
			http_code = dao.queryUpdateParameter(id,name,value);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
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
