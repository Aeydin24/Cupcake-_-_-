<%@page import="entity.User"%>

<jsp:include page='/jsp/admincheck.jsp'></jsp:include>

<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<jsp:include page='/jsp/sitemenus.jsp'></jsp:include>

<h2>User Details</h2>

<div id="tops">
    <%
        User user = (User) request.getAttribute("user");
        
        int id = user.getId();
        double balance = user.getBalance();
        boolean admin = user.isAdmin();

        out.print("<div>");
        out.print("<p>ID: " + id + "</p>");
        out.print("<p>Balance: " + balance + "</p>");
        out.print("<p>Admin: " + admin + "</p>");
        out.print("</div>");
    %>
</div>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
