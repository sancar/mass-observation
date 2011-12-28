<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
		response.sendRedirect("./login.jsp");
	}

%>


<script type="text/javascript">
    var countPolls = 0;
    var countOptions = 0;
    var countTags = 0;
function checkForm(){
    var x=document.forms["CreateOE"];
    var errorDiv = document.getElementById("error");
    var flag = 0;
    var newError;
    while (errorDiv.hasChildNodes()) {
        errorDiv.removeChild(errorDiv.lastChild);
    }


    if(x["txt_eventname"].value == "" || x["txt_eventname"] == null){
        newError = document.createElement("a");
        newError.style.color = "red";  
        newError.innerHTML =("please enter an event name<br>");
        errorDiv.appendChild(newError);
        flag = 1;
    }
    if(x["txt_summary"].value == "" || x["txt_summary"] == null){
        newError = document.createElement("a");
        newError.style.color = "red";  
        newError.innerHTML =("please enter a summary<br>");
        errorDiv.appendChild(newError);
        flag = 1;
    }
    if(!document.getElementById("radio_3").checked &&
       !document.getElementById("radio_1").checked &&
       !document.getElementById("radio_2").checked) {
        newError = document.createElement("a");         
        newError.style.color = "red";          
        newError.innerHTML =("please select who can participate (upload observation) to this event<br>");
        errorDiv.appendChild(newError);
        flag = 1;
    }
    if(!document.getElementById("radio2_3").checked &&
       !document.getElementById("radio2_1").checked &&
       !document.getElementById("radio2_2").checked){
        newError = document.createElement("a");         
        newError.style.color = "red";         
        newError.innerHTML =("please select who can see this event<br>");
        errorDiv.appendChild(newError);
        flag = 1;
    }
    if(!document.getElementById("cb_Poll").checked &&
       !document.getElementById("cb_Text").checked &&
       !document.getElementById("cb_Audio").checked &&
       !document.getElementById("cb_Video").checked &&
       !document.getElementById("cb_Image").checked )
   {
        newError = document.createElement("a");         
       newError.style.color = "red";           
        newError.innerHTML =("please select at least one observation event type<br>");
        errorDiv.appendChild(newError);
        flag = 1;
   }
   if(flag)
       return false;
   else
       return true;
}    
function addText(val) {
    if (val.value == "radio_mail_part") {

        document.forms['CreateOE'].elements['text_mail_part'].style.display = "block";
        	
    }else if(val.value=="radio_mail_see")
    {
           
            document.forms['CreateOE'].elements['text_mail_see'].style.display = "block";
    }
    
}
function removeText(val) {
    if (val.value == "radio_name_part" || val.value == "radio_anyone_part") {

        document.forms['CreateOE'].elements['text_mail_part'].style.display = "none";
        	
    }else if(val.value=="radio_name_see" || val.value == "radio_anyone_see")
    {
           
            document.forms['CreateOE'].elements['text_mail_see'].style.display = "none";
    }
}
function addPoll(){

    countPolls++;
    countOptions++;

    var newdiv = document.createElement("div");
    newdiv.className = "c1of3";
    newdiv.name = "poll";
    newdiv.id = "poll" + countPolls;

    newdiv.innerHTML = 
        "<a class='font_header'> New Poll </a>"+
        "<a href='#polls' class='font_normal' onClick='removePoll("+ countPolls +")' >remove</a><br>" +
        "<a class='font_normal'> Question </a><input type='text' name='poll_name' /><br><br>"+
        "<a class='font_normal'> Option </a><input type='text' name='poll"+countPolls+"' />"+
        "<input type='button' id='"+countPolls+","+countOptions+"' onclick='addOption("+countOptions+","+ countPolls +")'  value='Add Option' /><br>";
    document.getElementById("polls").appendChild(newdiv); 

}
function checkbox(val){
    if(!val.checked){
        document.getElementById("newpoll").style.display = "none";
        var polls = document.getElementById("polls");
        
        while ( polls.childNodes.length >= 1 )
        {
            polls.removeChild( polls.firstChild );       
        } 
        countOptions = 0;
        countPolls = 0;
    }else{
        document.getElementById("newpoll").style.display = "block";
    }
}
function addOption(pOptionID,pollID){
    document.getElementById(  pollID + "," +pOptionID  ).style.display = "none";
    pOptionID++;
    
    var newdiv = document.createElement("div");
    newdiv.innerHTML = 
         "<a class='font_normal'> Option </a><input type='text' name='poll"+pollID+"' />"+
        "<input type='button' id='"+pollID+","+pOptionID+"' onclick='addOption("+pOptionID+","+ pollID +")'  value='Add Option' /><br>";
    
    
    document.getElementById("poll"+pollID).appendChild(newdiv);
   
}
function removePoll(pollID){
    document.getElementById("polls").removeChild(document.getElementById("poll"+pollID));
}
function addTag(){
    countTags++;
    var newdiv = document.createElement("div");
    newdiv.name = "tag";
    newdiv.id = "tag" + countTags;

    newdiv.innerHTML = 
        "<input type='text' name='tag"+ countTags +"' size='10'>";
    document.getElementById("tags").appendChild(newdiv); 
    
}
</script>

