<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;">
    <title>MOB</title>
    <style type="text/css"></style> 
    <link rel="stylesheet" href="design.css">
</head>
<body>
<a id="logo" href="./welcome.jsp">
    <font id="logo_head" > mass</font><font id="logo_tail">observation</font>
</a>
<%
	if(session.getAttribute("name") != null){
	%>	
        <div style="float:right;"> 
            <a class="font_normal">Welcome <% out.print(session.getAttribute("name"));%></a>
            <a href="./Logout">Log Out</a>  
        </div>
        <%                
	}

%>
<hr width="100%" size="2">
