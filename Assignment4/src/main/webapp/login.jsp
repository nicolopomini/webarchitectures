<%-- 
    Document   : login
    Created on : Oct 10, 2018, 9:51:47 AM
    Author     : pomo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Assignment 4</title>
    </head>
    <body>
        Log in <br/>
        <form action="LoginServlet" method="POST">
            Name:<br/>
            <input type="text" name="username" required><br/>
            Password:<br/>
            <input type="password" name="password" required><br/>
            <input type="submit" value="Login">
        </form>
    </body>
</html>
