<%@ include file = "./includes/header.jsp" %>

<%
	if(session.getAttribute("name") == null){
		response.sendRedirect("./login.jsp");
	}

%>


<script type="text/javascript">
function addText(val) {
if (val.value == "radio_mail_part") {

document.forms['CreateOE'].elements['text_mail_part'].style.display = "block";
document.forms['CreateOE'].elements['text_name_part'].style.display = "none";	
document.forms['CreateOE'].elements['text_name_part'].value="";
}
else if(val.value=="radio_name_part")
	{
	document.forms['CreateOE'].elements['text_mail_part'].style.display = "none";	
	document.forms['CreateOE'].elements['text_mail_part'].value="";
	document.forms['CreateOE'].elements['text_name_part'].style.display = "block";
	}
else {
	document.forms['CreateOE'].elements['text_mail_part'].style.display = "none";	
	document.forms['CreateOE'].elements['text_mail_part'].value="";
	document.forms['CreateOE'].elements['text_name_part'].style.display = "none";	
	document.forms['CreateOE'].elements['text_name_part'].value="";
}
if(val.value=="radio_name_see")
{
document.forms['CreateOE'].elements['text_mail_see'].style.display = "none";	
document.forms['CreateOE'].elements['text_mail_see'].value="";
document.forms['CreateOE'].elements['text_name_see'].style.display = "block";
}
else if(val.value=="radio_mail_see")
{
document.forms['CreateOE'].elements['text_name_see'].style.display = "none";	
document.forms['CreateOE'].elements['text_name_see'].value="";
document.forms['CreateOE'].elements['text_mail_see'].style.display = "block";
}
else {
	document.forms['CreateOE'].elements['text_mail_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_mail_see'].value="";
	document.forms['CreateOE'].elements['text_name_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_name_see'].value="";
}

}
</script>
<script type="text/javascript">
function addText2(val) {
if(val.value=="radio_name_see")
{
	document.forms['CreateOE'].elements['text_mail_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_mail_see'].value="";
	document.forms['CreateOE'].elements['text_name_see'].style.display = "block";
}
else if(val.value=="radio_mail_see")
{
	document.forms['CreateOE'].elements['text_name_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_name_see'].value="";
	document.forms['CreateOE'].elements['text_mail_see'].style.display = "block";
}
else {
	document.forms['CreateOE'].elements['text_mail_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_mail_see'].value="";
	document.forms['CreateOE'].elements['text_name_see'].style.display = "none";	
	document.forms['CreateOE'].elements['text_name_see'].value="";
}

}
</script>





<div class="container">

    <form action="CreateOE" name="CreateOE" method = "post" >

    <div class="c1of3">
    
        <a class="font_header">Name of the Observation Event </a>

        <br>

        <input type="text" name="txt_eventname" size="30">
        
        <br>
         
        <a class="font_header">Summary</a>
        
        <br>
        
        <textarea name="txt_summary" rows="6" id="txt_summary"  style="width:250px;"></textarea>

    </div>

   
    <div class="c1of3">

      

        <a class="font_header">  Who can participate? </a>

        <br>
        <input type="radio" name="radio" value="radio_anyone_part" onclick="addText(this);"> Anyone(public) <br>
        <input type="radio" name="radio" value="radio_mail_part" onclick="addText(this);"> Select by mail <br>
        <input type="text" name="text_mail_part" style="display:none;">
        <input type="radio" name="radio" value="radio_name_part" onclick="addText(this);"> Select by name <br>
        <input type="text" name="text_name_part" style="display:none;">

        <br>
        <br>

        <a class="font_header">  Who can see? </a>

        <br>
        <input type="radio" name="radio2" value="radio_anyone_see" onclick="addText2(this);"> Anyone(public) <br>
        <input type="radio" name="radio2" value="radio_mail_see" onclick="addText2(this);"> Select by mail <br>
        <input type="text" name="text_mail_see" style="display:none;">
        <input type="radio" name="radio2" value="radio_name_see" onclick="addText2(this);"> Select by name <br>
        <input type="text" name="text_name_see" style="display:none;">


    </div>

    <div class="c1of3">

        <a class="font_header">What are the types of the observations? </a>

        <br>
        <br>

                <input type="checkbox" name="id" value="Java"> Poll<br>
                <input type="checkbox" name="id" value="Text"> Text<br>
                <input type="checkbox" name="id" value="Image"> Image<br>
                <input type="checkbox" name="id" value="Audio record"> Audio record<br>
                <input type="checkbox" name="id" value="Video record"> Video record<br>
                <br>
                <br>
                <input type="submit" name="CREATE" value="Create" >  
    </div>

    </form>
</div>

<%@ include file = "./includes/footer.jsp" %>