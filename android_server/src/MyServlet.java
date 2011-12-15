

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		doPost(request,response);
		//response.getWriter().print("Hello. This is MyServlet. Time is "+ new java.util.Date().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "", password1 = "";

		PrintWriter out = response.getWriter();
		if(request.getParameter("email") != null && request.getParameter("password") != null){
			email = request.getParameter("email");
			password1 = request.getParameter("password");
			if(!email.isEmpty() && !password1.isEmpty()){
				try {
			
					Connection connection = (Connection) DriverManager.getConnection(Common.dbUrl , Common.username, Common.password);
					Statement statement = (Statement) connection.createStatement();
					ResultSet result = 	statement.executeQuery("SELECT name FROM users WHERE email = '"+ email +"' AND activation_key = '1' AND password  =  '"+ password1+ "'");
					if(result.next()){
						out.print("OK");
					}else{
						out.print("NOT-AUTHORIZED");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.print(e);
				}
			}else{
				out.print("ERROR");
			}
		}else{
			out.print("ERROR");
		}
	}

}

