<%-- 
    Document   : insufficientAmount
    Created on : Mar 11, 2019, 12:39:47 PM
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
        <h1>Oops!</h1>
        <h2>You dont have enough money in your account to purchase the 
            amount of cupcakes, please add more money to your account and 
            
        </h2>
        <form method="POST"  action="/cupcake/Controller">
            <input type="hidden" name="origin" value="Registration">
            <input type="submit" value="Try again">
    </body>
</html>
