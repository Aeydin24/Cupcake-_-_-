<%-- 
    Document   : shop
    Created on : Mar 6, 2019, 1:32:22 PM
    Author     : ndupo
--%>

<%@page import="entity.Bottom"%>
<%@page import="entity.Top"%>
<%@page import="java.util.List"%>
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
            // Add balance box.
        %>
        
        <form method="POST" action="/cupcake/Controller" >
             <input type="hidden" name="origin" value="AddBalance">
                add amount: <input type="text" name="amount"/><br/>
            <input type="submit" value="add"/>
        </form>
        
        <%
            // Add dropdown menus for toppings and bottoms
            List<Top> toppings = dmc.getTops();
            List<Bottom> bottoms = dmc.getBottoms();
            
            // Add Dropdown menus:
        %>
        
        
        <form method="POST" action="/cupcake/Controller" >
             <input type="hidden" name="origin" value="AddProduct">
                <table class="table table-striped">
                    <thead><tr><th>Bottom</th><th>Topping</th><th>Quantity</th><th>Select</th><th></th></tr></thead>
                        <tbody>
                            <tr>
                                <td><select name="bottom" id="bottomSelect">
                                    <%  for (Bottom bot : bottoms) {
                                            out.print("<option value=\"" + bot.getName()
                                            + "\">" + bot.getName() + "</option>\n");
                                        }

                                            out.print("<select>\n");
                                            out.print("<td><select name=\"top\" id=\"topSelect\">\n");

                                        for (Top top : toppings) {
                                            out.print("<option value=\"" + top.getName()
                                            + "\">" + top.getName() + "</option>\n");
                                        }
                                    %>
                                    </select>
                                <td><input type="text" name="qty" placeholder="quantity" id="qtyInput"></td>
                                <td><input type="submit" name="submit" value="Add to cart"></td><td><span id="errorContainer"></span></td>
                            </tr>
                        </tbody>
                </table>
        </form>
    </body>
</html>
