package com.sheltonmachines.service.manage;


import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sheltonmachines.service.dao.SchemaSqlDB;
//import com.sheltonmachines.service.Data;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * This is the root path for restful api service
 * In the web.xml file it is mentioned that /api/* 
 * need to be in the url to get to this class
 * @author Malavika
 *
 */
@Path("/manage/display/")
public class manage_display {

	/**
	 * This method returns image details from the MSSQL database
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/image/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)

	public Response returnImageDetails() throws Exception {
		
        String returnString = null;
        JSONArray json = new JSONArray();
		
		
		try{
			
			
			SchemaSqlDB dao = new SchemaSqlDB();
			
			json = dao.queryReturnImageDetails();
			returnString = json.toString();
			
		}
		catch (Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		
		return Response.ok(returnString).build();
   }
	
	/**
	 * This method returns vector data from the MSSQL database
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/graph/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)

	public Response returnVectorData() throws Exception {
		
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
	 * This method returns a parameter value to be updated
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/parameters/{id}/{name}/{value}")
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
   
    
    /**
	 * This method returns a parameter value to be updated
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/parameters/")
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
	 * This method returns the result of checksum query on the images table
	 * @return MediaType.APPLICATION_JSON 
	 */
    @Path("/checksum/images/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    
	public Response returnCheckSumOfImages() throws Exception {
		
        String returnString = null;
        JSONArray json = new JSONArray();
		
		
		try{
			
			
			SchemaSqlDB dao = new SchemaSqlDB();
			
			json = dao.queryReturnCheckSumImages();
			returnString = json.toString();
			System.out.println("string"+ returnString);
			
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
       @Path("/checksum/vectordata/")
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
       
       
       /**
      	 * This method returns the result of checksum query on the Parameters table
      	 * @return MediaType.APPLICATION_JSON 
      	 */
          @Path("/checksum/parameters/")
      	@POST
      	@Produces(MediaType.APPLICATION_JSON)
          
      	public Response returnCheckSumOfParameters() throws Exception {
      		
              String returnString = null;
              JSONArray json = new JSONArray();
      		
      		
      		try{
      			
      			
      			SchemaSqlDB dao = new SchemaSqlDB();
      			
      			json = dao.queryReturnCheckSumParameters();
      			returnString = json.toString();
      			System.out.println("string"+ returnString);
      			
      		}
      		catch (Exception e){
      			e.printStackTrace();
      			return Response.status(500).entity("Server was not able to process your request").build();
      		}
      		
      		
      		
      		return Response.ok(returnString).build();
         }
          
          
          /**
      	 * This method returns a images count
      	 * @return MediaType.APPLICATION_JSON 
      	 */
         @Path("/count/images/")
      	@POST
      	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
      	@Produces(MediaType.APPLICATION_JSON)

      	public Response returnImageCount() throws Exception {
      		
              String returnString = null;
              JSONArray json = new JSONArray();
      		
      		
      		try{
      			
      			
      			SchemaSqlDB dao = new SchemaSqlDB();
      			
      			json = dao.queryReturnImageCount();
      			returnString = json.toString();
      			
      		}
      		catch (Exception e){
      			e.printStackTrace();
      			return Response.status(500).entity("Server was not able to process your request").build();
      		}
      		
      		
      		
      		return Response.ok(returnString).build();
         }
    
         /**
     	 * This method returns a image path of an image id
     	 * @return MediaType.APPLICATION_JSON 
     	 */
       @Path("/images/{image_id}/")
     	@POST
     	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
     	@Produces(MediaType.APPLICATION_JSON)

     	public Response returnParameter(@PathParam("image_id") int image_id) throws Exception {
     		
             String returnString = null;
             JSONArray json = new JSONArray();
             System.out.println("incomingData: " + image_id);
     		
     		try{
     			
     			
     			SchemaSqlDB dao = new SchemaSqlDB();
     			
     			json = dao.queryReturnImagePath(image_id);
     			returnString = json.toString();
     			
     		}
     		catch (Exception e){
     			e.printStackTrace();
     			return Response.status(500).entity("Server was not able to process your request").build();
     		}
     		
     		
     		
     		return Response.ok(returnString).build();
        }
}