<form action="CreateOE" name="CreateOE" method = "post" onsubmit="return checkForm()" >
<div class="container">
    <div id="error">
        
    </div>
    <div class="c1of3"> 
        <a class="font_header">Name of the Observation Event </a><br>
        <input type="text" name="txt_eventname" size="30"><br>
        <a class="font_header">Summary</a><br>
        <textarea name="txt_summary" rows="6" id="txt_summary"  style="width:250px;"></textarea><br>
        <a class="font_header">Tags</a><br>
        <div id="tags">
            <input type="text" name="tag0" size="10">
        </div>
        <a href="#tags" onclick="addTag()" class="font_normal">Add a new tag</a><br>
    </div>
   
    <div class="c1of3">
        <a class="font_header">  Who can participate? </a><br>       
        <input type="radio" id="radio_1" name="radio" value="radio_anyone_part" onclick="removeText(this);"><a class="font_normal"> Anyone(public) </a><br>
        <input type="radio" id="radio_2" name="radio" value="radio_mail_part" onclick="addText(this);"><a class="font_normal"> Select by mail </a><br>
        <input type="text" name="text_mail_part" style="display:none;">
        <input type="radio" id="radio_3" name="radio" value="radio_name_part" onclick="removeText(this);"><a class="font_normal"> Select by approval system</a><br>
        <br>
        <br>
        <a class="font_header">  Who can see? </a>
        <br>
        <input type="radio" id="radio2_1" name="radio2" value="radio_anyone_see" onclick="removeText(this);"><a class="font_normal"> Anyone(public) </a><br>
        <input type="radio" id="radio2_2" name="radio2" value="radio_mail_see" onclick="addText(this);"><a class="font_normal"> Select by mail </a><br>
        <input type="text" name="text_mail_see" style="display:none;"  >
        <input type="radio" id="radio2_3" name="radio2" value="radio_name_see" onclick="removeText(this);"><a class="font_normal"> Select by approval system </a><br>
   </div>

    <div class="c1of3">

        <a class="font_header">What are the types of the observations? </a>
        <br>
        <br>
                <input type="checkbox" id="cb_Poll" name="cb_Poll" value="Poll"  onclick="checkbox(this)"><a class="font_normal"> Poll</a>
                <input type="button" onClick="addPoll()"  value="Add a new poll" id="newpoll" style="display: none;" ><br>
                <input type="checkbox" id="cb_Text" name="cb_Text" value="Text"><a class="font_normal"> Text</a><br>
                <input type="checkbox" id="cb_Image" name="cb_Image" value="Image"><a class="font_normal"> Image</a><br>
                <input type="checkbox" id="cb_Audio" name="cb_Audio" value="Audio record"><a class="font_normal"> Audio record</a><br>
                <input type="checkbox" id="cb_Video" name="cb_Video" value="Video record"><a class="font_normal"> Video record</a><br>
                <br>
                <br>
                <input type="submit" name="CREATE" value="Create" >   
    </div>

    
</div>

<div id="polls" name="polls" class="container" >
    
</div>
</form>
<%@ include file = "./includes/footer.jsp" %>