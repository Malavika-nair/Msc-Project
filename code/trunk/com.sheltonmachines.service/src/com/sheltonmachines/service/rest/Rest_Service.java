package com.sheltonmachines.service.rest;

import javax.ws.rs.*;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.awt.Image;
import java.io.File;
import java.sql.*;
import java.util.*;

import com.sheltonmachines.service.dao.*;

/**
 * This is the root path for restful api service
 * In the web.xml file it is mentioned that /api/* 
 * need to be in the url to get to this class
 * @author Malavika
 *
 */
@Path("/service/*")
public class Rest_Service {
	
	/**
	 * This method returns images from the MSSQL database
	 * @return byte[] - bit map image
	 */
	
	@Path("/database/imagepath/*")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getImagePath() throws Exception {
		
		PreparedStatement query = null;
		String imagePath = null;
	  // byte image[] = null;
	   // int i=0;
		
		Connection conn = null;
		
		try{
			conn = SqlDB.SqlDataSourceConn().getConnection();
			query = conn.prepareStatement("Select IMAGE_PATH from IMAGES where IMAGE_ID=1"); //querying the database
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				
				  imagePath = rs.getString("IMAGE_PATH");
				  
			}
			
			query.close();// close connection
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(conn != null)conn.close();
		}
		
		 return imagePath;
			
		
	}
		
	/**
	 * This method returns image names from the MSSQL database
	 * @return List<String> - list of image names
	 */
	
	@Path("/database/imagenames/*")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public List<String> getImageNames() throws Exception {
		
		PreparedStatement query = null;
	    List<String> imageNames = new ArrayList<String>();
		Connection conn = null;
		
		try{
			conn = SqlDB.SqlDataSourceConn().getConnection();
			query = conn.prepareStatement("Select Name FROM Images"); //querying the database
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				
				 imageNames.add(rs.getString("Name"));
				 
				  
			}
			
			query.close();// close connection
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(conn != null)conn.close();
		}
		
		 return imageNames;
			
		
	}
}