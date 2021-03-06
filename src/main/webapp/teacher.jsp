<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <h2><fmt:message key="jsp_common.table.container.user_details"/></h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th><fmt:message key="jsp_common.table.user_details.first_name"/></th>
                            <th><fmt:message key="jsp_common.table.user_details.last_name"/></th>
                            <th><fmt:message key="jsp_common.table.user_details.email"/></th>
                            <th><fmt:message key="jsp_common.table.user_details.role"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:if test="${sessionScope.user ne null }">
                                <td><c:out value="${user.firstName}"></c:out></td>
                                <td><c:out value="${user.lastName}"></c:out></td>
                                <td><c:out value="${user.login}"></c:out></td>
                                <td><c:out value="${user.role}"></c:out></td>
                            </c:if>
                        </tr>
                    </tbody>
                </table>
                <h2><fmt:message key="jsp_common.table.container.course_list"/></h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                                 <th><fmt:message key="jsp_common.table.courses_list.title"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.topic"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.duration"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.status"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.start_date"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.enrollment"/></th>
                                 <th><fmt:message key="jsp_common.table.courses_list.action"/></th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.courses}" var="course">
                            <tr>
                                <td><c:out value="${course.title}"></c:out></td>
                                <td><c:out value="${course.category.name}"></c:out></td>
                                <td><c:out value="${course.duration}"></c:out></td>
                                <td><c:out value="${course.status}"></c:out></td>
                                 <td><c:out value="${course.startDate}"></c:out></td>
                                <td><c:out value="${course.enrollment}"></c:out></td>
                                <td><form class="form-inline"><button disabled class="btn btn-outline-success" type="submit">Evaluate</button></form></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </content>
        <footer class="mt-auto text-right">
            <%@ include file="parts/footer.jsp" %>
        </footer>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script>
            $('.nav-item').click(function() {
                $('.nav-item').removeClass('active');
                $(this).addClass('active');
            })
        </script>
    </body>
</html>
