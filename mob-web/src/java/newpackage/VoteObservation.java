
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
        int num_of_scores=1;
        
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
            
            String sql = " INSERT INTO user_votes(email, id, type, given_score) VALUES('"+email+"','"+id+"','"+type+"',"+Integer.parseInt(vote)*20+")";
            
            statement.executeUpdate(sql);
            
            statement.close();
            
            sql  = "SELECT COUNT(*) FROM user_votes WHERE type='"+type+"' AND id='"+id+"'";
            
            statement = connection.createStatement();
            
            ResultSet rs= statement.executeQuery(sql);
            
            if(rs.next())
                num_of_scores = rs.getInt(1);
            
            rs.close();
            statement.close();
            
            statement = connection.createStatement();
            String sqlType="";
            if(type.equals("audio") || type.equals("image") || type.equals("video")) sqlType="url";
            else if(type.equals("poll")) sqlType="poll_id";
            else if(type.equals("text")) sqlType="text_id";
            sql="UPDATE observations_"+type+" SET score=score/"+num_of_scores+"+"+(Integer.parseInt(vote)*20)/num_of_scores+ " WHERE "+sqlType+"= '"+id+"'";
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



