<%@page import="entity.User"%>

<jsp:include page='/jsp/usercheck.jsp'></jsp:include>
<% User userLoggedIn = (User) session.getAttribute("userloggedin"); %>

<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<jsp:include page='/jsp/sitemenus.jsp'></jsp:include>

<h2>User Info</h2>

<p>Logged in as: <span id="UserName"><%= userLoggedIn.getUserName() %></span></p>
<p>Balance: <span id="UserBalance"><%= userLoggedIn.getBalance() %>$</span></p>
<p>Admin: <span id="UserBalance"><%= userLoggedIn.isAdmin() %></span></p>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
