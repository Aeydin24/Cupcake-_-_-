<%-- 
    Document   : errorpage
    Created on : Mar 7, 2019, 12:21:53 PM
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
        <h1>User not registered</h1>
        <form method="POST" action="/cupcake/Controller" >
             <input type="hidden" name="origin" value="Error">
            <input type="submit" value="Try Again">
        </form>
    </body>
</html>
