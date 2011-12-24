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
    OEvents = database.search_joined_events(session.getAttribute("email").toString(),request.getParameter("o").toString(), request.getParameter("a").toString(), query);
    boolean isAllowedToObserve;
    boolean isAllowedToSee;

    if(!OEvents.isEmpty()) {
        for(int i=0;i<OEvents.size();i++) {
            HashMap<String, Object> newMap = (HashMap<String, Object>) OEvents.get(i);
            %>
            <a class="font_normal" href="./observeOE.jsp?id=<%= newMap.get("event_id") %>"> <% out.print(newMap.get("event_name")); %></a> 

         <%

        if(database.isInitiator(newMap.get("event_id").toString(), (String)session.getAttribute("email"))) {
            isAllowedToObserve=true;
            isAllowedToSee=true;
        }
        else {
            isAllowedToObserve = database.isAllowed((String)session.getAttribute("email"),newMap.get("event_id").toString(),"observe");
            isAllowedToSee=database.isAllowed((String)session.getAttribute("email"),newMap.get("event_id").toString(),"see");
        }
            if(isAllowedToSee) {
                %><img src="pics/see.png" alt="You can see observations" title="You can see observations" /><%
            }else{
                %><a href="RequestPerm?event_id=<%= newMap.get("event_id") %>&type=see"  ><img src="pics/not_see.png"  onmouseover="showExpSee(<%= i %>)"  onmouseout="hideExpSee(<%= i %>)"  /></a><% 
            }
         %>
            <a id="see<%= newMap.get("event_id").toString() %>"></a>

         <%   
            if(isAllowedToObserve){
                %><img src="pics/add.png" alt="You can add observations" title="You can add observations" /><%
            }else{
                %><a href="RequestPerm?event_id=<%= newMap.get("event_id") %>&type=observe" ><img src="pics/not_add.png"  onmouseover="showExpObserve(<%= i %>)" onmouseout="hideExpObserve(<%= i %>)" /></a><%
            }
        %>         
           <a id="observe<%= newMap.get("event_id").toString() %>"></a>    
           <div>
               <form method="post" name="Vote" action="VoteEvent?event_id=<%= newMap.get("event_id")%>" onsubmit="return checkVote()">
                   <input type="radio" name="vote" value="1" /><a>1</a>
                   <input type="radio" name="vote" value="2" /><a>2</a>
                   <input type="radio" checked="checked" name="vote" value="3" /><a>3</a>
                   <input type="radio" name="vote" value="4" /><a>4</a>
                   <input type="radio" name="vote" value="5" /><a>5</a>
                   <input type="submit" name="Vote" value="Vote" />
               </form>
           </div>
            <div class="classification"><div class="cover"></div><div class="progress" style="width: <%= Integer.parseInt(newMap.get("score").toString())/20 %>;"></div></div>
            <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
            <a class="small"><% out.print(newMap.get("date_added")); %></a>
            <hr width="100%" size="2"> 
<%
            }
        %>
        <a href="#joinedmore" id="joinedmore" onclick="callMore('<%= query %>',<%= Integer.parseInt(request.getParameter("o").toString()) + 4 %>,'joined')">Show me more of these</a>
   
        <% 
    }
%>