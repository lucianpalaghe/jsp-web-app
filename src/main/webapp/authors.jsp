<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- comment --%>
<%@ page isELIgnored="false" %>

<html>
<body>

<c:choose>
    <c:when test="${authors != null}">
    <table border="1">
            <tr><th>Nume</th><th>Email</th><th>Birth date</th><th>Remove</th></tr>
            <c:forEach items="${authors}" var="a">
                <tr>
                    <td>${a.name}</td>
                    <td>${a.email}</td>
                    <fmt:parseDate value="${a.birthDate}" pattern="yyyy-MM-dd"  var="parsedDate" type="date" />
                    <td align="right"><fmt:formatDate type="date" value="${parsedDate}"/></td>
                    <form method="post" action="authors">
                    <input type="hidden" name="param" value="${a.id}"> <%-- this will pass the id of author we want to delete to the doPost method --%>
                    <td align="right"><input type="submit" value="Remove"/></td> <%-- this button will call submit the form, calling doPost method of the controller --%>
                    <form>
                </tr>
            </c:forEach>
     </table>
    </c:when>
    <c:otherwise>
        <a href="<%= request.getAttribute("javax.servlet.forward.request_uri") %>">No authors found, click here to try again!</a>
    </c:otherwise>

</c:choose>
</body>
</html>
