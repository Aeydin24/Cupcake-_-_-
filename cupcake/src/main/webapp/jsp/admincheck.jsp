<%@page import="entity.User"%>
<%
    User userLoggedIn = (User) session.getAttribute("userloggedin");

    if(userLoggedIn == null || !userLoggedIn.isAdmin())
    {
        request.setAttribute("errormessage", "Admin not logged in...");
        response.sendRedirect("index.jsp");
    }
%>