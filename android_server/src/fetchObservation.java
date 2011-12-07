

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
 * Servlet implementation class fetchObservation
 */
@WebServlet("/fetchObservation")
public class fetchObservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchObservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection connection;
		String observation = request.getParameter("observation");
		int OEID = Integer.parseInt(request.getParameter("oeid"));
		String contributor = request.getParameter("username");
		try {
			connection = (Connection) DriverManager.getConnection(Common.dbUrl , Common.username, Common.password);
			Statement statement = (Statement) connection.createStatement();
			int result = statement.executeUpdate("INSERT INTO observations_text (text, event_id, supplied_by, name_visible) VALUES ('"+observation+"',"+OEID+",'"+contributor+"',1)");
			if(result == 1)
				out.print("Success!");
			else
				out.print("Failure!");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	}

}
