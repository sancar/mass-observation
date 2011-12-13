
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
import java.util.*;

@WebServlet("/AddPoll")
public class AddPoll extends HttpServlet {
    

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
        
        String poll_id=request.getParameter("poll_id");
        String event_id=request.getParameter("event_id");
        String choice_id=request.getParameter("poll_answer");

        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        PrintWriter out = response.getWriter();
        
        try
        {
            
            
            
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();

            String sql="INSERT INTO poll_answers(poll_id, answered_by, choice_id) VALUES("+poll_id +",'"+ email + "',"+choice_id+ " )";
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



