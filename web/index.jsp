<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Image Upload</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Image Upload</h1>
            <hr/>
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
        
        <form method="post" enctype="multipart/form-data" action="Controller">
            <input type="hidden" name="action" value="upload_image"/>
            
            <label for="imageName">Name:</label><br/>
            <input name="imageName" type="text" required/><br/><br/>
            
            <label for="uploadImage">Upload Image:</label><br/>
            <input name="uploadImage" type="file" size="50" required/><br/><br/>
            
            <input type="submit" name="submit" value="Upload"/>
            <input type="reset" name="reset" value="Clear"/>
        </form>
    </body>
</html>
