<html>
<body>
<% String[] authors = request.getParameterValues("author");
if (authors != null) {
%>
    <strong>Authors</strong><br>
    <% for (int i = 0; i < authors.length; ++i) { %>
        <%= authors[i] %><br>
    <% } %>
<% } %>
<a href="<%= request.getRequestURI() %>">Once more!</a>
</body>
</html>
