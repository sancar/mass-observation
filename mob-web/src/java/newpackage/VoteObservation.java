
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
@WebServlet("/VoteObservation")
public class VoteObservation extends HttpServlet {
    

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
        
        String type="";
        String id="";
        
        if(request.getParameter("text_id")!=null)
        {
            type = "text";
            id= request.getParameter("text_id");
        }
        else if(request.getParameter("poll_id")!=null)
        {
            type = "poll";
            id= request.getParameter("poll_id");
        }
        else if(request.getParameter("audio_id")!=null)
        {
            type = "audio";
            id= request.getParameter("audio_id");
        }
        else if(request.getParameter("image_id")!=null)
        {
            type = "image";
            id= request.getParameter("image_id");
        }
        else if(request.getParameter("video_id")!=null)
        {
            type = "video";
            id= request.getParameter("video_id");
        }
        
        
        String vote= request.getParameter("vote");
       
        try
        {
            Connection connection = DriverManager.getConnection(dbUrl , username, password);
            Statement statement;
       
            statement = connection.createStatement();
            
            String sql="UPDATE observations_"+type+" SET score=score+"+Integer.parseInt(vote)*20+" WHERE "+type+"_id="+id;
            statement.executeUpdate(sql);

        }
        
        catch (SQLException e)
                            {
                                   System.out.println( e.getMessage());
                            }
        
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./observeOE.jsp?id="+event_id);
        
        }
 
    }



