
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
@WebServlet("/DeleteOE")
public class DeleteOE extends HttpServlet {

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
        String event_id=request.getParameter("event_id"); 
        String created_by="";
        //first we have to confirm that the current user is the initiator of the OE.
        
        String message = "";
        try{
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();
            String query= "SELECT * FROM created_events WHERE event_id = '"+event_id+ "' ";
            ResultSet resultSet=statement.executeQuery(query);
            boolean next=resultSet.next();
            created_by=resultSet.getObject("created_by").toString();
            }
        catch(SQLException e) {
            message=e.getMessage();
        }
        

        if(email.equals(created_by)) {
            String sql="DELETE FROM created_events WHERE event_id = '"+event_id+ "' ";
            try{
                Connection connection = DriverManager.getConnection(dbUrl , username, password);
                Statement statement;
                statement=connection.createStatement();
                statement.executeUpdate(sql);
                
            }
            catch(SQLException e) {
                message=e.getMessage();
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
