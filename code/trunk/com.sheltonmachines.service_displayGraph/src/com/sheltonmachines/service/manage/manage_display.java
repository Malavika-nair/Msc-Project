package com.sheltonmachines.service.manage;


import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
}

