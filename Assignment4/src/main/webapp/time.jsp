<%-- 
    Document   : time
    Created on : Oct 10, 2018, 9:49:37 AM
    Author     : pomo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Date now = new Date();
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Date Page - Assignment 4</title>
    </head>
    <body>
        <jsp:include page="greetings.jsp"/>
        <br>
        Current time: <%= dateFormat.format(now) %>
    </body>
</html>
