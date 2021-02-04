<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.epam.model.User"%>
<%

            User user = (User) request.getAttribute("user-record");
    %>

<div class="container">
  <h2>Basic Table</h2>
  <p>The .table class adds basic styling (light padding and horizontal dividers) to a table:</p>
  <table class="table">
    <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><%=user.getFirstName()%></td>
        <td><%=user.getLastName()%></td>
        <td><%=user.getLogin()%></td>
      </tr>
    </tbody>
  </table>
</div>

</body>