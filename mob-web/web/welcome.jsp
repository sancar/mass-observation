<%@ include file = "./includes/header.jsp" %> 

<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<script type="text/javascript">
    function more(q,offset,type,list){
    var xmlhttp;
    if (window.XMLHttpRequest)
      {// code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp=new XMLHttpRequest();
      }
    else
      {// code for IE6, IE5
      xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
      }
    xmlhttp.onreadystatechange=function()
      {
      if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            document.getElementById(type+"wait").style.visibility = "hidden"; 
            document.getElementById(type+"results").innerHTML += xmlhttp.responseText;
        }
      }
    
    xmlhttp.open("GET","./ajax/search"+type+".jsp?q="+q+"&o="+offset+"&a="+list,true);
    xmlhttp.send();
    }
    
    function callMore(q,offset,type,list){
        document.getElementById(type+"wait").style.visibility = "visible";
        document.getElementById(type+"results").removeChild(document.getElementById(type+'more'));
        var list;
         if( document.getElementById(type+"list").checked ){
             list = "score";
         }else{
             list = "date_added";
         }
        more(q,offset,type,list);
    }
    function callSearch(type){
         var cell = document.getElementById(type+"results");
         if ( cell.hasChildNodes() ){
            while ( cell.childNodes.length >= 1 ){
                cell.removeChild( cell.firstChild );       
            } 
         }    
         document.getElementById(type+"wait").style.visibility = "visible";
         var q = document.getElementById(type+"query").value;
         var list;
         if( document.getElementById(type+"list").checked ){
             list = "score";
         }else{
             list = "date_added";
         }
           
         more(q,0,type,list);
    }
    function showExpSee(vid){
        var alt = document.createElement("a");
        alt.style.color = "red";
        alt.innerHTML =  "You are not authorized to see observations. Click the observation to request  permision from initiator";
        document.getElementById("see" + vid).appendChild(alt);
    }
    function showExpObserve(vid){
        var alt = document.createElement("a");
        alt.style.color = "red";
        alt.innerHTML = "You are not authorized to add observations Click the observation to request permision from initiator";
        document.getElementById("observe" + vid).appendChild(alt);
    }
    function hideExpSee(vid){
        
        var a =document.getElementById("see" + vid);
        a.removeChild(a.lastChild);
    }
    function hideExpObserve(vid){
        var a =document.getElementById("observe" + vid);
        a.removeChild(a.lastChild);
    }
