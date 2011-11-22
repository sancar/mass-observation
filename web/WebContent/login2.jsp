

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

<table>
<td>
<form action="Login" method = "POST" >
<div>

<style type="text/css">
p.one
{
width: 300px; height: 200px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="one">
<font face="Calibri"><font color="#000080" size="5">&nbsp;<b>Login</b></font><br></font>
<br>
<font face="Calibri"><font color="#000080" size="3.5">&nbsp;&nbsp;<b>E-mail: </b></font></font>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="email"  /> 
<br>
<br>
<font face="Calibri"><font color="#000080" size="3.5">&nbsp;&nbsp;<b>Password: </b></font></font>
<input type="password" name="password1"  />
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="Login" value= "Login"/>
</div>
</form>



</body>



<font face="Calibri"><font color="#000080" size="4"><b>New here?</b></font><br></font>
<a href="./signin.jsp">Sign in as a new user</a>



<head>
<title>Untitled</title>
</head>
<style type="text/css">
p.two
{
width: 300px; height: 200px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="two">
</body>


</td>
<td>


<head>
<title>Untitled</title>
</head>
<style type="text/css">
p.three
{
width: 930px; height: 480px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="three">
</body>


</td>
</table>
<br>









<%@ include file = "./includes/footer.jsp" %>
