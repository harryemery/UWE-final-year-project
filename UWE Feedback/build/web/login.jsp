<%-- 
    Document   : login
    Created on : 06-Nov-2017, 11:27:06
    Author     : Colin Berry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/index.css" rel="stylesheet"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/centered.css" />
<!DOCTYPE html>


<!-- Testing --> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <c:if test="${not empty failed}">
            <p>Invalid Login Credentials</p>
        </c:if>
        <h1>Login</h1>
        <form method="post" action="Login">
            <input type="text" name="username" placeholder="Username" /> <br>
            <input type="password" name="password" placeholder="Password"/> <br>
            <p>
            <input name="button" type="submit" value="Login" />      
            </p>
        </form>

    </body>
</html>
