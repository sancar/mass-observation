
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/myservlet")
public class myservlet extends HttpServlet {
   //to try the database on the titan server uncomment the following lines and comment other three lines. 
	String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr/database3";
	String username = "project3";
	String password = "i52jm"; //i did not commit the password, you should change it
  //String dbUrl = "jdbc:mysql://localhost:3306/mob";
	//String username = "root";
	//String password = "ozan";
	private static final long serialVersionUID = 1L;
	
	public String MD5(String md5) {
 	   try {
 	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
 	        byte[] array = md.digest(md5.getBytes());
 	        StringBuffer sb = new StringBuffer();
 	        for (int i = 0; i < array.length; ++i) {
 	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
 	       }
 	        return sb.toString();
 	    } catch (java.security.NoSuchAlgorithmException e) {
 	    }
 	    return null;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String un = request.getParameter("email");
		String pw = request.getParameter("password");
		try {
		Connection connection = DriverManager.getConnection(dbUrl, username, password);
		Statement statement = connection.createStatement();
		
		ResultSet result = statement.executeQuery("SELECT name FROM users WHERE email = '"+un+"' AND password = '"+pw+"'");
		
		if(result.next()){
			out.print("OK");
		}
		else {
			out.print("NOT-AUTHORIZED");
		}
		}
		catch (SQLException e){
			e.printStackTrace();
			out.print("ERROR");
			
		}
	}

}
