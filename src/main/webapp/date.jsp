<html>
<body>
<% java.util.Date date = new java.util.Date(); %>
Hello! The time is
<%
out.println(String.valueOf(date));
out.println( "<br>Your machine's address is " );
out.println( request.getRemoteHost());
%>
</body>
</html>