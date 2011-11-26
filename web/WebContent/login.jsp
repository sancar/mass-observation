<%@ include file = "./includes/header.jsp" %>


<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
	<a style="color:red;"> <% out.print(message); %></a>
		<% 
	}
%>

<table>
<tr>
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

<p class="one">
<font face="Calibri"><font color="#000080" size="5"><b>Login</b></font><br></font>
<br>
<font face="Calibri"><font color="#000080" size="3.5"><b>E-mail: </b></font></font>
<input type="text" name="email"  /> 
<br>
<br>
<font face="Calibri"><font color="#000080" size="3.5"><b>Password: </b></font></font>
<input type="password" name="password1"  />
<br>
<input type="submit" name="Login" value= "Login"/>
</div>
</form>



<font face="Calibri"><font color="#000080" size="4"><b>New here?</b></font><br></font>
<a href="./signin.jsp">Sign in as a new user</a>


<style type="text/css">
p.two
{
width: 300px; height: 200px;
border-style: solid;
border-color: #68caf9
}
</style>

<p class="two">
</body>


</td>
<td>

<style type="text/css">
p.three
{
width: 930px; height: 480px;
border-style: solid;
border-color: #68caf9
}
</style>

<p class="three">



</td>
</tr>
</table>
<br>

<%@ include file = "./includes/footer.jsp" %>