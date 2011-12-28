package newpackage;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
   	//String dbUrl = "jdbc:mysql://localhost/database3";
        String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
	String username = "project3";
	String password = "i52jm";
        
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "", password1 = "";
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("email") != null && request.getParameter("password1") != null){
			email = request.getParameter("email");
			password1 = request.getParameter("password1");
                        
                        if(email.contains("'") || password1.contains("'"))
                        {
                            String message = "Disallowed character usage.";
		            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			    request.setAttribute("message", message);
			    dispatcher.forward(request, response);
                        }
                        else
                        {
			if(!email.isEmpty() && !password1.isEmpty()){
				try {
					Connection connection = DriverManager.getConnection(dbUrl , username, password);
					Statement statement = connection.createStatement();
					password1 = MD5(password1);
					ResultSet result = 	statement.executeQuery("SELECT name FROM users WHERE email = '"+ email +"' AND activation_key = '1' AND password  =  '"+ password1+ "'");
		
					 if(result.next()){
						 HttpSession session = request.getSession();
						 session.setAttribute("email", email);
						 session.setAttribute("name", (String)result.getObject(1));
                                                

                                                 
						 response.sendRedirect("./index.jsp");
					 }else{
						String message = "There is no account with matching email and password";
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
						request.setAttribute("message", message);
						dispatcher.forward(request, response);
					 }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
                                         out.print("error");
                                        e.printStackTrace(out);
                                       
				}
			}else{
				String message = "please enter email and password to login";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				request.setAttribute("message", message);
				dispatcher.forward(request, response);
				
			}
                        }
		}else{
			out.print("invalid input to login");
		}
	}

}
