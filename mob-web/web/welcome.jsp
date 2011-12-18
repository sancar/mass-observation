<%@ include file = "./includes/header.jsp" %> 

<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<script type="text/javascript">
    function showExpSee(id){
        var alt = document.createElement("a");
        alt.style.color = "red";
        alt.innerHTML =  "You are not authorized to see observations. Click the observation to request  permision from initiator";
        document.getElementById("see" + id).appendChild(alt);
    }
    function showExpObserve(id){
        var alt = document.createElement("a");
        alt.style.color = "red";
        alt.innerHTML = "You are not authorized to add observations Click the observation to request permision from initiator";
        document.getElementById("observe" + id).appendChild(alt);
    }
    function hideExpSee(id){
        
        var a =document.getElementById("see" + id);
        a.removeChild(a.lastChild);
    }
    function hideExpObserve(id){
        var a =document.getElementById("observe" + id);
        a.removeChild(a.lastChild);
    }
</script>
<% 
	if(session.getAttribute("name") == null || session.getAttribute("email") == null){ 
		response.sendRedirect("./login.jsp");           
        }
%>
<%
        databaseConnections database=new databaseConnections();
        database.connect();
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
    <div class="c1of3">
        <a class="font_header">Edit Observation Events created by me</a><br>
        <form action="Search" name="Search" method="post">
             <input type="text" name="txt_search" size="48">
             <input type="button" value="Search" name="btn_search">
             
        </form>
        <br>
<%
        ArrayList<HashMap<String, Object>> OEList = new ArrayList<HashMap<String, Object>>();
        OEList = database.return_my_events((String)session.getAttribute("email"));
        if(!OEList.isEmpty()) {
            for(int i=0;i<OEList.size();i++) {
                HashMap<String, Object> newMap = (HashMap<String, Object>) OEList.get(i);
                %>
                <a class="font_normal" href="./editOE.jsp?id=<%= newMap.get("event_id") %>"> <% out.print(newMap.get("event_name")); %></a> 
                <br>
                <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
                <hr width="100%" size="2"> 
 <%
                }
        }
        
 %>
  
    </div>

    <div class="c1of3">
        <a class="font_header">Joined Observation Events</a><br>
       <form action="Search" name="Search" method="post">
             <input type="text" name="txt_search" size="48">
             <input type="button" value="Search" name="btn_search">
             
        </form>
        <br>
    </div>

    <div class="c1of3">
        
        <a class="font_header">All Observation Events</a><br>
        <form action="Search" name="Search" method="post">
             <input type="text" name="txt_search" size="48">
             <input type="button" value="Search" name="btn_search">
             
        </form>
        <br>
         <%
        ArrayList<HashMap<String, Object>> OEpublicList = new ArrayList<HashMap<String, Object>>();
        OEpublicList = database.return_all_events();
        boolean isAllowedToObserve;
        boolean isAllowedToSee;
        
        if(!OEpublicList.isEmpty()) {
            for(int i=0;i<OEpublicList.size();i++) {
                HashMap<String, Object> newMap = (HashMap<String, Object>) OEpublicList.get(i);
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
                <a id="see<%= i %>"></a>
               
             <%   
                if(isAllowedToObserve){
                    %><img src="pics/add.png" alt="You can add observations" title="You can add observations" /><%
                }else{
                    %><a href="RequestPerm?event_id=<%= newMap.get("event_id") %>&type=observe" ><img src="pics/not_add.png"  onmouseover="showExpObserve(<%= i %>)" onmouseout="hideExpObserve(<%= i %>)" /></a><%
                }
            %>         
               <a id="observe<%= i %>"></a>    
                <br>
                <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
                <hr width="100%" size="2"> 
 <%
                }
        }
        
 %>
    </div>
    <br style="clear: left; clear: right;" />
</div>
<br/>
<%
    database.close();
%>
<%@include file="./includes/footer.jsp"%>