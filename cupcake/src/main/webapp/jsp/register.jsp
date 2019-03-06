<%-- 
    Document   : register
    Created on : Mar 6, 2019, 1:23:43 PM
    Author     : ndupo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Register here</h1>
        <form method="POST"  action="/cupcake/Controller?origin=Login">
            <input type="hidden" name="origin" value="Registration">
        Username:<br>
        <input type="text" name="username" value="">
        <br>
        Password:<br>
        <input type="text" name="password" value="">
        <br>
        Email:<br>
        <input type="text" name="email" value="">
        <br><br>
        <input type="submit" value="Create User">
</form> 
    </body>
</html>
