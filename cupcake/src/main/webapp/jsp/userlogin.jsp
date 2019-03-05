<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<jsp:include page='/jsp/sitemenus.jsp'></jsp:include>

<h2>Login</h2>

<form action="Controller?command=userlogin" method="post">
    <input type="text" name="username" value="" placeholder="Username..." autofocus>
    <input type="password" name="password" value="" placeholder="Password...">
    <input type="submit" name="Login" value="Log in">
</form>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>