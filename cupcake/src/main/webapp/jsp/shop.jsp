<%-- 
    Document   : shop
    Created on : Mar 6, 2019, 1:32:22 PM
    Author     : ndupo
--%>

<%@page import="mapper.DataMapperUsers"%>
<%@page import="mapper.DataMapperCupcake"%>
<%@page import="entity.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to the cupcake shop</h1>
        
        <%  //Get user from Database.
            Users user = (Users) session.getAttribute("user");
            
            //Instance of relevant DataMapper.
            DataMapperCupcake dmc = new DataMapperCupcake();
            DataMapperUsers dmu = new DataMapperUsers();
            
            //Show the logged in user.
            out.println("<h2> Hi! " + user.getUserName() + " </h2>");
            
            //Show user balance.
            out.println("<p style=\"font-size:16px\"> " + " Balance  " + user.getBalance() + "</p>");

        %>
        

    </body>
</html>
