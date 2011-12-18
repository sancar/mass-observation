package newpackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


@WebServlet("/AddImage")
public class AddImage extends HttpServlet {
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
        
        
        String event_id=request.getParameter("event_id");
        
        String score="0";
        String name_visible;
        if("anonymous".equals(request.getParameter("anonymous"))) name_visible="0";
        else name_visible="1";
       
        

        
        
        FileItemFactory factory = new DiskFileItemFactory();

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            // Parse the request
               List<FileItem> items = upload.parseRequest(request);
               String s = items.get(0).getFieldName();
               s = items.get(0).getName();
        
      
        
        FileItem item = (FileItem) items.get(0);
 
  
        String itemName = item.getName();
        Random generator = new Random();
        int r = Math.abs(generator.nextInt());

        String reg = "[.*]";
        String replacingtext = "";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(itemName);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
        matcher.appendReplacement(buffer, replacingtext);
        }
        int IndexOf = itemName.indexOf("."); 
        String domainName = itemName.substring(IndexOf);
        System.out.println("domainName: "+domainName);

        String finalimage = buffer.toString()+"_"+r+domainName;
        System.out.println("Final Image==="+finalimage);

        
            
            
        Connection connection = DriverManager.getConnection(dbUrl , username, password);
        Statement statement;
        statement=connection.createStatement();

        String sql="INSERT INTO observations_image( url, event_id, supplied_by, comment_id, score"
                + ", name_visible)"
                + " VALUES('"+ finalimage + "',"+event_id+ ",'"+email+"',0,"+score+","+name_visible+" )";
        statement.executeUpdate(sql);

            
        }
        catch (SQLException ex) {
            Logger.getLogger(AddImage.class.getName()).log(Level.SEVERE, null, ex);
        }        
        catch (FileUploadException ex) {
            Logger.getLogger(AddImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.setAttribute("email", email);
        session.setAttribute("name", name);
        response.sendRedirect("./observeOE.jsp?id="+event_id);
        
        }
 
        
        

}
