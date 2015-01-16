package com.sheltonmachines.service.manage;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import com.sheltonmachines.service.dao.SchemaSqlDB;

/**
 * Backend UserInterface
 * @author Malavika
 * @version 1.0
 */

/**
 * 
 * This is the root path to the resource - Images.
 * In the web.xml file,it is specified that /api/* 
 * needs to be in the URL to get to this class.
 * 
 * V1 is used in the URL path for versioning.
 *
 *Example URI to get to the root of this api resource:
 * http://localhost:7001/com.sheltonmachines.service/api/v1/images/
 */

@Path("/v1/images/")
public class V1_Images {
	 /**
		 * This method is nested one down from the root 
		 * and it returns the number of image paths
		 * @return MediaType.APPLICATION_JSON 
		 * @throws Exception
		 */
	    @Path("/count/") 
	    // indicates the HTTP verb to get to this method
	    @POST
	    //indicates the type of output
		@Produces(MediaType.APPLICATION_JSON) 

		public Response returnImageCount() throws Exception {
			
	        String returnString = null;
	        //initializing an instance of the JSONArray object
	        JSONArray json = new JSONArray();
			
			
			try{
				
				//creating instance of a database access object
				SchemaSqlDB dao = new SchemaSqlDB();
				
				//saving the JSON array returned by database access object
				json = dao.queryReturnImageCount();
				
				//JSONArray object is converted to string format before returning
				returnString = json.toString();
				
			}
			//error handling
			catch (Exception e){
				e.printStackTrace();
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			
			//returning the response to the client
			return Response.ok(returnString).build();
	   }

	   /**
		 * This method is nested one down from the root and
		 * returns the image path of an image id
		 * @return MediaType.APPLICATION_JSON 
		 * @param image path id
		 * @throws Exception
		 */
	    @Path("/path/{id}/")
	    // indicates the HTTP verb to get to this method
		@POST
		//indicates the HTTP message body
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	    //indicates the type of output
		@Produces(MediaType.APPLICATION_JSON)

		public Response returnImagePath(@PathParam("id") int image_id) throws Exception {
			
	       String returnString = null;
	     //initializing an instance of the JSONArray object
	       JSONArray json = new JSONArray();
			
			try{
				
				//creating instance of a database access object
				SchemaSqlDB dao = new SchemaSqlDB();
				
				//saving the JSON array returned by database access object
				json = dao.queryReturnImagePath(image_id);
				
				//JSONArray object is converted to string format before returning
				returnString = json.toString();
				
			}
			//error handling
			catch (Exception e){
				e.printStackTrace();
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			
			//returning the response to the client
			return Response.ok(returnString).build();
	  }
}
