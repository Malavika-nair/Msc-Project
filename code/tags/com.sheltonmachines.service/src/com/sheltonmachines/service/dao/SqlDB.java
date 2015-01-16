package com.sheltonmachines.service.dao;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

/**
 * This class will return the MS SQL server connect object
 * @author Malavika.
 */
public class SqlDB {
	     //SqlDataSource holds the database object
	     private static DataSource SqlDataSource = null;
	     //state is used to look up the database connection in weblogic
		 private static Context state = null;
		
		public static DataSource SqlDataSourceConn() throws Exception {
			
			// if the database object is already defined,
			// then return the connection
			 
			if(SqlDataSource != null){
				return SqlDataSource;
			}
			
			 // state is used to lookup the database object in weblogic
			try{
				if(state == null){
					state = new InitialContext();
				}
				
			// SqlDataSource will hold the database object
				SqlDataSource = (DataSource) state.lookup("DataSourceSql");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			
			return SqlDataSource;
		}
		
		/**
		 * This method will return the connection to the SchemaSqlDB schema.
		 * Only java class in the dao package can use this method as the scope is protected
		 * 
		 * @return Connection to MS SQL Server database.
		 */
		
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
