<%-- 
    Document   : register
    Created on : Mar 6, 2019, 1:23:43 PM
    Author     : ndupo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style></style>   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register here</h1>
        <form method="POST"  action="/cupcake/Controller?origin=Registration">
        Username:<br>
        <input type="text" name="username" value="">
        <br>
        Password:<br>
        <input type="password" name="password" value="">
        <br>
        Email:<br>
        <input type="email" name="email" value="">
        <br><br>
        <input type="submit" value="Create User">
</form> 
    </body>
</html>
