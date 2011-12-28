<%@ include file = "./includes/header.jsp" %>


<% 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		%>
	<a style="color:red;"> <% out.print(message); %></a>
		<% 
	}
%>

<div class="container">
    <div class="c1of2">
        <%@ include file = "./includes/what.jsp" %>
    </div>
    <div class="c1of2">
        <form action="Login" method = "post" >
            <table>
                <tr>
                        <td colspan="2" ><a class="font_header">Login</a></td>
                </tr>
                <tr>
                        <td><a class="font_normal">E-mail: </a></td>
                        <td><input type="text" name="email"  /> </td>
                </tr>
                <tr>
                        <td colspan="2"></td> 
                </tr>
                <tr>
                        <td><a class="font_normal">Password: </a></td>
                        <td><input type="password" name="password1"  /></td>
                </tr>
                <tr>
                        <td></td>
                        <td><input type="submit" name="Login" value= "Login"/></td>
                </tr>
                <tr>
                    <td><a class="font_normal" href="./signin.jsp">New Here?</a></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>
    <div class="c1of2">
        <img style="margin-left: 90px;  margin-right: 90px;" src="pics/observe.GIF" height="430"></img>
    </div>
    <br style="clear: left; clear: right;" />
</div>


<%@ include file = "./includes/footer.jsp" %>