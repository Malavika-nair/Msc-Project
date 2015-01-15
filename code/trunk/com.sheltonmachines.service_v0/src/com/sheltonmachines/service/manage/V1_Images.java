package com.sheltonmachines.service.manage;



import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
 * Resource - images
 *
 */

@Path("/v1/images/")
public class V1_Images {
	 /**
		 * This method returns images count
		 * @return MediaType.APPLICATION_JSON 
		 */
	    @Path("/count/") 
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
	    @Path("/path/{id}/")
		@POST
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
		@Produces(MediaType.APPLICATION_JSON)

		public Response returnImagePath(@PathParam("id") int image_id) throws Exception {
			
	       String returnString = null;
	       JSONArray json = new JSONArray();
			
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
