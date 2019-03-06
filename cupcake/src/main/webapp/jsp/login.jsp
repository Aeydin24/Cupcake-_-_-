<%-- 
    Document   : login
    Created on : Mar 6, 2019, 1:23:54 PM
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
        <h1>Log in here</h1>
         <form action="Controller?origin=Login" method="post">
            Username:<br>
            <input type="text" name="username" value="">
            <br>
            Password:<br>
            <input type="password" name="psw" value="">
            <br><br>
            <input type="submit" value="Log in">
        </form> 
    </body>
</html>
