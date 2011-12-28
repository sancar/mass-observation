package newpackage;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

@WebServlet("/AddAudio")
public class AddAudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
        String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
        String username = "project3";
        String password = "i52jm";       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            
            String event_id=request.getParameter("event_id");
            
            String score="0";
            String name_visible;
            if("anonymous".equals(request.getParameter("anonymous"))) name_visible="0";
            else name_visible="1";
            String audioUrl="";
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File("/home/project3/tomcat/webapps/observations/audios"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            try {
                List items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                while(iter.hasNext()){
                     FileItem item = (FileItem) iter.next();
                     if(item.isFormField()){
                            String fieldName = item.getFieldName();
                            if(fieldName.equals("oe_id"))event_id = item.getString();
                            else if(fieldName.equals("username")) username = item.getString();
                     }
                     else {
                            Calendar cal = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmssSSS");
                            if(item.getName().endsWith("mp3"))
                            audioUrl = sdf.format(cal.getTime()) +".mp3";
                            else if(item.getName().endsWith("wav"))
                                audioUrl = sdf.format(cal.getTime()) +".wav";
                            else if(item.getName().endsWith("m4a"))
                                audioUrl = sdf.format(cal.getTime()) +".m4a";
                            else if(item.getName().endsWith("wma"))
                                audioUrl = sdf.format(cal.getTime()) +".wma";
                            else {
                                session.setAttribute("email", email);
                                session.setAttribute("name", name);
                                response.sendRedirect("./observeOE.jsp?id="+event_id);
                            }

                            File uploadedFile = new File("/home/project3/tomcat/webapps/observations/audios/"+audioUrl);
                            item.write(uploadedFile);
                     }
               }
                
            
               Connection connection = DriverManager.getConnection(dbUrl , username, password);
               Statement statement;
               statement=connection.createStatement();
                
               String sql="INSERT INTO observations_audio( url, event_id, supplied_by, comment_id, score"
                          + " ,name_visible)"
                          + " VALUES('"+ audioUrl + "',"+event_id+ ",'"+email+"',0,"+score+","+name_visible+" )";
               statement.executeUpdate(sql);
                        
                }
            catch (FileUploadException e) {
                        
                    e.printStackTrace();
            } 
            catch (Exception e) {
                    
                    e.printStackTrace();
            }
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                response.sendRedirect("./observeOE.jsp?id="+event_id);
        }

}
