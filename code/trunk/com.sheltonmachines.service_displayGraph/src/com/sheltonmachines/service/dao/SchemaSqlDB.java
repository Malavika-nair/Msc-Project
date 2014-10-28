package com.sheltonmachines.service.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.sheltonmachines.service.util.ToJSON;

public class SchemaSqlDB extends SqlDB {
	
	// return image details
	public JSONArray queryReturnImageDetails() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select IMAGE_ID,IMAGE_NAME,IMAGE_PATH FROM Images");
			
			
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
	
	//return vector data
	public JSONArray queryReturnVectorData() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select VectorData FROM VectorData");
			
			
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
}