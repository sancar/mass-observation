<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<script src="valid.js" type="text/javascript"></script>
<style>
A:hover {

text-decoration: none;
font-family:arial;
font-size:12px;
color: #000000;

}
A {

text-decoration: underline;
font-family:arial;
font-size:12px;
color: #000000;
BORDER: none;

}

</style>
</head>
<body>


<form name="loginform" method="post" action="loginop.jsp" onsubmit="return validLogin();">
<table width="250px" border=0 align="center" style="background-color:ffeeff;">


<tr>
		<td colspan=2 align="center" style="font-weight:bold;font-size:20pt;" align="center">User Login</td>
		
	</tr>
	<tr>
		<td colspan=2>&nbsp;</td>
		
	</tr>

	<tr>
		<td style="font-size:12pt;" align="center">Login Name</td>
		<td><input type="text" name="userName" value=""></td>
	</tr>
	<tr>
		<td style="font-size:12pt;" align="center">Password</td>
		<td><input type="password" name="password" value=""></td>
	</tr>
	
	<tr>
		<td></td>
		<td><input type="submit" width="120" name="Submit" value="Login"></td>
	</tr>
	
</table>
</form>


</body>
</html>


