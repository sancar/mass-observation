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
<h1><font face="Calibri"><font color="#68caf9">&nbsp;</font></font><font face="Calibri"><font color="#68caf9" size="240">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mass</font><font color="#000080" size="240">observation</font></font></h1><hr width="100%" size="2">



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<head>
<title>Untitled</title>
</head>
<style type="text/css">
p.one
{
width: 300px; height: 300px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="one">





<font face="Calibri"><font color="#000080" size="5">&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Registration Form</b></font><br></font>
<br>
      
	
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Name: </b></font></font></td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td><input type="text" name="name"  /> </td>
	<br>
	
	
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Email: </b></font></font></td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td><input type="text" name="email"  /> </td>
	<br>
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Choose a password: </b></font></font></td>
		<td><input type="password" name="password1"  /> </td>
	<br>
	
	
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Re-enter password: </b></font></font></td>
		&nbsp;<td><input type="password" name="password2"  /> </td>
	<br>
	
	<tr>
		<td align="center"></td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td><input type="submit" name="Sign In" value= "Sign in"/> </td>
	</tr>
	




</body>




<%@ include file = "./includes/footer.jsp" %>
