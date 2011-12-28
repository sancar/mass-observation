/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddComment", urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet {

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
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
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
       
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        
        
        
        
        String comment=request.getParameter("Comment");
        String event_id=request.getParameter("event_id");
        String owner=email;
        String observation_id="";
        String observation_type="";
        String name_visible;
        if("anonymous".equals(request.getParameter("anonymous"))) name_visible="0";
        else name_visible="1";
        
        
        if(!(request.getParameter("text_id")==null))
        {
            observation_id=request.getParameter("text_id");
            observation_type = "text";
        }
        else if(!(request.getParameter("poll_id")==null))
        {
            observation_id=request.getParameter("poll_id");
            observation_type = "poll";
        }
        else if(!(request.getParameter("image_url")==null))
        {
            observation_id=request.getParameter("image_url");
            observation_type="image";
        }
        else if(!(request.getParameter("video_url")==null))
        {
            observation_id=request.getParameter("video_url");
            observation_type="video";
        }
        else if(!(request.getParameter("audio_url")==null))
        {
            observation_id=request.getParameter("audio_url");
            observation_type="audio";
        }

        PrintWriter out = response.getWriter();
        
        
        if(comment.contains("'"))
        {
            String message = "Disallowed character usage.";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/observeOE.jsp?id="+event_id);
            request.setAttribute("message", message);
            dispatcher.forward(request, response);
        }
        else
        {
        try
        {
            
            
            String sql = "";
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();
            /*
            if(observation_type.equals("image") || observation_type.equals("audio") || observation_type.equals("video"))
            {
              sql = "SELECT ";
            }*/
            
            sql="INSERT INTO comment_owner( owner, name_visible, comment ,observation_type, observation_id)"
                   
                    + " VALUES('"+ owner + "',"+name_visible+ ",'"+comment+"','"+observation_type+"','"+observation_id+"' )";
            statement.executeUpdate(sql);




            
        }
        
        catch (SQLException e)
                            {
                                    out.print("error");
                                    e.printStackTrace(out);
                            }
        
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./observeOE.jsp?id="+event_id);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
