package com.sheltonmachines.service.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.sheltonmachines.service.dao.*;
import com.sheltonmachines.service.util.*;
import com.sheltonmachines.service.dao.SqlDB;
import com.sheltonmachines.service.util.ToJSON;

@Path("/manage/display/*")
public class manage_display {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnImageDetails() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
        String returnString = null;
		Response resp = null;
		
		try{
			conn = SqlDB.SqlDataSourceConn().getConnection();
			query = conn.prepareStatement("Select IMAGE_ID,IMAGE_NAME,IMAGE_PATH FROM Images");
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close();// close connection
			
		    returnString = json.toString();
		    resp = Response.ok(returnString).build();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			if(conn != null)conn.close();
		}
		
		return resp;
   }
}
