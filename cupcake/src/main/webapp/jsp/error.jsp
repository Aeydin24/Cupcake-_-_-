<jsp:include page='/jsp/usercheck.jsp'></jsp:include>

<jsp:include page='/jsp/siteheader.jsp'></jsp:include>

<jsp:include page='/jsp/sitemenus.jsp'></jsp:include>

<%
    String errormessage = "Error occurred...";
    if(request.getAttribute("errormessage") != null)
    {
        errormessage = (String) request.getAttribute("errormessage");
    }
%>

<h2>Error</h2>

<p>Error message: <%= errormessage %></p>

<jsp:include page='/jsp/sitefooter.jsp'></jsp:include>
