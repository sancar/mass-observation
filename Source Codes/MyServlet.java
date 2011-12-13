package com.free.tutorial;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().print("Hello. This is MyServlet. Time is "+ new java.util.Date().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
