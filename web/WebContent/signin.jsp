<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

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
		<td align="right">Password: </td>
		<td><input type="text" name="password1"  /> </td>
	</tr>
	
	<tr>
		<td align="right">Password Again: </td>
		<td><input type="text" name="password2"  /> </td>
	</tr>
	
	<tr>
		<td align="right"></td>
		<td><input type="submit" name="Sign In" value= "Sign in"/> </td>
	</tr>
	</table>
</div>
</form>
<%@ include file = "./includes/footer.jsp" %>