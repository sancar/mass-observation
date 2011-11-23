<%@ page language="java" import="java.sql.*;"%>
<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
//		response.sendRedirect("./login.jsp");
	}

%>
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<em><font face="Calibri"><font color="#40424d">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; Welcome</font></font>   <% out.print(session.getAttribute("name"));%></em> <br><div><font color="#0000ff">&nbsp;</font><font color="#0000ff">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; </font><u><font color="#0000ff">Log Out</font></u></div><h1><font face="Calibri"><font color="#68caf9">&nbsp;</font></font><font face="Calibri"><font color="#68caf9" size="240">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mass</font><font color="#000080" size="240">observation</font></font></h1><hr width="100%" size="2">


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<table>
<td>
<head>
<title>Untitled</title>
</head>
<style type="text/css">
p.one
{
width: 350px; height: 450px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="one">
<br>


<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">&nbsp; &nbsp; &nbsp;<b><u>Name of the Observation Event </u></b> </font><br></font></font>

<br>

 
 
 &nbsp; &nbsp; &nbsp;<input type="text" name="txt_eventname" size="30">
 
 <br>
 <br>
 <br>
<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">&nbsp; &nbsp; &nbsp;<b><u>Summary</u></b></font><br></font></font>
<br>
&nbsp; &nbsp; &nbsp;
<textarea name="txt_summary" rows="6" id="txt_summary"
checkMaxLength(event,this)" TAMaxLength="300"
style="width:250px;"></textarea>

</body>
</td>

<td>
<head>
<title>Untitled2</title>

</head>

<style type="text/css">
p.two
{
width: 350px; height: 450px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="two">

<br>

<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">&nbsp; &nbsp; &nbsp;Who can participate? </font><br></font></font>

<br>
<script type="text/javascript">
function addText(val) {
if (val.value == "radio_mail_part") {

document.forms['form1'].elements['text_mail_part'].style.display = "block";
document.forms['form1'].elements['text_name_part'].style.display = "none";	
document.forms['form1'].elements['text_name_part'].value="";
}
else if(val.value=="radio_name_part")
	{
	document.forms['form1'].elements['text_mail_part'].style.display = "none";	
	document.forms['form1'].elements['text_mail_part'].value="";
	document.forms['form1'].elements['text_name_part'].style.display = "block";
	}
else {
	document.forms['form1'].elements['text_mail_part'].style.display = "none";	
	document.forms['form1'].elements['text_mail_part'].value="";
	document.forms['form1'].elements['text_name_part'].style.display = "none";	
	document.forms['form1'].elements['text_name_part'].value="";
}
if(val.value=="radio_name_see")
{
document.forms['form1'].elements['text_mail_see'].style.display = "none";	
document.forms['form1'].elements['text_mail_see'].value="";
document.forms['form1'].elements['text_name_see'].style.display = "block";
}
else if(val.value=="radio_mail_see")
{
document.forms['form1'].elements['text_name_see'].style.display = "none";	
document.forms['form1'].elements['text_name_see'].value="";
document.forms['form1'].elements['text_mail_see'].style.display = "block";
}
else {
	document.forms['form1'].elements['text_mail_see'].style.display = "none";	
	document.forms['form1'].elements['text_mail_see'].value="";
	document.forms['form1'].elements['text_name_see'].style.display = "none";	
	document.forms['form1'].elements['text_name_see'].value="";
}

}
</script>
<script type="text/javascript">
function addText2(val) {
if(val.value=="radio_name_see")
{
document.forms['form2'].elements['text_mail_see'].style.display = "none";	
document.forms['form2'].elements['text_mail_see'].value="";
document.forms['form2'].elements['text_name_see'].style.display = "block";
}
else if(val.value=="radio_mail_see")
{
document.forms['form2'].elements['text_name_see'].style.display = "none";	
document.forms['form2'].elements['text_name_see'].value="";
document.forms['form2'].elements['text_mail_see'].style.display = "block";
}
else {
	document.forms['form2'].elements['text_mail_see'].style.display = "none";	
	document.forms['form2'].elements['text_mail_see'].value="";
	document.forms['form2'].elements['text_name_see'].style.display = "none";	
	document.forms['form2'].elements['text_name_see'].value="";
}

}
</script>
<form name="form1">
<input type="radio" name="radio" value="radio_anyone_part" onclick="addText(this);"> Anyone(public) <br>
<input type="radio" name="radio" value="radio_mail_part" onclick="addText(this);"> Select by mail <br>
<input type="text" name="text_mail_part" style="display:none;">



<input type="radio" name="radio" value="radio_name_part" onclick="addText(this);"> Select by name <br>
<input type="text" name="text_name_part" style="display:none;">

<br>
<br>
</form>
<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">&nbsp; &nbsp; &nbsp;Who can see? </font><br></font></font>

<br>
<form name="form2">
<input type="radio" name="radio2" value="radio_anyone_see" onclick="addText2(this);"> Anyone(public) <br>
<input type="radio" name="radio2" value="radio_mail_see" onclick="addText2(this);"> Select by mail <br>
<input type="text" name="text_mail_see" style="display:none;">
<input type="radio" name="radio2" value="radio_name_see" onclick="addText2(this);"> Select by name <br>
<input type="text" name="text_name_see" style="display:none;">
</form>


</body>

</td>

<td>
<head>
<title>Untitled3</title>
</head>
<style type="text/css">
p.two
{
width: 350px; height: 450px;
border-style: solid;
border-color: #68caf9
}
</style>
<body>
<p class="two">

<br>

<font face="Calibri" size="4.2"><font color="#68caf9"><font color="#000080">&nbsp; &nbsp; &nbsp;What are the types of <br> &nbsp; &nbsp;&nbsp;&nbsp;the observations? </font><br></font></font>

<br>
<br>

<input type="checkbox" name="id" value="Java"> Poll<br>
<input type="checkbox" name="id" value="Text"> Text<br>
<input type="checkbox" name="id" value="Image"> Image<br>
<input type="checkbox" name="id" value="Audio record"> Audio record<br>
<input type="checkbox" name="id" value="Video record"> Video record<br>
</body>

</td>
</table>

<font face="Calibri"><br><a href="./welcome.jsp">create</a></font><br>  


<%@ include file = "./includes/footer.jsp" %>