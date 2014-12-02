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
			query = conn.prepareStatement("Select VectorData1 FROM VectorData");
			
			
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
	
	//return parameter value
/**	
	public JSONArray queryReturnParameter(String paraName) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select Parameter_Value FROM Parameters WHERE Parameter_Name=?  ");
			query.setString(1, paraName);
			
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
	 **/
	
	public JSONArray queryReturnParameters() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("Select ID,Parameter_Name,Parameter_Value FROM Parameters ");
			
			
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



//return checksum for the table Images
	public JSONArray queryReturnCheckSumImages() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		
		try{
			conn = databaseConnector();
			query = conn.prepareStatement("SELECT CHECKSUM_AGG(BINARY_CHECKSUM(*)) AS Result FROM Images WITH (NOLOCK)");
			
			
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
	
	
	//return checksum for the table VectorData
		public JSONArray queryReturnCheckSumVectorData() throws Exception {
			PreparedStatement query = null;
			Connection conn = null;
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			
			try{
				conn = databaseConnector();
				query = conn.prepareStatement("SELECT CHECKSUM_AGG(BINARY_CHECKSUM(*)) AS Result FROM VectorData WITH (NOLOCK)");
				
				
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
	
		//return checksum for the table Parameters
		public JSONArray queryReturnCheckSumParameters() throws Exception {
			PreparedStatement query = null;
			Connection conn = null;
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			
			try{
				conn = databaseConnector();
				query = conn.prepareStatement("SELECT CHECKSUM_AGG(BINARY_CHECKSUM(*)) AS Result FROM Parameters WITH (NOLOCK)");
				
				
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
	
	
//return images count
		public JSONArray queryReturnImageCount() throws Exception {
			PreparedStatement query = null;
			Connection conn = null;
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			try{
				conn = databaseConnector();
				query = conn.prepareStatement("SELECT Count(IMAGE_PATH) AS number FROM Images");
				
				
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
	
	//return image path of an image id
		
				public JSONArray queryReturnImagePath(int imageId) throws Exception {
					PreparedStatement query = null;
					Connection conn = null;
					
					ToJSON converter = new ToJSON();
					JSONArray json = new JSONArray();
					
					try{
						conn = databaseConnector();
						query = conn.prepareStatement("SELECT IMAGE_PATH FROM Images WHERE IMAGE_ID= ?");
						query.setInt(1, imageId);
						
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
			
	
				//update parameter
				
				public int queryUpdateParameter(int Id,String name,String value) throws Exception {
					PreparedStatement query = null;
					Connection conn = null;
					
					
					try{
						conn = databaseConnector();
						query = conn.prepareStatement("UPDATE Parameters SET Parameter_Name=?, Parameter_Value=? WHERE ID=?");
						query.setString(1,name);
						query.setString(2,value);
						query.setInt(3, Id);
						
						query.executeUpdate();
						
						
						query.close();// close connection
					}
					catch(Exception e) {
						e.printStackTrace();
						return 500;
					}
					finally {
						if (conn != null) conn.close();
					}
					
					return 200;
				 }
	
	
	
}