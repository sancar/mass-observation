

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FetchImageObservation
 */
@WebServlet("/FetchImageObservation")
public class FetchImageObservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchImageObservation() {
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
		String username = "";
		String oeID = "";
		String photoUrl = "";
		InputStream is = request.getInputStream();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("/home/project3/tomcat/webapps/observations/images"));
		//factory.setRepository(new File("C:\\Users\\TOSHIBA\\workspace\\myServer"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while(iter.hasNext()){
				FileItem item = (FileItem) iter.next();
				if(item.isFormField()){
					String fieldName = item.getFieldName();
					if(fieldName.equals("oe_id"))oeID = item.getString();
					else if(fieldName.equals("username")) username = item.getString();
				}
				else {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmssSSS");
					photoUrl = sdf.format(cal.getTime()) +".jpg";
					File uploadedFile = new File("/home/project3/tomcat/webapps/observations/images/"+photoUrl);
					item.write(uploadedFile);
				}
			}
			int ret = insertIntoDB(username,oeID,photoUrl);
			if(ret == 1)
				out.write("Success!");
			if(ret == 0)
				out.write("Failure!");
		} catch (FileUploadException e) {
			System.out.println(e.toString());
			System.out.println("patladik");
			System.out.println(new File(".").getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private int insertIntoDB(String username, String oeID, String photoUrl) throws SQLException {
		Connection connection;
		connection = (Connection) DriverManager.getConnection(Common.dbUrl , Common.username, Common.password);
		Statement statement = (Statement) connection.createStatement();
		int result = statement.executeUpdate("INSERT INTO observations_image (url, event_id, supplied_by) VALUES ('"+photoUrl+"',"+oeID+",'"+username+"')");
		return result;
	}
}
