<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="users" value="${requestScope.users}" />
<c:if test="${!(empty users)}">
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userName}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
</c:if>
