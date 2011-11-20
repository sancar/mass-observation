<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
		response.sendRedirect("/mob/login.jsp");
	}

%>

<h2>Welcome <% out.print(session.getAttribute("name")); %> to Mass Observation</h2>
<br/>
<a href="/mob/signin.jsp">Sign in as new user</a>
<a href="/mob/Logout">Logout</a>
<%@ include file = "./includes/footer.jsp" %>