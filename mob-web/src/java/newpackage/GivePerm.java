
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
 * @author msaglam
 */
@WebServlet("/GivePerm")
public class GivePerm extends HttpServlet {
    
        private static final long serialVersionUID = 1L;
        String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
        String username = "project3";
        String password = "i52jm";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");    
        String message="";
        String type=request.getParameter("type");
        String event_id=request.getParameter("event_id");
        String user_email=request.getParameter("email");
        String query="";
        //giving permission to the user in a certain type:
        
        try{
            Connection connection=DriverManager.getConnection(dbUrl,username,password);
            Statement statement;
            statement=connection.createStatement();
            
            query= "INSERT INTO users_can_"+type+"(event_id, user)"
                    +" VALUES("+event_id+",'"+user_email+"' )";
            statement.executeUpdate(query);
            
            
        }
        
        catch(SQLException e) {
            message=e.getMessage();
        }
        
        //once permission is given, we can remove the permission request of the user from the database:
        try{
            Connection connection=DriverManager.getConnection(dbUrl,username,password);
            Statement statement;
            statement=connection.createStatement();
            query="UPDATE users_request SET "+type+"=0 WHERE event_id = "+event_id+ " AND user_email = '"+ user_email + "'";
            statement.executeUpdate(query);
        }
        
        catch(SQLException e) {
            message=e.getMessage();
        }
        
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./editOE.jsp?id="+event_id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


}
