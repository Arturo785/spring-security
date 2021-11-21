<%--
  Created by IntelliJ IDEA.
  User: Arturo
  Date: 13/11/2021
  Time: 6:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login form custom</title>

    <style>

        .failed {
            color: red;
        }

    </style>
</head>

<body>

    <h3>My custom login page</h3>
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">

        <c:if test="${param.error != null}">
            <i class="failed"> Sorry, the credentials are invalid</i>
        </c:if>

        <p>
            User name: <input type="text" name="username" />
        </p>

        <p>
            Password: <input type="password" name="password" />
        </p>

        <input type="submit" value="Login">

    </form:form>

</body>

</html>
