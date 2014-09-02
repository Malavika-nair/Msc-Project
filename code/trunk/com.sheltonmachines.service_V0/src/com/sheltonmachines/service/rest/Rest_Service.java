package com.sheltonmachines.service.rest;

import javax.ws.rs.*;

import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.awt.Image;
import java.io.File;
import java.sql.*;
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
	 * This method returns an image from the MSSQL database
	 * @return byte[] - bit map image
	 */
	
	@Path("/database/image/*")
	@GET
	@Produces("image/bmp")
	public byte[] getImage() throws Exception {
		
		PreparedStatement query = null;
	    byte image[] = null;
		
		Connection conn = null;
		
		try{
			conn = SqlDB.SqlDataSourceConn().getConnection();
			query = conn.prepareStatement("Select Image FROM Images WHERE ID=1"); //querying the database
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				
				  // /*DEbug*/ System.out.println("test");
				   Blob b = rs.getBlob("Image");
				   
				   //converting the Binary Large Object(Blob) to byte array
				   byte barr[] = new byte[(int)b.length()]; //create empty array
				   image = barr;
				   image = b.getBytes(1,(int)b.length());
				 
				
			}
			
			query.close();// close connection
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(conn != null)conn.close();
		}
		
	
		return image;
	}
		
	
}