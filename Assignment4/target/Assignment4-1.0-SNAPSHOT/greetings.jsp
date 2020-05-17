<%-- 
    Document   : greetings
    Created on : Oct 10, 2018, 9:30:25 AM
    Author     : pomo
--%>

<%-- 
This page checks if the user is already logged and in case prints the name
Otherwise a link to the login page is given
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    boolean logged = false; 
    String username = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
        for(Cookie c: cookies) {
            if(c.getName().equals("ass4_cookie")) {
                logged = true;
                username = c.getValue();
            }
        }
    }
%>
<div class="greetings">
    <% if(logged) { %>
    Welcome <%= username%>! Click <a href="Logout">here</a> to log out.
    <% } else { %>
    Hello, click <a href="login.jsp">here</a> to log in.
    <% } %>
</div>