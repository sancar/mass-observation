package newpackage;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;

@WebServlet("/CreateOE")
public class CreateOE extends HttpServlet {
        private static final long serialVersionUID = 1L;
        String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
        String username = "project3";
        String password = "i52jm";
   
        
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
                String name = (String) session.getAttribute("name");
                String email = (String) session.getAttribute("email");
                String message = "";
                String mail = "", mail_observe = "";
                int event_id=0;
                String txt_eventname = null, txt_summary=null, radio = null , radio2 = null  ;
                int cb_Poll = 0, cb_Text = 0, cb_Image = 0, cb_Audio = 0, cb_Video = 0;
                int public_to_observe = 0, public_to_see = 0;
                String[] polls = {""};
                String[] poll_options = {""};
                String[] tags = {""};
                PrintWriter out = response.getWriter();
                boolean error = false;
        
                if(request.getParameter("txt_eventname") == null ){
                        error = true;
                        message = "please enter an event name<br/>";
                }else{
                        txt_eventname = request.getParameter("txt_eventname");
                }
                if(request.getParameter("txt_summary") == null ){
                        error = true;
                        message = "please enter the summary<br/>";
                }else{
                        txt_summary = request.getParameter("txt_summary");
                }
                
                if(request.getParameter("radio") == null){
                        error = true;
                        message = "please select who can participate your observation event<br/>";
                }else{
                        radio = request.getParameter("radio");
                        if("radio_anyone_part".equals(radio))
                           public_to_observe = 1;
                         else if("radio_mail_part".equals(radio))
                            mail_observe = request.getParameter("text_mail_part");
                    
                }
                if(request.getParameter("radio2") == null){
                        error = true;
                        message = "please select who can see to your observation event<br/>";
                }else{
                        radio2 = request.getParameter("radio2");
                        
                        if("radio_anyone_see".equals(radio2))
                           public_to_see = 1;
                         else if("radio_mail_see".equals(radio2))
                            mail = request.getParameter("text_mail_see");
                          
                        
                }
               
                if(request.getParameterValues("cb_Poll") != null){
                 
                    cb_Poll = 1;
                  
                    
                }else{
                        cb_Poll = 0;
                }
                if(request.getParameterValues("cb_Text") != null){
                 
                    cb_Text = 1;
                    
                }else{
                    cb_Text = 0;
                    
                }
                if(request.getParameterValues("cb_Image") != null){
                 
                    cb_Image = 1;
                    
                }else{
                        cb_Image = 0;
                }
                if(request.getParameterValues("cb_Audio") != null){
                 
                    cb_Audio = 1;
                    
                }else{
                        cb_Audio = 0;
                }
                if(request.getParameterValues("cb_Video") != null){
                 
                    cb_Video = 1;
                    
                }else{
                        cb_Video = 0;
                }
                
