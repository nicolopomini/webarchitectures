<%-- 
    Document   : date
    Created on : Oct 10, 2018, 9:45:33 AM
    Author     : pomo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Date now = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        Current date: <%= dateFormat.format(now) %>
    </body>
</html>
