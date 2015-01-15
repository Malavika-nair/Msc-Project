package com.sheltonmachines.service.dao;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class SqlDB {
	
	     private static DataSource SqlDataSource = null;
		 private static Context state = null;
		
		public static DataSource SqlDataSourceConn() throws Exception {
			
			if(SqlDataSource != null){
				return SqlDataSource;
			}
			
			try{
				if(state == null){
					state = new InitialContext();
				}
				
				SqlDataSource = (DataSource) state.lookup("DataSourceSql");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			
			return SqlDataSource;
		}
		
	protected static Connection databaseConnector() {
		Connection conn= null;
		try{
			conn = SqlDataSourceConn().getConnection();
			return conn;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
