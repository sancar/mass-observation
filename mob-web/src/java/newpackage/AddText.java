
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
import java.util.Date;
import java.text.*;

@WebServlet("/AddText")
public class AddText extends HttpServlet {
    

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
        
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yy");
        
        String text_id="";
        String text="";
        String event_id=request.getParameter("event_id");
        String supplied_by=email;
        String comment_id="";
        String score="0";
        String name_visible;
        if("0".equals(request.getParameter("anonymous"))) name_visible="0";
        else name_visible="1";
        
        String date_added=sdf.format(date);
        
        text = request.getParameter("textOE");

        PrintWriter out = response.getWriter();
        
        
        
        try
        {
            
            
            
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
            statement=connection.createStatement();
            
            String sql="INSERT INTO observations_text( text, event_id, supplied_by, comment_id, score"
                    + ", name_visible)"
                    + " VALUES('"+ text + "',"+event_id+ ",'"+email+"',0,"+score+","+name_visible+" )";
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



