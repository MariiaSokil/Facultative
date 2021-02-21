<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
  <a class="navbar-brand" href="/#">Faculty</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <c:if test="${sessionScope.user == null}">
          <li class="nav-item active">
            <a class="nav-link" href="/#">Home</a>
          </li>
      </c:if>
      <c:if test="${sessionScope.user != null}">
        <li class="nav-item">
            <a class="nav-link" href="/#">Home</a>
        </li>
      </c:if>
      <c:if test="${sessionScope.user != null && sessionScope.user.role == 'STUDENT'}">
          <li class="nav-item active">
            <a class="nav-link" href="/user">Student</a>
          </li>
      </c:if>
      <c:if test="${sessionScope.user != null && sessionScope.user.role == 'TEACHER'}">
          <li class="nav-item active">
                  <a class="nav-link" href="/user">Teacher</a>
          </li>
      </c:if>
      <c:if test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
          <li class="nav-item active">
            <a class="nav-link" href="/user">Administrator</a>
          </li>
      </c:if>
    </ul>
    <c:if test="${sessionScope.user == null}">
        <form class="form-inline my-2 my-lg-0" action="/login.jsp">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
        </form>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <form class="form-inline my-2 my-lg-0" action="/logout" method="post">
            <input type="hidden" id="hiddenUserId" value="<c:out value="${user.id}"></c:out>"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
        </form>
    </c:if>
  </div>
</nav>