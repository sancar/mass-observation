package newpackage;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Driver;
import java.sql.DriverManager;

import java.util.*;



public class databaseConnections {
    public Connection connection = null;
    public ResultSet resultSet = null;
    public Statement statement = null;

    public void connect() throws Exception {
   	 
   	 Driver driver = new com.mysql.jdbc.Driver();
   	 DriverManager.registerDriver(driver);
   	 
   	 

   	 String ConnectionURL = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";


   	 Properties prop = new Properties();
   	 	prop.put("user","project3"); 
   	 	prop.put("password","i52jm"); 
   	 
   	 
   	 connection =DriverManager.getConnection(ConnectionURL, prop);
   		 
    if (connection == null) {
   		 System.out.println("Cannot connect to the database!");
   	 }
   	 connection.setAutoCommit(true);
   	 
   	 statement = connection.createStatement(
   			 ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
   		 
    }

 
   	   	 
   	 public ArrayList<HashMap<String, Object>> return_my_events(String userName)
   	 {
             
   		 String query = "SELECT * FROM created_events WHERE created_by = '" + userName + "'";

   		 
   	 	return returnListQuery(query);
   		 
   	 }
   	 
   	   	 
   
   	
   	 

   	 public ArrayList<HashMap<String, Object>> returnListQuery(String query){ 
   		 try
   		 {
   			 ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String, Object>>();
   			 statement = connection.createStatement();
   			 resultSet = statement.executeQuery(query);
   			 
   			 ResultSetMetaData metadat = resultSet.getMetaData();

   			 while(resultSet.next())
   			 {
   				 HashMap<String,Object> hashMap = new HashMap<String,Object>();
   				 for(int i = 0; i < metadat.getColumnCount(); i++)
   				 {
   					 hashMap.put(metadat.getColumnName(i+1), resultSet.getObject(i+1));
   				 }
   				 list.add(hashMap);
   			 }
   			 return list;
   		 }catch (SQLException e)
   		 {
   			 e.printStackTrace();
   			 return null;
   		 }
   		 finally
   		 {
   			 try
   			 {
   				 statement.close();
   				 resultSet.close();
   			 } catch (SQLException e)
   			 {
   				 e.printStackTrace();
   			 }
   		 }
   	 }

   	 @SuppressWarnings("finally")
   	    	 

    public void close() throws Exception {
   	 if (connection != null) {
   		 try {
   			 connection.commit();
   			 connection.close();
   		 } catch (Exception e) {
   			 System.out.println("Can't close connection: "
   					 + e.getMessage());
   		 }
   		 connection = null;
   	 }
    }


    
}


