/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
/**
 *
 * @author Betul
 */
@WebServlet(name = "RequestPerm", urlPatterns = {"/RequestPerm"})
public class RequestPerm extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
    String username = "project3";
    String password = "i52jm";
    
    databaseConnections database = new databaseConnections();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        
        //-----------------------------------------
         String query="";
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
             
        //-----------------------------------------
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
            
            String sql="INSERT INTO users_request(event_id, user_email, see, observe)"
                   
                    + " VALUES("+event_id+ ",'"+email+ "',"+see+","+observe+" )";
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

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }

   

}
