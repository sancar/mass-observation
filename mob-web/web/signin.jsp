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
        <form action="SignIn" method = "post" >
            <table>
                <tr>
                    <td align="center" colspan="2" > <a class="font_header">Registration Form</a></td>
                </tr>
                <tr>
                    <td align="center"><a class="font_normal">Name: </a></td>
                        <td><input type="text" name="name"  /> </td>
                </tr>

                <tr>
                    <td align="center"><a class="font_normal">Email: </a></td>
                        <td><input type="text" name="email"  /> </td>
                </tr>
                <tr>
                        <td align="center"><a class="font_normal">Choose a password: </a></td>
                        <td><input type="password" name="password1"  /> </td>
                </tr>

                <tr>
                        <td align="center"><a class="font_normal">Re-enter password: </a></td>
                        <td><input type="password" name="password2"  /> </td>
                </tr>

                <tr>
                        <td align="center"></td>
                        <td><input type="submit" name="Sign In" value= "Sign in"/> </td>
                </tr>
            </table>
        </form>
    </div>
    <br style="clear: left; clear: right;" />
</div>


<%@ include file = "./includes/footer.jsp" %>