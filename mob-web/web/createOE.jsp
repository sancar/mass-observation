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
        "<a class='font_normal'> Option </a><input type='text' name='optionsOf"+countPolls+"' />"+
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
        "<input type='text' name='tag"+ countPolls +"' size='10'>";
    document.getElementById("tags").appendChild(newdiv); 
    
}
</script>

<div class="container">

    <form action="CreateOE" name="CreateOE" method = "post" >

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
        <input type="radio" name="radio" value="radio_anyone_part" onclick="removeText(this);"><a class="font_normal"> Anyone(public) </a><br>
        <input type="radio" name="radio" value="radio_mail_part" onclick="addText(this);"><a class="font_normal"> Select by mail </a><br>
        <input type="text" name="text_mail_part" style="display:none;">
        <input type="radio" name="radio" value="radio_name_part" onclick="removeText(this);"><a class="font_normal"> Select by approval system</a><br>
        <br>
        <br>
        <a class="font_header">  Who can see? </a>
        <br>
        <input type="radio" name="radio2" value="radio_anyone_see" onclick="removeText(this);"><a class="font_normal"> Anyone(public) </a><br>
        <input type="radio" name="radio2" value="radio_mail_see" onclick="addText(this);"><a class="font_normal"> Select by mail </a><br>
        <input type="text" name="text_mail_see" style="display:none;"  >
        <input type="radio" name="radio2" value="radio_name_see" onclick="removeText(this);"><a class="font_normal"> Select by approval system </a><br>
   </div>

    <div class="c1of3">

        <a class="font_header">What are the types of the observations? </a>
        <br>
        <br>
                <input type="checkbox" name="cb_Poll" value="Poll"  onclick="checkbox(this)"><a class="font_normal"> Poll</a>
                <input type="button" onClick="addPoll()"  value="Add a new poll" id="newpoll" style="display: none;" ><br>
                <input type="checkbox" name="cb_Text" value="Text"><a class="font_normal"> Text</a><br>
                <input type="checkbox" name="cb_Image" value="Image"><a class="font_normal"> Image</a><br>
                <input type="checkbox" name="cb_Audio" value="Audio record"><a class="font_normal"> Audio record</a><br>
                <input type="checkbox" name="cb_Video" value="Video record"><a class="font_normal"> Video record</a><br>
                <br>
                <br>
                <input type="submit" name="CREATE" value="Create" >   
    </div>

    </form>
</div>

<div id="polls" name="polls" class="container" >
    
</div>
<%@ include file = "./includes/footer.jsp" %>