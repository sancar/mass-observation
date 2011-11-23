<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") != null){
		response.sendRedirect("./welcome.jsp");
	}
%>

<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
	<a style="color:red;"> <% out.print(message); %></a>
		<% 
	}
%>

<form action="Login" method = "POST" >
<div>
<table>

	<tr>
		<td align="right">Email: </td>
		<td><input type="text" name="email"  /> </td>
	</tr>
	
	<tr>
		<td align="right">Password: </td>
		<td><input type="password" name="password1"  /> </td>
	</tr>
	
	<tr>
		<td align="right"></td>
		<td><input type="submit" name="Login" value= "Login"/> </td>
	</tr>
	</table>
</div>
</form>
<br/>
<a href="./signin.jsp">Sign in as a new user</a>

<%@ include file = "./includes/footer.jsp" %>