package com.sheltonmachines.service.manage;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.sheltonmachines.service.dao.SchemaSqlDB;
//import com.sheltonmachines.service.Data;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.binary.Base64;

@Path("/manage/display/")
public class manage_display {

	//@Path("/{imageName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
//	public Response returnImageDetails(
		//	@PathParam ("imageName") String imageName ) throws Exception {
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
	
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,"text/plain"})
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayImage(String imageName) throws Exception {
		
		String returnString = null;
		String imagePath=null;
		JSONArray json = new JSONArray();
	
		
		try{
			
			
			SchemaSqlDB dao = new SchemaSqlDB();
			
			json = dao. queryReturnImagePath(imageName);
			returnString = json.toString();
			
			
			
		}
		catch (Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		
		return Response.ok(returnString).build();
		
	}
	
	
}

