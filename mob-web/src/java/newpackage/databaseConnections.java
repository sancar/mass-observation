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
    /*
   	 public ArrayList<HashMap<String, Object>> return_my_events(String email)
   	 {            
   		 String query = "SELECT * FROM created_events WHERE created_by = '" + email + "'";   		 
   	 	return returnListQuery(query);  		 
   	 }*/ 
         public ArrayList<HashMap<String, Object>> search_my_events(String email,String from, String orderby, String search)
   	 {
   		String query = "SELECT * FROM created_events WHERE (event_name LIKE '%"+search+"%' OR event_summary LIKE '%"+search+"%') AND created_by = '" + email + "'  ORDER BY " + orderby+" DESC  LIMIT " + from + ", 4 ";
   	 	return returnListQuery(query);	 
   	 }
         /** Not implemented yet
           * Currently searching according to tag is missing
           * @param from  = offset for starting point
           * @param orderby = data OR score
           * @param search = search query
           * @return 
           */
         public ArrayList<HashMap<String, Object>> search_joined_events(String email,String from, String orderby, String search)
   	 {
   		String query = "SELECT * FROM joined_events WHERE (event_name LIKE '%"+search+"%' OR event_summary LIKE '%"+search+"%') AND email = '" + email + "' ORDER BY " + orderby+" DESC  LIMIT " + from + ", 4 ";
   	 	return returnListQuery(query);	 
   	 }
         /*
   	 public ArrayList<HashMap<String, Object>> return_public_events()
   	 {
   		 String query = "SELECT * FROM created_events WHERE public_to_observe = '1' ";
   	 	return returnListQuery(query);	 
   	 }  	 
          public ArrayList<HashMap<String, Object>> return_all_events()
   	 {
   		 String query = "SELECT * FROM created_events  ORDER BY score DESC  LIMIT 0 , 4 ";
   	 	return returnListQuery(query);	 
   	 }*/
          /**
           * Currently searching according to tag is missing
           * @param from  = offset for starting point
           * @param orderby = data OR score
           * @param search = search query
           * @return 
           */
          public ArrayList<HashMap<String, Object>> search_all_events(String from, String orderby, String search)
   	 {
   		String query = "SELECT * FROM created_events WHERE event_name LIKE '%"+search+"%' OR event_summary LIKE '%"+search+"%'  ORDER BY " + orderby+" DESC  LIMIT " + from + ", 4 ";
   	 	return returnListQuery(query);	 
   	 }
   	public ArrayList<HashMap<String, Object>> return_an_event(String id)
   	 {
   		String query = "SELECT * FROM created_events WHERE event_id = '"+id+"' ";
                return returnListQuery(query);	 		 
   	 } 
   	 public ArrayList<HashMap<String, Object>> return_texts(String event_id)
   	 {
   		 String query = "SELECT * FROM observations_text WHERE event_id = '"+event_id+"' ";
   	 	return returnListQuery(query);   		 
   	 } 
          public ArrayList<HashMap<String, Object>> return_comments(String o_id , String o_type)
   	 {
   		 String query = "SELECT * FROM comment_owner WHERE observation_id = '"+o_id+"'  AND observation_type = '"+ o_type + "' ";
   	 	return returnListQuery(query);   		 
   	 }
          public ArrayList<HashMap<String, Object>> return_polls(String event_id)
   	 {
   		 String query = "SELECT * FROM observations_poll WHERE event_id = '"+event_id+"' ";
   	 	return returnListQuery(query);   		 
   	 }
          public ArrayList<HashMap<String, Object>> return_images(String event_id)
   	 {
   		 String query = "SELECT * FROM observations_image WHERE event_id = '"+event_id+"' ";
   	 	return returnListQuery(query);   		 
   	 }
          public ArrayList<HashMap<String, Object>> return_audios(String event_id)
   	 {
   		 String query = "SELECT * FROM observations_audio WHERE event_id = '"+event_id+"' ";
   	 	return returnListQuery(query);   		 
   	 }
          public ArrayList<HashMap<String, Object>> return_videos(String event_id)
   	 {
   		 String query = "SELECT * FROM observations_video WHERE event_id = '"+event_id+"' ";
   	 	return returnListQuery(query);   		 
   	 }
          public ArrayList<HashMap<String, Object>> return_choices(String poll_id)
   	 {
   		 String query = "SELECT * FROM poll_choices WHERE poll_id = '"+poll_id+"' ";
   	 	return returnListQuery(query);   		 
   	 }
           public ArrayList<HashMap<String, Object>> return_see_requests(String event_id)
   	 {
   		 String query = "SELECT * FROM users_request WHERE event_id = '"+event_id+"' AND see = '1' ";
   	 	return returnListQuery(query);   		 
   	 }
           public ArrayList<HashMap<String, Object>> return_observe_requests(String event_id)
   	 {
   		String query = "SELECT * FROM users_request WHERE event_id = '"+event_id+"' AND observe = '1' ";
   	 	return returnListQuery(query);   		 
   	 }
           /*
           * email : id of user which will be checked
           * id    : id of event that is checked
           * type : is either see or observe .
           */
          public String getUserName(String email) {
              String name="";
              String query="SELECT * FROM users WHERE email= '"+email+ "' ";
              String message="";
              try{
                  statement=connection.createStatement();
                  resultSet=statement.executeQuery(query);
                  boolean next=resultSet.next();
                  name=resultSet.getObject("name").toString();
              }
              catch(SQLException e) {
                  message=e.getMessage();
              }
              
              return name;
          }
          public boolean isInitiator(String event_id, String email) {
              String query= "SELECT * FROM created_events WHERE event_id = '"+event_id+ "' ";
              String message="";
              String created_by="";
              try{
                  statement=connection.createStatement();
                  resultSet=statement.executeQuery(query);
                  boolean next=resultSet.next();
                  created_by=resultSet.getObject("created_by").toString();
              }
              catch(SQLException e) {
                  message=e.getMessage();
              }
              if(created_by.equals(email))
                  return true;
              return false;
              
              
          }
          public boolean isAllowed(String email, String id, String type){
              String query = "SELECT * FROM created_events WHERE event_id = '"+id+ "' ";
              int pblc = 0;
              int allowed = 0;
              String message = "s";
              try{
                  statement=connection.createStatement();
                  resultSet=statement.executeQuery(query);
                  boolean next = resultSet.next();
                  pblc = (Integer)resultSet.getObject("public_to_"+type);
              }catch(SQLException e){
                  message = e.getMessage();
              }
              if(pblc == 1) return true;
              query = "SELECT * FROM users_can_"+type+" WHERE event_id = '"+id+"' AND user = '"+email+"' ";
              try{
                  statement=connection.createStatement();
                  resultSet=statement.executeQuery(query);
                  if(resultSet.next()){
                      allowed++;
                  }
              }catch(Exception e){
                  allowed = -1;
              }
              if(allowed != 0)  return true;
              
              return false;
          }
          public boolean voteCheck(String poll_id, String email)
          {
              
              String query="SELECT * FROM poll_answers WHERE poll_id = '"+poll_id+"' AND answered_by = '"+ email + "' ";
              
              int rowCount=0;
              try {
                  statement=connection.createStatement();
                  resultSet=statement.executeQuery(query);
                  while(resultSet.next()) {
                      rowCount++;
                  }
              }
              catch(SQLException e)
              {
                  e.printStackTrace();
                  return false;
              }
              if(rowCount!=0) return true; 
              return false;
          }
          
          public int return_n_answers(String choice_id){
                String query = "SELECT * FROM poll_answers WHERE choice_id = '"+choice_id+"' " ;   
                int rowCount = 0;
                try {
                  statement = connection.createStatement();
                  resultSet = statement.executeQuery(query);
                  while(resultSet.next()){
                      rowCount++;
                  }
                 }
                catch (SQLException e)
                 {
                     e.printStackTrace();
                     return 0;
                 }
                
                return rowCount;
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
   			System.out.println(e.getMessage());
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
   				System.out.println(e.getMessage());
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


