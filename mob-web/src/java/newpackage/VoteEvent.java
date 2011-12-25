
package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 *
 * @author Betul
 */
@WebServlet("/VoteEvent")
public class VoteEvent extends HttpServlet {
    

    private static final long serialVersionUID = 1L;
    String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
    String username = "project3";
    String password = "i52jm";
    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        
        String event_id=request.getParameter("event_id");
        
        String vote=request.getParameter("vote");
       
        try
        {
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
       
            statement = connection.createStatement();
            
            String sql="UPDATE created_events SET score=score+"+Integer.parseInt(vote)*20+", number_of_scores=number_of_scores+1  WHERE event_id="+event_id;
            statement.executeUpdate(sql);

        }
        
        catch (SQLException e)
                            {
                                   System.out.println( e.getMessage());
                            }
        
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./welcome.jsp?id="+event_id);
        
        }
 
    }



