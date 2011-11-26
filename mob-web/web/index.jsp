<%@include file="./includes/header.jsp"%> 
 
<% 
	if(session.getAttribute("name") == null){ 
		response.sendRedirect("./login.jsp"); 
	}%>
	
<div align="right">
<em><font face="Calibri" color="#40424d">Welcome </font><% out.print(session.getAttribute("name"));%></em><font color="#0000ff"></font>
<a href="./Logout">Log Out</a>
</div>

<table>
<tr>
<td>

<style type="text/css">
p.one
{
width: 250px; height: 400px;
border-style: solid;
border-color: #68caf9
}
</style>

<p class="one">
<br>

<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">  <b><u>Recent Observation Events </u></b> </font><br></font></font>
 

<br>
  <select id="list_popularOE" name="list_popularOE" multiple="multiple"> 
    <option value="value1">IndyCar 2011</option>
    <option value="value2">Samsung Galaxy Ace</option>
    
</select>
<br>
<br>
<br>
<br>

<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">  <b><u>Popular Observation Events</u></b></font><br></font></font>
<br>
  <select id="list_popularOE" name="list_popularOE" multiple="multiple"> 
    <option value="value1">Life at Japan</option>
    <option value="value2">Autobahns in Turkey</option>
    
</select>

</td>
<td>

<style type="text/css">
p.two
{
width: 470px; height: 400px;
border-style: solid;
border-color: #68caf9
}
</style>

<p class="two">

</td>
<td>


<style type="text/css">
p.three
{
width: 500px; height: 400px;
border-style: solid;
border-color: #68caf9
}
</style>

<p class="three">
<input type="text" name="txt_search" size="50"><font face="Calibri"><font color="#68caf9"> </font></font><input type="button" value="Search" name="btn_search"></h3> 
 
<br>

</td>
</tr>
</table>


<font face="Calibri"><br><a href="./createOE.jsp">Create a new observation event</a></font><br>  
<%@include file="./includes/footer.jsp"%>