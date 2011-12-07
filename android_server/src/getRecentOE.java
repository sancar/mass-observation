

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class getRecentOE
 */
@WebServlet("/getRecentOE")
public class getRecentOE extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRecentOE() {
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
		try {
			connection = (Connection) DriverManager.getConnection(Common.dbUrl , Common.username, Common.password);
			Statement statement = (Statement) connection.createStatement();
			ResultSet result = 	statement.executeQuery("SELECT event_id, event_name, event_summary FROM created_events ORDER BY event_id DESC LIMIT 5");
			JSONArray jason = new JSONArray();
			jason = writeJson(result);
			out.print(jason);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private JSONArray writeJson(ResultSet result) throws JSONException, SQLException {
		JSONArray jasonarray = new JSONArray();
		while(result.next()){
			JSONObject jason = new JSONObject();
			jason.put("id", result.getInt("event_id"));
			jason.put("name",result.getString("event_name"));
			jason.put("desc", result.getString("event_summary"));
			jasonarray.put(jason);
		}
		return jasonarray;
	}

}
