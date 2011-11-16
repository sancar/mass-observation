

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
import java.util.Map;
/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	String dbUrl = "jdbc:mysql://localhost:3306/mob";
	String username = "root";
	String password = "admin";
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = "", password1 = "", password2 = "" , email = "";
		
		PrintWriter out = response.getWriter();
		
		
		if (request.getParameter("name")!= null && request.getParameter("email") != null 
				&&	request.getParameter("password1") != null && request.getParameter("password2") != null){
			name = request.getParameter("name");
			email = request.getParameter("email");
			password1 = request.getParameter("password1");
			password2 = request.getParameter("password2");
			if(!name.isEmpty() && !email.isEmpty() && !password1.isEmpty() && !password2.isEmpty()){
			
				if(password1.equals(password2)){
					
					try {
						Connection connection = DriverManager.getConnection(dbUrl , username, password);
						Statement statement = connection.createStatement();
						
						ResultSet result = 	statement.executeQuery("SELECT email FROM users WHERE email = '"+ email +"' AND activation_key = '1' ");
						int count = 0;
			
						 while(result.next())
						 {
						   count++;
						 }
						 if(count == 1){
							String message = "This email is used by another user";
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signin.jsp");
							request.setAttribute("message", message);
							dispatcher.forward(request, response);
							
						 }else if(count == 0){
							//generate a random activation key
							int randomNumber = (int)(Math.random() * Math.random() * 10000);
							//out.print(randomNumber);
							String activation_key = MD5(Integer.toString(randomNumber));
							activation_key = "1"; //TODO change this part when email confirmation system is added
							//out.print(activation_key);	
							
							//encrypt the password then save it
							password1 = MD5(password1);
							
							 statement.executeUpdate("INSERT INTO users ( email , name, password, activation_key) " +
						 								"VALUES ( '"+ email + "' , '"+ name +"'  , '"+ password1 +"' , '"+ activation_key +"' ) " );
							 
							 HttpSession session = request.getSession();
							 session.setAttribute("email", email);
							 session.setAttribute("name", name);
							 
							 //out.print("new user account is succesfully created");
							 
							 response.sendRedirect("/mob/welcome.jsp");
							 // TODO send email with activation key
							 // TODO make a ActivationController class
						 }else{
							 out.print("A serious problem is found, duplicate email in database");
						 }
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						out.print("Connection time out");
					}
				}else{
					String message = "Passwords did not match";
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signin.jsp");
					request.setAttribute("message", message);
					dispatcher.forward(request, response);
					
				}
			}else{
				String message = "Please fill the form";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signin.jsp");
				request.setAttribute("message", message);
				dispatcher.forward(request, response);
				
			}
			
		}else{
			out.print("invalid input to sign in");
		}
	}

}

