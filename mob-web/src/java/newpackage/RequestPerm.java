
package newpackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "RequestPerm", urlPatterns = {"/RequestPerm"})
public class RequestPerm extends HttpServlet {


    private static final long serialVersionUID = 1L;
    String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
    String username = "project3";
    String password = "i52jm";
    
    databaseConnections database = new databaseConnections();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        
        int see=0;
        int observe=0;
        
        String event_id=request.getParameter("event_id"); 
        String type = request.getParameter("type");
        String message = "";
        
        PrintWriter out = response.getWriter();
        
        
        
         String query="";
         query="SELECT * FROM users_request WHERE event_id = "+event_id+" AND user_email = '"+ email + "' ";
         int requestNum=0;
         try{
             Connection connection=DriverManager.getConnection(dbUrl, username, password);
             Statement statement;
             statement=connection.createStatement();
             ResultSet resultSet=statement.executeQuery(query);
             while(resultSet.next()) {
                 requestNum++;
             }
         }
         catch(SQLException e) {
             e.printStackTrace();
         }
              if(type.equals("see"))
              query="SELECT * FROM users_request WHERE event_id = "+event_id+" AND user_email = '"+ email + "' AND see=1 ";
              
              else if(type.equals("observe"))
              query="SELECT * FROM users_request WHERE event_id = "+event_id+" AND user_email = '"+ email + "' AND observe=1 ";
              
              int rowCount=0;
              try {
                  
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();
                  ResultSet resultSet=statement.executeQuery(query);
                  while(resultSet.next()) {
                      rowCount++;
                  }
              }
              catch(SQLException e)
              {
                  e.printStackTrace();
                  
              }
             
        
        if(rowCount>0) 
        {     
         RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
         message =  "You have already sent a request!";
         request.setAttribute("message", message);
         dispatcher.forward(request, response);
    
        }
        else
        {
        if(type.equals("see"))
            see = 1;
        else if(type.equals("observe"))
            observe = 1;

        
        try
        {
          
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();
            String sql="";
            //if this is the first request by the user:
            if(requestNum==0) {
            sql="INSERT INTO users_request(event_id, user_email, see, observe)"
                   
                    + " VALUES("+event_id+ ",'"+email+ "',"+see+","+observe+" )";
            
            }
            //we have to consider the case, where this user has made another request 
            //to the same event before, so this request is already in the table, and must be updated. 
            else {
            sql="UPDATE users_request SET "+type+"=1 WHERE event_id = "+event_id+ " AND user_email = '"+ email + "'";
            }
            statement.executeUpdate(sql);
         
        }
        
        catch (SQLException e)
                            {
                                    out.print("error");
                                    e.printStackTrace(out);
                            }
        }
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./welcome.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }


}
