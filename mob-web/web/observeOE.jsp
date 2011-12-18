<%@ include file = "./includes/header.jsp" %>
<%@ page import="newpackage.databaseConnections" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<script type="text/javascript">
function showComments(val){
    document.getElementById(val).style.display = "block";
}   
    
</script>
<% 
	if(session.getAttribute("name") == null){ 
		response.sendRedirect("./login.jsp");           
        }
%>
<%
    String baseUrl = "http://titan.cmpe.boun.edu.tr:8082/observations";
    String id = (String) request.getParameter("id");
    if(id == null)
        response.sendRedirect("welcome.jsp");
%>
<%
    databaseConnections database=new databaseConnections();
    database.connect();
    boolean isAllowedToObserve;
    boolean isAllowedToSee;
    if(database.isInitiator(id, session.getAttribute("email").toString())) {
        isAllowedToObserve=true;
        isAllowedToSee=true;
    }
    else {
        isAllowedToObserve = database.isAllowed(session.getAttribute("email").toString(),id,"observe");
        isAllowedToSee=database.isAllowed(session.getAttribute("email").toString(),id,"see");
    }
if(!isAllowedToObserve){
%> 
    <p style="color:red;" class="font_header">You are not allowed to add observation to this event.
    <a href="RequestPerm?event_id=<%= id %>&type=observe">Click</a> to request permission from user</p>
<%
}
if(!isAllowedToSee){
%> 
    <p style="color:red;" class="font_header">You are not allowed to see this page. 
    <a href="RequestPerm?event_id=<%= id %>&type=see">Click</a> to request permission from user</p>
<%    
}else{
%>
<%
    ArrayList<HashMap<String, Object>> Oevent = new ArrayList<HashMap<String, Object>>();
    Oevent = database.return_an_event(id); 
    HashMap<String, Object> eventMap = (HashMap<String, Object>) Oevent.get(0);
%>
    <div class="container">
        <a class="font_header"> <% out.print(eventMap.get("event_name")); %></a> <br>
        <p class="font_normal"><% out.print(eventMap.get("event_summary")); %></p>
        <!-- TEXT OBSERVATIONS -->
<%
    if((Integer)eventMap.get("text") == 1){
%>
        <div class="c1of2">
            <a class="font_header">Text Observations</a><br>
<%
        ArrayList<HashMap<String, Object>> OEtextList = new ArrayList<HashMap<String, Object>>();
        OEtextList = database.return_texts(id);
        if(!OEtextList.isEmpty()) {
            for(int i=0;i<OEtextList.size();i++) {
                HashMap<String, Object> textMap = (HashMap<String, Object>) OEtextList.get(i);
 
                if((Integer)textMap.get("name_visible") == 1){
 %>                <a class="font_normal">by <% out.print(database.getUserName(textMap.get("supplied_by").toString())); %></a><br>                         
 <%             }else{
 %>                <a class="font_normal">by Anonymous</a><br>    
 <%             }
 %>
                <a class="font_normal" > <% out.print(textMap.get("text")); %></a> <br> 
                
                <a class="font_normal"  href="javascript:showComments('textcomment<%= i %>');" >Show Comments</a><br>
                <div class="comment" id="textcomment<%= i %>" style="display: none;">
                    <!-- TEXT OBSERVATIONS COMMENTS-->
 <%       
                ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
                commentList = database.return_comments(((Integer)textMap.get("text_id")).toString(),"text");
                if(!commentList.isEmpty()) {
                    for(int j=0;j<commentList.size();j++) {
                         HashMap<String, Object> commentMap = (HashMap<String, Object>) commentList.get(j);
                        if((Integer)commentMap.get("name_visible") == 1){
         %>                <a class="font_normal">by <% out.print(database.getUserName(commentMap.get("owner").toString())); %></a><br>                         
         <%             }else{
         %>                <a class="font_normal">by Anonymous</a><br>    
         <%             }
         %>                      
                        <a class="font_normal" > <% out.print(commentMap.get("comment")); %></a> <br>   
                        <hr width="100%" size="2">   
<%                                              
                    }
                }
%>                  <!-- ADD TEXT OBSERVATIONS  COMMENT -->
                    <form action="AddComment?text_id=<% out.print(textMap.get("text_id")); %>&event_id=<%= eventMap.get("event_id") %>" method="post">
                           <textarea name="Comment" cols="40" rows="3"></textarea><br>
                           <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                           <input type="submit" value="submit" name="submit">
                     </form>
                </div>  
                <hr width="100%" size="2">   
<%
            }
        }
        if(isAllowedToObserve){

%>
 
            <br><!-- ADD TEXT OBSERVATION -->
            <a class="font_normal">Add a new text observation</a>
            <form action="AddText?event_id=<%= id %>" method="post">
                <textarea name="textOE" cols="50" rows="5"></textarea><br>
                <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                <input type="submit" value="submit" name="submit">
            </form>     
 <%     }
 %>    </div>
 <%
    }
    if((Integer)eventMap.get("poll") == 1){
%>
        <div class="c1of2">
            <!-- POLL OBSERVATIONS -->
            <a class="font_header">Poll Observations</a><br>
<%       ArrayList<HashMap<String, Object>> OEpollList = new ArrayList<HashMap<String, Object>>();
        OEpollList = database.return_polls(id);
        if(!OEpollList.isEmpty()) {
            for(int i=0;i<OEpollList.size();i++) {
                HashMap<String, Object> pollMap = (HashMap<String, Object>) OEpollList.get(i);
 
                if((Integer)pollMap.get("name_visible") == 1){
 %>                <a class="font_normal">by <% out.print(database.getUserName(pollMap.get("supplied_by").toString())); %></a><br>                         
 <%             }else{
 %>                <a class="font_normal">by Anonymous</a><br>    
 <%             }
              if(database.voteCheck((pollMap.get("poll_id")).toString(),session.getAttribute("email").toString())) {
 %>                   <a class="font_normal" > <% out.print("You have voted for this poll!"); %></a> <br>
 <%               }
 

 %>               <a class="font_normal" > <% out.print(pollMap.get("text")); %></a> <br> 
 <%       
           
                ArrayList<HashMap<String, Object>> choiceList = new ArrayList<HashMap<String, Object>>();
                choiceList = database.return_choices(((Integer)pollMap.get("poll_id")).toString());
                             
                if(!choiceList.isEmpty()) {
                    
 %>             <!-- ADD POLL OBSERVATION -->    
                <form method="post" action="AddPoll?poll_id=<%= pollMap.get("poll_id") %>&event_id=<%= id %>"  >
                    
 <%
                    for(int j=0;j<choiceList.size();j++) {
                         HashMap<String, Object> choiceMap = (HashMap<String, Object>) choiceList.get(j);                      
 %>
                             <a class="font_normal"><% out.print(choiceMap.get("choice_name")); %></a>
                             <a class="font_normal">(<% out.print(database.return_n_answers(((Integer)choiceMap.get("choice_id")).toString())) ; %>)</a>   
 <%                    if(isAllowedToObserve){                            
 %>                          <input type="radio" value="<% out.print(choiceMap.get("choice_id")); %>"  name="poll_answer">                                     
 <%
                       }
%>                      <br>
<%
                    }
                     if(isAllowedToObserve){                     
 %>                     <input type="submit" name="Vote" value="Vote" > 
 <%                    }
 %>                    </form>    
 <%
              }
           
%>              <!-- POLL OBSERVATIONS COMMENTS -->                 
                <a class="font_normal"  href="javascript:showComments('pollcomment<%= i %>');" >Show Comments</a><br>
                <div class="comment" id="pollcomment<%= i %>" style="display: none;">
 <%       
                ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
                commentList = database.return_comments(((Integer)pollMap.get("poll_id")).toString(),"poll");
                if(!commentList.isEmpty()) {
                    for(int j=0;j<commentList.size();j++) {
                         HashMap<String, Object> commentMap = (HashMap<String, Object>) commentList.get(j);
                        if((Integer)commentMap.get("name_visible") == 1){
         %>                <a class="font_normal">by <% out.print(database.getUserName(commentMap.get("owner").toString())); %></a><br>                         
         <%             }else{
         %>                <a class="font_normal">by Anonymous</a><br>    
         <%             }
         %>                      
                        <a class="font_normal" > <% out.print(commentMap.get("comment")); %></a> <br> 
                        <hr width="100%" size="2">   
<%                                              
                    }
                }
%>          <!-- ADD POLL OBSERVATIONS COMMENT -->
                <form action="AddComment?poll_id=<% out.print(pollMap.get("poll_id")); %>&event_id=<%= id %>" method="post">
                       <textarea name="Comment" cols="40" rows="3"></textarea><br>
                       <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                       <input type="submit" value="submit" name="submit">
                 </form>
            </div> 
            <hr width="100%" size="2">    
<%
            }
        }
 %>
        </div>
 <%
    }
    if((Integer)eventMap.get("image") == 1){
 %>
        <div class="c1of2">
            <a class="font_header">Image Observations</a><br>  
 <%
        ArrayList<HashMap<String, Object>> OEimageList = new ArrayList<HashMap<String, Object>>();
        OEimageList = database.return_images(id);
        if(!OEimageList.isEmpty()) {
            for(int i=0;i<OEimageList.size();i++) {
                HashMap<String, Object> imageMap = (HashMap<String, Object>) OEimageList.get(i);
 %>              <!--- IMAGE OBSERVATIONS --->
                <img src="<%= baseUrl %>/images/<%= imageMap.get("url").toString() %>" />
                <!-- IMAGE OBSERVATIONS COMMENTS -->                 
                <a class="font_normal"  href="javascript:showComments('imagecomment<%= i %>');" >Show Comments</a><br>
                <div class="comment" id="imagecomment<%= i %>" style="display: none;">
 <%       
                ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
                commentList = database.return_comments(imageMap.get("url").toString(),"image");
                if(!commentList.isEmpty()) {
                    for(int j=0;j<commentList.size();j++) {
                         HashMap<String, Object> commentMap = (HashMap<String, Object>) commentList.get(j);
                        if((Integer)commentMap.get("name_visible") == 1){
         %>                <a class="font_normal">by <% out.print(database.getUserName(commentMap.get("owner").toString())); %></a><br>                         
         <%             }else{
         %>                <a class="font_normal">by Anonymous</a><br>    
         <%             }
         %>                      
                        <a class="font_normal" > <% out.print(commentMap.get("comment")); %></a> <br> 
                        <hr width="100%" size="2">   
<%                                              
                    }
                }
%>          <!-- ADD IMAGE OBSERVATIONS COMMENT -->
                <form action="AddComment?image_url=<% out.print(imageMap.get("url")); %>&event_id=<%= id %>" method="post">
                       <textarea name="Comment" cols="40" rows="3"></textarea><br>
                       <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                       <input type="submit" value="submit" name="submit">
                 </form>
            </div> 
                <hr width="100%" size="2">  
 <%           
            }
        }
        if(isAllowedToObserve){ 
%>          <!--- ADD IMAGE OBSERVATION --->
            <form action="AddImage?event_id=<%= id %>" method="post" enctype="multipart/form-data">
              Select a file: 
              <input type="file" name="first" />
              <br />
              <input type="submit" name="button" value="upload" />
            </form>        
<%      }
%>   </div>
<%
    }
    if((Integer)eventMap.get("audio") == 1){
    %>
            <div class="c1of2">
                <a class="font_header">Audio Observations</a><br>
    <%
        ArrayList<HashMap<String, Object>> OEaudioList = new ArrayList<HashMap<String, Object>>();
        OEaudioList = database.return_audios(id);
        if(!OEaudioList.isEmpty()) {
            for(int i=0;i<OEaudioList.size();i++) {
                HashMap<String, Object> audioMap = (HashMap<String, Object>) OEaudioList.get(i);
 %>              <!--- audio OBSERVATIONS --->
                 <audio controls="controls">
                  <source src="<%= baseUrl %>/audios/<%= audioMap.get("url").toString() %>" type="audio/mpeg" />                 
                  <embed height="50px" width="100px" src="<%= baseUrl %>/audios/<%= audioMap.get("url").toString() %>" />
                </audio>
                <!-- audio OBSERVATIONS COMMENTS -->                 
                <a class="font_normal"  href="javascript:showComments('audiocomment<%= i %>');" >Show Comments</a><br>
                <div class="comment" id="audiocomment<%= i %>" style="display: none;">
 <%       
                ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
                commentList = database.return_comments(audioMap.get("url").toString(),"audio");
                if(!commentList.isEmpty()) {
                    for(int j=0;j<commentList.size();j++) {
                         HashMap<String, Object> commentMap = (HashMap<String, Object>) commentList.get(j);
                        if((Integer)commentMap.get("name_visible") == 1){
         %>                <a class="font_normal">by <% out.print(database.getUserName(commentMap.get("owner").toString())); %></a><br>                         
         <%             }else{
         %>                <a class="font_normal">by Anonymous</a><br>    
         <%             }
         %>                      
                        <a class="font_normal" > <% out.print(commentMap.get("comment")); %></a> <br> 
                        <hr width="100%" size="2">   
<%                                              
                    }
                }
%>          <!-- ADD audio OBSERVATIONS COMMENT -->
                <form action="AddComment?audio_url=<% out.print(audioMap.get("url")); %>&event_id=<%= id %>" method="post">
                       <textarea name="Comment" cols="40" rows="3"></textarea><br>
                       <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                       <input type="submit" value="submit" name="submit">
                 </form>
            </div> 
                <hr width="100%" size="2">  
 <%           
            }
        }
        if(isAllowedToObserve){ 
%>          <!--- ADD audio OBSERVATION --->
            <form action="AddAudio?event_id=<%= id %>" method="post" enctype="multipart/form-data">
              Select a file: 
              <input type="file" name="first" />
              <br />
              <input type="submit" name="button" value="upload" />
            </form>
<%      }
%>   </div>
<%  
    }
    if((Integer)eventMap.get("video") == 1){
    %>
            <div class="c1of2">
                <a class="font_header">Video Observations</a><br>
           <%
        ArrayList<HashMap<String, Object>> OEvideoList = new ArrayList<HashMap<String, Object>>();
        OEvideoList = database.return_videos(id);
        if(!OEvideoList.isEmpty()) {
            for(int i=0;i<OEvideoList.size();i++) {
                HashMap<String, Object> videoMap = (HashMap<String, Object>) OEvideoList.get(i);
 %>              <!--- video OBSERVATIONS --->
                    <video width="320" height="240" controls="controls">
                      <source src="<%= baseUrl %>/videos/<%= videoMap.get("url").toString() %>" type="video/ogg" />
                      <source src="<%= baseUrl %>/videos/<%= videoMap.get("url").toString() %>" type="video/mp4" />
                      <source src="<%= baseUrl %>/videos/<%= videoMap.get("url").toString() %>" type="video/webm" />
                    <object width="320" height="240" src="<%= baseUrl %>/videos/<%= videoMap.get("url").toString() %>">
                    <embed width="320" height="240" src="<%= baseUrl %>/videos/<%= videoMap.get("url").toString() %>">
                    Your browser does not support video
                    </embed>
                    </object>
                    </video>
                <!-- video OBSERVATIONS COMMENTS -->                 
                <a class="font_normal"  href="javascript:showComments('videocomment<%= i %>');" >Show Comments</a><br>
                <div class="comment" id="videocomment<%= i %>" style="display: none;">
 <%       
                ArrayList<HashMap<String, Object>> commentList = new ArrayList<HashMap<String, Object>>();
                commentList = database.return_comments(videoMap.get("url").toString(),"video");
                if(!commentList.isEmpty()) {
                    for(int j=0;j<commentList.size();j++) {
                         HashMap<String, Object> commentMap = (HashMap<String, Object>) commentList.get(j);
                        if((Integer)commentMap.get("name_visible") == 1){
         %>                <a class="font_normal">by <% out.print(database.getUserName(commentMap.get("owner").toString())); %></a><br>                         
         <%             }else{
         %>                <a class="font_normal">by Anonymous</a><br>    
         <%             }
         %>                      
                        <a class="font_normal" > <% out.print(commentMap.get("comment")); %></a> <br> 
                        <hr width="100%" size="2">   
<%                                              
                    }
                }
%>          <!-- ADD video OBSERVATIONS COMMENT -->
                <form action="AddComment?video_url=<% out.print(videoMap.get("url")); %>&event_id=<%= id %>" method="post">
                       <textarea name="Comment" cols="40" rows="3"></textarea><br>
                       <a class="font_normal">Anonymous</a><input type="checkbox" value="anonymous" name="anonymous" />
                       <input type="submit" value="submit" name="submit">
                 </form>
            </div> 
                <hr width="100%" size="2">  
 <%           
            }
        }
        if(isAllowedToObserve){ 
%>          <!--- ADD video OBSERVATION --->
            <form action="AddVideo?event_id=<%= id %>" method="post" enctype="multipart/form-data">
              Select a file: 
              <input type="file" name="first" />
              <br />
              <input type="submit" name="button" value="upload" />
            </form>
<%      }
%>      </div>
<%  
    }
}
%>
    </div>
<%@ include file = "./includes/footer.jsp" %>