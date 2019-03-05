<%@page import="java.util.List"%>
<%@page import="entity.User"%>

<jsp:include page='/jsp/admincheck.jsp'></jsp:include>

<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<jsp:include page='/jsp/sitemenus.jsp'></jsp:include>

<h2>Users List</h2>

<div id="tops">
    <%
        List<User> users = (List<User>) request.getAttribute("users");

        for (User user : users)
        {
            int id = user.getId();
            String username = user.getUserName();
            boolean admin = user.isAdmin();
            
            out.print("<div>");
            out.print("<p>Username: <a href=\"Controller?command=userdetails&id=" + id + "\">" + username + "</a></p>");
            out.print("<p>Admin: " + admin + "</p>");
            out.print("<br>");
            out.print("</div>");
        }
    %>
</div>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
