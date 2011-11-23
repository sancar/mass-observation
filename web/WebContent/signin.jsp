<%@ include file = "./includes/header.jsp" %>

<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
	<a style="color:red;"> <% out.print(message); %></a>
		<% 
	}
%>

<style type="text/css">
div.one
{
width: 300px; height: 300px;
border-style: solid;
border-color: #68caf9
}
</style>


<div class="one">
<form action="SignIn" method = "POST" >


<table>
    <tr>
    	<td align="center" colspan="2" ><font  face="Calibri" color="#000080" size="5">  <b>Registration Form</b></font></td>
    </tr>
	<tr>
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Name: </b></font></font></td>
		<td><input type="text" name="name"  /> </td>
	</tr>
	
	<tr>
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Email: </b></font></font></td>
		<td><input type="text" name="email"  /> </td>
	</tr>
	<tr>
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Choose a password: </b></font></font></td>
		<td><input type="password" name="password1"  /> </td>
	</tr>
	
	<tr>
		<td align="center"><font face="Calibri"><font color="#000080" size="3.5"><b>Re-enter password: </b></font></font></td>
		<td><input type="password" name="password2"  /> </td>
	</tr>
	
	<tr>
		<td align="center"></td>
		<td><input type="submit" name="Sign In" value= "Sign in"/> </td>
	</tr>
	
</table>
</form>
</div>

<%@ include file = "./includes/footer.jsp" %>
