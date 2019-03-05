<%@page import="entity.User"%>

<%  
    User userLoggedIn = (User) session.getAttribute("userloggedin");
%>

<div id="sitemenus">

    <nav id="site">
        <a href=".">Home</a>
        <%
            if(userLoggedIn != null && userLoggedIn.isAdmin())
            {
                %>
                    <a href="Controller?command=userslist">Users</a>
                <%
            }
        %>
    </nav>

    <nav id="user">
        
        <%
            if(userLoggedIn == null)
            {
                %>
                    <a href="jsp/userregister.jsp">Register</a>
                    <a href="jsp/userlogin.jsp">Log in</a>
                <%
            }
            else
            {
                %>
                    <a href="jsp/userinfo.jsp"><%= userLoggedIn.getUserName()%></a>
                    <a href="Controller?command=userlogout">Log out</a>
                    <form action="Controller?command=userlogout" method="post">
                        <input type="submit" value="Log out" />
                    </form>
                <%
            }
        %>
        
    </nav>
    
</div>