<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>


<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
	<a style="color:red;"> <% out.print(message); %></a>
		<% 
	}
%>

<form action="SignIn" method = "POST" >
<div>
<table>
	<tr>
		<td align="right">Name: </td>
		<td><input type="text" name="name"  /> </td>
	</tr>
	
	<tr>
		<td align="right">Email: </td>
		<td><input type="text" name="email"  /> </td>
	</tr>
	
	<tr>
		<td align="right">Choose a password: </td>
		<td><input type="password" name="password1"  /> </td>
	</tr>
	
	<tr>
		<td align="right">Re-enter password: </td>
		<td><input type="password" name="password2"  /> </td>
	</tr>
	
	<tr>
		<td align="right"></td>
		<td><input type="submit" name="Sign In" value= "Sign in"/> </td>
	</tr>
	</table>
</div>
</form>
<%@ include file = "./includes/footer.jsp" %>