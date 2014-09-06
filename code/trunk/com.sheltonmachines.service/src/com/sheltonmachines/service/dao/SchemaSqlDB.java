package com.sheltonmachines.service.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.sheltonmachines.service.util.ToJSON;

public class SchemaSqlDB extends SqlDB {
	
	public JSONArray queryReturnImageDetails() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select IMAGE_ID,IMAGE_NAME,IMAGE_PATH FROM Images");
			
			//query.setString(1,imageName);
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();// close connection
		}
		catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e){
			e.printStackTrace();
			return json;
		}
		finally{
			if (conn != null) conn.close();
		}
		return json;
	 }
	
	public JSONArray queryReturnImagePath(String imageName) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
      

		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select IMAGE_PATH FROM Images WHERE IMAGE_NAME= ?");
			
			query.setString(1,imageName);
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close();// close connection
			
			query.close();// close connection
		}
		catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e){
			e.printStackTrace();
			return json;
		}
		finally{
			if (conn != null) conn.close();
		}
		return json;
	 }
}