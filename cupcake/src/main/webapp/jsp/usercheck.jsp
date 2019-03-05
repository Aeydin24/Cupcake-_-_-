<%@page import="entity.User"%>
<%
    User userLoggedIn = (User) session.getAttribute("userloggedin");

    if(userLoggedIn == null)
    {
        request.setAttribute("errormessage", "User not logged in...");
        response.sendRedirect("index.jsp");
    }
%>