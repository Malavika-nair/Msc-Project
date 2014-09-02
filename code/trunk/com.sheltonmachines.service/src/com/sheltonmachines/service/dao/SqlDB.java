package com.sheltonmachines.service.dao;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class SqlDB {
	
	     private static DataSource SqlDataSource = null;
		 private static Context context = null;
		
		public static DataSource SqlDataSourceConn() throws Exception {
			
			if(SqlDataSource != null){
				return SqlDataSource;
			}
			
			try{
				if(context == null){
					context = new InitialContext();
				}
				
				SqlDataSource = (DataSource) context.lookup("DataSourceSql");
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			
			return SqlDataSource;
		}
}
