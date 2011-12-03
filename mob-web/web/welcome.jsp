<%@ include file = "./includes/header.jsp" %> 

<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<% 
	if(session.getAttribute("name") == null){ 
		response.sendRedirect("./login.jsp"); 
        
		
       
        
                          
}
%>
<font face="Calibri"><br><a href="./createOE.jsp">Create a new observation event</a></font>	
<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
        <br><a class="font_normal"> <% out.print(message); %></a>
		<% 
	}
%>

<div class="container">
            OBSERVATION EVENTS CREATED BY ME 
            <br>
        <%
        databaseConnections database=new databaseConnections();
        database.connect();
        ArrayList<HashMap<String, Object>> OEList = new ArrayList<HashMap<String, Object>>();
        OEList = database.return_my_events((String)session.getAttribute("name"));
        if(!OEList.isEmpty()) {
            for(int i=0;i<OEList.size();i++) {
                HashMap<String, Object> newMap = (HashMap<String, Object>) OEList.get(i);
                %>
                <a href="./EditOE.jsp"> <% out.print(newMap.get("event_name")); %></a> 
                <br>
                <p><% out.print(newMap.get("event_summary")); %></p>
 <%
                }
        }
        
        %>
    <div class="c1of3">
        <a class="font_header">Observation Events created by me</a>
        <%
                ///oeedit.jsp?id=asda;//
       
        %>
    </div>

    <div class="c1of3">
        
    </div>

    <div class="c1of3">
        <form action="Search" name="Search" method="post">
             <input type="text" name="txt_search" size="48">
             <input type="button" value="Search" name="btn_search">
        </form>
    </div>
    <br style="clear: left; clear: right;" />
</div>
<br/>

<%@include file="./includes/footer.jsp"%>