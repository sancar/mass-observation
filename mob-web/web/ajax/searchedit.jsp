<%-- 
    Document   : more
    Created on : 24.Ara.2011, 12:49:13
    Author     : msk
--%>
<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%
    databaseConnections database=new databaseConnections();
    database.connect();
    ArrayList<HashMap<String, Object>> OEvents = new ArrayList<HashMap<String, Object>>();
    String query = request.getParameter("q").toString();
    if(query == null){
        query = "";
    }
    OEvents = database.search_my_events(session.getAttribute("email").toString(),request.getParameter("o").toString(), request.getParameter("a").toString(), query);

    if(!OEvents.isEmpty()) {
       for(int i=0;i<OEvents.size();i++) {
            HashMap<String, Object> newMap = (HashMap<String, Object>) OEvents.get(i);
            %>
            <a class="font_normal" href="./editOE.jsp?id=<%= newMap.get("event_id") %>"> <% out.print(newMap.get("event_name")); %></a> 
            <div class="classification"><div class="cover"></div><div class="progress" style="width: <%= Integer.parseInt(newMap.get("score").toString()) %>%;"></div></div>
            <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
            <hr width="100%" size="2"> 
 <%
     }
%>
               <a href="#editmore" id="editmore" onclick="callMore('<%= query %>',<%= Integer.parseInt(request.getParameter("o").toString()) + 4 %>,'edit')">Show me more of these</a>   
<% 
    }
%>