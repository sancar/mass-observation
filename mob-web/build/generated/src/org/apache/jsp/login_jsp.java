package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(2);
    _jspx_dependants.add("/./includes/header.jsp");
    _jspx_dependants.add("/./includes/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>MOB</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<a href=\"./welcome.jsp\"><font face=\"Calibri\"><font color=\"#68caf9\"></font></font>\r\n");
      out.write("<font face=\"Calibri\"><font color=\"#68caf9\" size=\"240\"> mass</font><font color=\"#000080\" size=\"240\">observation</font></font></a>\r\n");
      out.write("<hr width=\"100%\" size=\"2\">");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
	
	String message = (String)request.getAttribute("message");
	if(!(message == null)){
		
      out.write("\r\n");
      out.write("\t<a style=\"color:red;\"> ");
 out.print(message); 
      out.write("</a>\r\n");
      out.write("\t\t");
 
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("<tr>\r\n");
      out.write("<td>\r\n");
      out.write("<form action=\"Login\" method = \"POST\" >\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("div.one\r\n");
      out.write("{\r\n");
      out.write("width: 300px; height: 200px;\r\n");
      out.write("border-style: solid;\r\n");
      out.write("border-color: #68caf9\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<div class=\"one\">\r\n");
      out.write("<table>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td colspan=\"2\" ><font face=\"Calibri\"><font color=\"#000080\" size=\"5\"><b>Login</b></font><br></font></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td><font face=\"Calibri\"><font color=\"#000080\" size=\"3.5\"><b>E-mail: </b></font></font></td>\r\n");
      out.write("\t\t<td><input type=\"text\" name=\"email\"  /> </td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td colspan=\"2\"></td> \r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td><font face=\"Calibri\"><font color=\"#000080\" size=\"3.5\"><b>Password: </b></font></font></td>\r\n");
      out.write("\t\t<td><input type=\"password\" name=\"password1\"  /></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td></td>\r\n");
      out.write("\t\t<td><input type=\"submit\" name=\"Login\" value= \"Login\"/></td>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\r\n");
      out.write("</table>\r\n");
      out.write("</div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<font face=\"Calibri\"><font color=\"#000080\" size=\"4\"><b>New here?</b></font><br></font>\r\n");
      out.write("<a href=\"./signin.jsp\">Sign in as a new user</a>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("p.two\r\n");
      out.write("{\r\n");
      out.write("width: 300px; height: 200px;\r\n");
      out.write("border-style: solid;\r\n");
      out.write("border-color: #68caf9\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<p class=\"two\">\r\n");
      out.write("</td>\r\n");
      out.write("<td>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("p.three\r\n");
      out.write("{\r\n");
      out.write("width: 930px; height: 480px;\r\n");
      out.write("border-style: solid;\r\n");
      out.write("border-color: #68caf9\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<p class=\"three\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
