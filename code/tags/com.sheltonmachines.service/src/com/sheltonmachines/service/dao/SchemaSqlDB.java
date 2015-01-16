package com.sheltonmachines.service.dao;

import java.sql.*;
import org.codehaus.jettison.json.JSONArray;
import com.sheltonmachines.service.util.ToJSON;

/**
 * This class holds all sql queries performed on the database.
 * This class extends SqlDB class to inherit all the methods of that class
 * @author Malavika
 *
 */

public class SchemaSqlDB extends SqlDB {

	        /**
	         * This method allows to get the count of the image paths
	         * @return - count of the images in json format
	         * @throws Exception
	         */
			public JSONArray queryReturnImageCount() throws Exception {
				//use of 'PreparedStatement' avoids data injection
				PreparedStatement query = null;
				Connection conn = null;
				
				// an instance of ToJSON class is used to convert the query result to JSON format
				ToJSON converter = new ToJSON();
				JSONArray json = new JSONArray();
				
				try{
					// getting the database connection
					conn = databaseConnector();
					//performing SQL query on the image table
					query = conn.prepareStatement("SELECT Count(IMAGE_PATH) AS number FROM Images");
					
					
					ResultSet rs = query.executeQuery();
					
					//converting the result to JSON array
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
		
			 /**
	         * This method allows to get the image path given an id
	         * @return - image path in json format
	         * @throws Exception
	         */
			
			public JSONArray queryReturnImagePath(int imageId) throws Exception {
					PreparedStatement query = null;
					Connection conn = null;
						
					ToJSON converter = new ToJSON();
					JSONArray json = new JSONArray();
						
						try{
							conn = databaseConnector();
							//SQL query
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
				
	
			 /**
	         * This method allows to get the image path given an id
	         * @return - image path in json format
	         * @throws Exception
	         */
			
			public JSONArray queryReturnVectorData() throws Exception {
				PreparedStatement query = null;
				Connection conn = null;
		
				ToJSON converter = new ToJSON();
				JSONArray json = new JSONArray();
		
				try{
					conn = databaseConnector();
					//SQL Query
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
	
		

	
	// return parameter details
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