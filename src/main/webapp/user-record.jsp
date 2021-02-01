<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.epam.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Record</title>
</head>
<body>
    <%
        if (request.getAttribute("user-record") != null) {
            User user = (User) request.getAttribute("user-record");
    %>

    <h1>User Record</h1>
    <div>
        ID:
        <%=user.getId()%></div>
    <div>
        First Name:
        <%=user.getFirstName()%></div>
    <div>
        Last Name:
        <%=user.getLastName()%></div>

    <%
        } else {
    %>
    <h1>No user record found.</h1>
    <%
        }
    %>
</body>
</html>