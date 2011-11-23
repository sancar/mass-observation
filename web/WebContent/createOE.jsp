<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
		response.sendRedirect("/mob/login.jsp");
	}

%>

<h1>Observation Event Creation Page</h1>

<form action="CrateOE" method = "POST" >
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

<%@ include file = "./includes/footer.jsp" %>