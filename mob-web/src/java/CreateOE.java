

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

@WebServlet("/CreateOE")
public class CreateOE extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String dbUrl = "jdbc:mysql://titan.cmpe.boun.edu.tr:3306/database3";
	String username = "project3";
	String password = "i52jm";
   
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		String email = (String) session.getAttribute("email");
		String message = "";
		String txt_eventname = null, txt_summary=null, radio = null , radio2 = null,  id = null;
		PrintWriter out = response.getWriter();
		boolean error = false;
		if(request.getParameter("txt_eventname") == null ){
			error = true;
			message = "please enter an event name<br/>";
		}else{
			txt_eventname = request.getParameter("txt_eventname");
		}
		if(request.getParameter("txt_summary") == null ){
			error = true;
			message = "please enter the summary<br/>";
		}else{
			txt_summary = request.getParameter("txt_summary");
		}
		
		if(request.getParameter("radio") == null){
			error = true;
			message = "please select who can see your observation event<br/>";
		}else{
			radio = request.getParameter("radio");
		}
		if(request.getParameter("radio2") == null){
			error = true;
			message = "please select who can participate to your observation event<br/>";
		}else{
			radio2 = request.getParameter("radio2");
		}
		if(request.getParameter("id") == null){
			error = true;
			message = "please select the types that you wanted<br/>";
		}else{
			id = request.getParameter("id");
		}
		
		if(error){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/createOE.jsp");
			request.setAttribute("message", message);
			dispatcher.forward(request, response);
		}else{
			try {
				Connection connection = DriverManager.getConnection(dbUrl , username, password);
				Statement statement = connection.createStatement();
				
				int result = 	statement.executeUpdate("INSERT INTO cretead_events ( event_id, created_by, event_name, public_to_observe, public_to_see, observation_types, score, number_of_scores )" + 
														                       "VALUES ( 0 ,"+ email +", "+ txt_eventname + ", 1 , 1, 00001 , 0 , 0  ) ");
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			/*
			"txt_eventname"
			"txt_summary"
			"radio" value "radio_anyone_part "
				value "radio_mail_part"  name"text_mail_part"
				value "radio_name_part" name "text_name_part"
			"radio2" value "radio_anyone_see "
				value "radio_mail_see" name "text_mail_see"
				value "radio_name_see" name "text_name_see"
			"id" value "Text" "Image" "Audio record" "Video record"
			*/
			
	}

}

