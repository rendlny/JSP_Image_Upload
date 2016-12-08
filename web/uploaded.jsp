<%-- 
    Document   : uploaded
    Created on : 08-Dec-2016, 18:14:19
    Author     : Ren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DONE!</h1>
        <%
        String error = (String)session.getAttribute("error");

        if(error != null) {
        out.print("<div class='error_container'>");
            out.print("<p class='error_msg'>");
                out.print(error);
                out.print("</p>");
            out.print("</div>");

        session.setAttribute("error", null);
        }
        %>
    </body>
</html>