                if(error){
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createOE.jsp");
                        request.setAttribute("message", message);
                        dispatcher.forward(request, response);
                }else{
                        try {
                                Connection connection = DriverManager.getConnection(dbUrl , username, password);
                                Statement statement = connection.createStatement();                                                                                         //           " VALUES ("+name +","+ txt_eventname + ","+txt_summary+","+public_to_observe +","+ 
                                
                                int result = statement.executeUpdate("INSERT INTO created_events ( created_by, event_name, event_summary, public_to_observe, public_to_see, score, poll, text, image, audio, video )" + 
                                                                                                                                       "VALUES ('"+email +"','"+ txt_eventname + "','"+txt_summary+"',"+public_to_observe +","+ 
                                                                                                                                       public_to_see +"," + " 0 , "+cb_Poll + "," + cb_Text + "," + cb_Image + "," + cb_Audio +
                                                                                                                                       "," + cb_Video + " )");
                          
                                 statement.close();
                                Statement stmt = connection.createStatement();
                                ResultSet rs = stmt.executeQuery( "select LAST_INSERT_ID()" );
                                if ( rs.next() )
                                        {
                                        event_id = rs.getInt(1);
                                        }
                                rs.close();
                                stmt.close();
                                connection.close();
                               
                        } catch (SQLException e) {
                                
                                out.print("error");
                                e.printStackTrace(out);
                        }
                        
                        
                        if(!mail.equals(""))
                        {
                             try
                            {
                                Connection connection = DriverManager.getConnection(dbUrl , username, password);
                                PreparedStatement statement;
                                
                                statement = connection.prepareStatement("SELECT email FROM users WHERE email LIKE '%"+mail+"'");
                                Statement statement2 = connection.createStatement();
                                
                                ResultSet mails = statement.executeQuery();
                                
                                 while(mails.next())
                                    statement2.executeUpdate("INSERT INTO users_can_see(event_id, user) VALUES("+event_id+",'"+mails.getString(1) +"')");
                                 
                                 mails.close();
                                 
                            }
                            catch (SQLException e)
                            {
                                    out.print("error");
                                    e.printStackTrace(out);
                            }
                             
                        }
                            if(!mail_observe.equals(""))
                        {
                             try
                            {
                                Connection connection = DriverManager.getConnection(dbUrl , username, password);
                                PreparedStatement statement;
                                
                                statement = connection.prepareStatement("SELECT email FROM users WHERE email LIKE '%"+mail_observe+"'");
                                Statement statement2 = connection.createStatement();
                                
                                ResultSet mails = statement.executeQuery();
                                
                                 while(mails.next())
                                    statement2.executeUpdate("INSERT INTO users_can_observe(event_id, user) VALUES("+event_id+",'"+mails.getString(1) +"')");
                                 
                                 mails.close();
                                 
                            }
                            catch (SQLException e)
                            {
                                    out.print("error");
                                    e.printStackTrace(out);
                            }
                        }
                             
                        polls = request.getParameterValues("poll_name");
                        
                            if(polls != null)
                            {
                            int pollindex = 1, poll_id = 0;
                           
                            for(int i = 0; i < polls.length; i++)
                            {
                                try {
                                    Connection connection = DriverManager.getConnection(dbUrl , username, password);
                    
                                    Statement statement = connection.createStatement();
                
                                    statement.executeUpdate("INSERT INTO observations_poll(event_id, supplied_by, score, name_visible, text) VALUES("+event_id+",'"+email +"',0,1,'"+polls[i]+"')");
                                    ResultSet rs = statement.executeQuery( "select LAST_INSERT_ID()" );
                                    if ( rs.next() )
                                        {
                                        poll_id = rs.getInt(1);
                                        }
                                     rs.close();
                                    poll_options = request.getParameterValues("poll"+pollindex);
                                    pollindex++;
                                    for(int j = 0; j < poll_options.length ; j++)
                                        statement.executeUpdate("INSERT INTO poll_choices(choice_name, poll_id) VALUES('"+poll_options[j]+"',"+poll_id+")");
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreateOE.class.getName()).log(Level.SEVERE, null, ex);
                               }
                                 
                            }
                            }
                           
                            int i=0;
                            tags = request.getParameterValues("tag"+i);
                            while(tags!=null)
                            {
                                 try {
                                    Connection connection = DriverManager.getConnection(dbUrl , username, password);
                    
                                    Statement statement = connection.createStatement();
                
                                    statement.executeUpdate("INSERT INTO oe_tags(event_id, tag_name) VALUES("+event_id+",'"+tags[0].toString()+"')");
                                  
                                } catch (SQLException ex) {
                                    Logger.getLogger(CreateOE.class.getName()).log(Level.SEVERE, null, ex);
                               }
                                
                                i++;
                                tags = request.getParameterValues("tag"+i);
                            }
                }
                        
                                
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome.jsp");
			message = txt_eventname + " is successfully created.";
                        request.setAttribute("message", message);
                        dispatcher.forward(request, response);
                        
                        session.setAttribute("email", email);
                        session.setAttribute("name", name);
                        response.sendRedirect("./welcome.jsp");
                }
                
                        /*
                        "txt_eventname"
                        "txt_summary"
                        "radio" value "radio_anyone_part "
                                value "radio_mail_part"  name"text_mail_part"
                                value "radio_name_part" name "text_name_part"
                        "radio2" value "radio_anyone_see "
                                value "radio_mail_see" name "text_mail_see"
                                value "radio_name_see" name "text_name_see"
                        "id" value "Text" "Image" "Audio record" "Video record"
                        */
                        
        }
