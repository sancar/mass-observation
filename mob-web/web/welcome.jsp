<%@include file="./includes/header.jsp"%> 
 
<% 
	if(session.getAttribute("name") == null){ 
		response.sendRedirect("./login.jsp"); 
}
%>
<font face="Calibri"><br><a href="./createOE.jsp">Create a new observation event</a></font>	


<div class="container">
    <div class="c1of3">

       

    </div>

    <div class="c1of3">

    </div>

    <div class="c1of3">
        <form action="Search" name="Search" method="post">
             <input type="text" name="txt_search" size="50">
             <input type="button" value="Search" name="btn_search">
        </form>
    </div>
    <br style="clear: left; clear: right;" />
</div>
<br/>

<%@include file="./includes/footer.jsp"%>