<%@ include file = "./includes/header.jsp" %>
<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<% 
	if(session.getAttribute("name") == null){// a user is not logged in 
		response.sendRedirect("./login.jsp");           
        }
        String id = (String) request.getParameter("id");
        if(id == null) // an event is not selected
            response.sendRedirect("welcome.jsp");
        //connect to database
        databaseConnections database=new databaseConnections();
        database.connect();
        //check if this event is created by current user 
        ArrayList<HashMap<String, Object>> event = new ArrayList<HashMap<String, Object>>();
        event = database.return_an_event(id);
         HashMap<String, Object> eventMap = (HashMap<String, Object>) event.get(0);
        if(!eventMap.get("created_by").toString().equals((String)session.getAttribute("email"))){
            response.sendRedirect("welcome.jsp");//event is created by somebody else
        }
        
%>
<div class="container">
    
    <a class="font_header"> <% out.print(eventMap.get("event_name")); %></a> 
    <a class="leftRed" href="DeleteOE?event_id<%= id%>">Delete this event completely !!!</a>
    <br>
    <p class="font_normal"><% out.print(eventMap.get("event_summary")); %></p>
    <div class="c1of2">
        <a class="font_header">Requests to see this observation</a><br>
<%
        ArrayList<HashMap<String, Object>> seeList = new ArrayList<HashMap<String, Object>>();
        seeList = database.return_see_requests(id);
        if(!seeList.isEmpty()) {
            for(int i=0;i<seeList.size();i++) {
                HashMap<String, Object> seeMap = (HashMap<String, Object>) seeList.get(i);
%>
                <a class="font_normal"><%= database.getUserName(seeMap.get("user_email").toString()) %></a>
                <a class="font_normal" href="GivePerm?type=see&event_id<%= id %>&email=<%= seeMap.get("user_email").toString() %>">Let in</a><br>
                <hr width="100%" size="2"> 
<%
            }
        }
%>              
    </div>
    
    <div class="c1of2">
        <a class="font_header">Requests to observe for this observation</a><br>
<%
        ArrayList<HashMap<String, Object>> observeList = new ArrayList<HashMap<String, Object>>();
        observeList = database.return_observe_requests(id);
        if(!observeList.isEmpty()) {
            for(int i=0;i<observeList.size();i++) {
                HashMap<String, Object> observeMap = (HashMap<String, Object>) observeList.get(i);
%>
                <a class="font_normal"><%= database.getUserName(observeMap.get("user_email").toString()) %></a>
                <a class="font_normal" href="GivePerm?type=observe&event_id<%= id %>&email=<%= observeMap.get("user_email").toString() %>">Let in</a><br>
                <hr width="100%" size="2"> 
                
<%
            }
        }
%>
    </div>
</div>
<%@ include file = "./includes/footer.jsp" %>