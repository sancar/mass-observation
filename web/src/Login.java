

import java.io.IOException;
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
	String dbUrl = "jdbc:mysql://localhost:3306/mob";
	String username = "root";
	String password = "admin";
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "", password1 = "";
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("email") != null && request.getParameter("password1") != null){
			email = request.getParameter("email");
			password1 = request.getParameter("password1");
			
			if(!email.isEmpty() && !password1.isEmpty()){
				try {
					Connection connection = DriverManager.getConnection(dbUrl , username, password);
					Statement statement = connection.createStatement();
					
					ResultSet result = 	statement.executeQuery("SELECT name FROM users WHERE email = '"+ email +"' AND activation_key = '1' AND password  =  '"+ password1+ "'");
		
					 if(result.next()){
						 HttpSession session = request.getSession();
						 session.setAttribute("email", email);
						 session.setAttribute("name", (String)result.getObject(0));
						 response.sendRedirect("/welcome.jsp");
					 }else{
						out.print("There is no account with matching email and password");
					 }
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				out.print("please enter email and password to login");
			}
		}else{
			out.print("invalid input to login");
		}
	}

}
