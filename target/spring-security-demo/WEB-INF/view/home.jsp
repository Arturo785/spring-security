<%--
  Created by IntelliJ IDEA.
  User: Arturo
  Date: 10/11/2021
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>
        This is just a demo page I just did
    </p>

    <hr>
        <p>
            <%--this is managed auto by springSecurity--%>
            User : <security:authentication property="principal.username" />
            <br/>
            <br/>
            Role : <security:authentication property="principal.authorities" />
        </p>

        <hr>

            <security:authorize access="hasRole('MANAGER')">
                <p>
                    <a href="${pageContext.request.contextPath}/leaders"> Leadership Meeting</a>
                    (only for managers)
                </p>
            </security:authorize>


            <security:authorize access="hasRole('ADMIN')">
                <p>
                    <a href="${pageContext.request.contextPath}/systems"> Leadership Meeting</a>
                    (only for admins)
                </p>
            </security:authorize>

        <hr>

    <form:form action="${pageContext.request.contextPath}/logout" method="post">


    <input type="submit" value="Logout" />
    </form:form>

</body>
</html>