</script>
<% 
	if(session.getAttribute("name") == null && session.getAttribute("email") == null){ 
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
    <div class="c1of2" id="edit">
        <a class="font_header">Edit Observation Events created by me</a><br>
             <input type="text" id="editquery" name="txt_search" size="45">
             <input type="button" value="Search" onclick="callSearch('edit')"><br>
             <input type="radio"  name="editlist_" checked="checked" value="date_added" /><a>Most Recent</a>
             <input type="radio" name="editlist_" id="editlist" value="score" /><a>Top Rated</a>
        <br>
<%
        ArrayList<HashMap<String, Object>> OEList = new ArrayList<HashMap<String, Object>>();
        OEList = database.search_my_events((String)session.getAttribute("email"),"0", "date_added", "");
        if(!OEList.isEmpty()) {
%>
            <div id="editresults" >
<%
            for(int i=0;i<OEList.size();i++) {
                HashMap<String, Object> newMap = (HashMap<String, Object>) OEList.get(i);
                %>
                <a class="font_normal" href="./editOE.jsp?id=<%= newMap.get("event_id") %>"> <% out.print(newMap.get("event_name")); %></a> 
                <div class="classification"><div class="cover"></div><div class="progress" style="width: <%= Integer.parseInt(newMap.get("score").toString()) %>%;"></div></div>
                <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
                <a class="small"><% out.print(newMap.get("date_added")); %></a>
                <hr width="100%" size="2"> 
 <%
                }
%>
               <a href="#editmore" id="editmore" onclick="callMore('',4,'edit')">Show me more of these</a>
              
              </div><!-- END OF EDITRESULTS DIV --->
<%            
        }
%>
            <a id="editwait" style="color : red; visibility: hidden;" >Please Wait</a>
    </div><!-- END OF EDIT DIV --->

    <!--
    <div class="c1of3" id="joined">
        <a class="font_header">Joined Observation Events</a><br>
             <input type="text" id="joinedquery" name="txt_search" size="45">
             <input type="button" value="Search" onclick="callSearch('joined')"><br>
             <input type="radio" name="joinedlist_" checked="checked" value="date_added" /><a>Most Recent</a>
             <input type="radio" name="joinedlist_" id="joinedlist" value="score" /><a>Top Rated</a>
             <h2>NOT IMPLEMENTED YET</h2>
        <br>
    </div>
-->
    <div class="c1of2" id="all">
        
        <a class="font_header">All Observation Events</a><br>
             <input type="text" id="allquery" name="txt_search" size="45">
             <input type="button" value="Search" onclick="callSearch('all')"><br>
             <input type="radio" name="alllist_" checked="checked" value="date_added" /><a>Most Recent</a>
             <input type="radio"  name="alllist_" id="alllist" value="score" /><a>Top Rated</a>
        <br>
         <%
        ArrayList<HashMap<String, Object>> OEvents = new ArrayList<HashMap<String, Object>>();
        OEvents = database.search_all_events("0", "date_added", "");
        boolean isAllowedToObserve;
        boolean isAllowedToSee;
        
        if(!OEvents.isEmpty()) {
            %>
            <div id="allresults" >
            <%
            for(int i=0;i<OEvents.size();i++) {
                HashMap<String, Object> newMap = (HashMap<String, Object>) OEvents.get(i);
                String event_id = newMap.get("event_id").toString();
                %>
                <a class="font_normal" href="./observeOE.jsp?id=<%= newMap.get("event_id") %>"> <% out.print(newMap.get("event_name")); %></a> 
                
             <%
                
            if(database.isInitiator(event_id, (String)session.getAttribute("email"))) {
                isAllowedToObserve=true;
                isAllowedToSee=true;
            }
            else {
                isAllowedToObserve = database.isAllowed((String)session.getAttribute("email"),event_id,"observe");
                isAllowedToSee=database.isAllowed((String)session.getAttribute("email"),event_id,"see");
            }
                if(isAllowedToSee) {
                    %><img src="pics/see.png" alt="You can see observations" title="You can see observations" /><%
                }else{
                    %><a href="RequestPerm?event_id=<%= newMap.get("event_id") %>&type=see"  ><img src="pics/not_see.png"  onmouseover="showExpSee(<%= event_id %>)"  onmouseout="hideExpSee(<%= event_id %>)"  /></a><% 
                }
             %>
                <a id="see<%= event_id %>"> </a>
               
             <%   
                if(isAllowedToObserve){
                    %><img src="pics/add.png" alt="You can add observations" title="You can add observations" /><%
                }else{
                    %><a href="RequestPerm?event_id=<%= newMap.get("event_id") %>&type=observe" ><img src="pics/not_add.png"  onmouseover="showExpObserve(<%= event_id %>)" onmouseout="hideExpObserve(<%= event_id %>)" /></a><%
                }
            %>         
               <a id="observe<%= event_id %>"> </a>    
               <div>
                   <form method="post" name="Vote" action="VoteEvent?event_id=<%= newMap.get("event_id")%>">
                       <input type="radio" name="vote" value="1" /><a>1</a>
                       <input type="radio" name="vote" value="2" /><a>2</a>
                       <input type="radio" checked="checked" name="vote" value="3" /><a>3</a>
                       <input type="radio" name="vote" value="4" /><a>4</a>
                       <input type="radio" name="vote" value="5" /><a>5</a>
                       <input type="submit" name="Vote" value="Vote" />
                   </form>
               </div>
                <div class="classification"><div class="cover"></div><div class="progress" style="width: <%= Integer.parseInt(newMap.get("score").toString()) %>%;"></div></div>
                <p class="font_normal"><% out.print(newMap.get("event_summary")); %></p>
                <a class="small"><% out.print(newMap.get("date_added")); %></a>
                <hr width="100%" size="2"> 
 <%
                }
            %>
                <a href="#allmore" id="allmore" onclick="callMore('',4,'all')">Show me more of these</a>
                
             </div><!-- END OF ALLRESULTS DIV --->
            <%   
        }
        
 %>
             <a id="allwait" style="color : red; visibility: hidden;" >Please Wait</a>
    </div>
    <br style="clear: left; clear: right;" />
</div>
<br/>
<%
    database.close();
%>
<%@include file="./includes/footer.jsp"%>