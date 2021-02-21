<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        body {
          min-height: 100vh;
          position: relative;
          margin: 0;
          padding-bottom: 50px; //height of the footer
          box-sizing: border-box;
        }

        content{
            display: center;
        }

        footer {
          position: absolute;
          bottom: 0;
          height: 50px;
        }
    </style>
    <body>
          <%
                //allow access only if session exists
                if(session.getAttribute("user") == null){
                	response.sendRedirect("login.jsp");
                }
          %>
        <header>
            <jsp:include page="parts/navigation.jsp"></jsp:include>
        </header>
        <content>
            <div class="container">
                <h2>User details</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Email</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:if test="${requestScope.user ne null }">
                                <td><c:out value="${user.firstName}"></c:out></td>
                                <td><c:out value="${user.lastName}"></c:out></td>
                                <td><c:out value="${user.login}"></c:out></td>
                                <td><c:out value="${user.role}"></c:out></td>
                            </c:if>
                        </tr>
                    </tbody>
                </table>
                <div class="d-grid gap-2 d-md-flex justify-content-between">
                    <h2>Courses list</h2>
                       <form class="form-inline">
                          <button class="btn btn-outline-success" type="submit">Add new course</button>
                       </form>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Topic</th>
                            <th>Duration</th>
                            <th>Status</th>
                            <th>Teacher</th>
                            <th>Students</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.courses}" var="course">
                            <tr>
                                <td><c:out value="${course.title}"></c:out></td>
                                <td><c:out value="${course.category.name}"></c:out></td>
                                <td><c:out value="${course.duration}"></c:out></td>
                                <td><c:out value="${course.status}"></c:out></td>
                                <td><c:out value="${course.teacher.firstName} ${course.teacher.lastName}"></c:out></td>
                                <td><c:out value="${course.students.size()}"></c:out></td>
                                <td>

                                         <div class="d-grid gap-2 d-md-block">
                                              <form class="form-inline" action="/courses2" method="post">
                                                <input type="hidden" name="id" value="<c:out value="${course.id}"></c:out>" />
                                                <input type="hidden" name="title" value="<c:out value="${course.title}"></c:out>" />
                                                <input type="hidden" name="category_id" value="<c:out value="${course.category.id}"></c:out>" />
                                                <input type="hidden" name="category_name" value="<c:out value="${course.category.name}"></c:out>" />
                                                <input type="hidden" name="duration" value="<c:out value="${course.duration}"></c:out>" />
                                                <input type="hidden" name="enrollment" value="<c:out value="${course.enrollment}"></c:out>" />
                                                <input type="hidden" name="price" value="<c:out value="${course.price}"></c:out>" />
                                                <input type="hidden" name="status" value="<c:out value="${course.status}"></c:out>" />
                                                <input type="hidden" name="teacher_id" value="<c:out value="${course.teacher.id}"></c:out>" />
                                                <input type="hidden" name="start_date" value="<c:out value="${course.startDate}"></c:out>" />
                                                 <c:if test="${course.students ne null}">
                                                 <c:forEach items="${course.students}" var="student">
                                                         <c:set var="myVar" value="${stat.first ? '' : myVar} ${currentItem}" />

                                                 </c:forEach>
                                                 <input type="hidden" name="students" value="<c:out value="${student.id}"></c:out>" />


                                                 </c:if>

                                                <button class="btn btn-outline-warning" type="submit">Edit</button>
                                              </form>
                                              <form class="form-inline" action="/courses2">
                                                 <input type="hidden" name="id" value="<c:out value="${course.id}"></c:out>" />
                                                <button class="btn btn-outline-danger" type="submit">Remove</button>
                                              </form>
                                         </div>

                                 </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </content>
        <footer class="mt-auto text-right">
            <%@ include file="parts/footer.jsp" %>
        </footer>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script>
            $('.nav-item').click(function() {
                $('.nav-item').removeClass('active');
                $(this).addClass('active');
            })
        </script>
    </body>o return the deleted row(s) to the client, you use the RETURNING clause as follows
</html>
