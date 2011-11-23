<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
		response.sendRedirect("./login.jsp");
	}

%>

<h2>Welcome <% out.print(session.getAttribute("name")); %> to Mass Observation</h2>
<br/>

<a href="./signin.jsp">Sign in as a new user</a><br/>
<a href="./Logout">Logout</a><br/>
<a href="./createOE.jsp">Create a new observation event</a><br/> 
<%@ include file = "./includes/footer.jsp" %>