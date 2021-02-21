<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
    <link href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
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
                <div class="container">
                <table id="adminCourses" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Topic</th>
                            <th>Duration</th>
                            <th>Status</th>
                            <th>Start Date</th>
                            <th>Teacher</th>
                            <th>Enrollment</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                             <th>Title</th>
                             <th>Topic</th>
                             <th>Duration</th>
                             <th>Status</th>
                             <th>Start Date</th>
                             <th>Teacher</th>
                             <th>Enrollment</th>
                             <th>Price</th>
                             <th>Action</th>
                        </tr>
                    </tfoot>
                </table>
                </div>
            </div>
        </content>
        <footer class="mt-auto text-right">
            <%@ include file="parts/footer.jsp" %>
        </footer>

        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
        <script>
            $('.nav-item').click(function() {
                $('.nav-item').removeClass('active');
                $(this).addClass('active');
            });
            var myTable = $('#adminCourses').DataTable({
                        ajax: {
                                url: '/courses',
                                dataSrc: ''
                            },
                        "order": [],
                        "columns": [
                            { "data": "title" },
                            { "data": "category",
                              render: function ( data, type, row ) {
                                                         return data.name; }
                            },
                            { "data": "duration" },
                            { "data": "status" },
                            { "data": "startDate",
                               render: function ( data, type, row ) {
                                   var date = new Date(data.year, data.monthValue -1, data.dayOfMonth);
                                   return new Date(data.year, data.monthValue -1, data.dayOfMonth).toLocaleDateString();
                               }
                            },
                            { "data": "teacher",
                               render: function ( data, type, row ) {
                                                          return data.firstName +' '+ data.lastName;
                                                  }
                            },


                            { "data": "enrollment"},
                             { "data": "price"},
                             { "data": "",
                                 render: function ( data, type, row ) {
                                         return '<button class="btn btn-outline-warning" id="removeCourseBtn" value="'+ row.id+'">Remove</button>';
                                 }
                             }
                        ]
                });

                $('#adminCourses tbody').on( 'click', '#removeCourseBtn', function () {
                    $.ajax({
                        url: "/courses2?id=" + this.value,
                        type: "get"
                    }).always(function() {
                        myTable.ajax.reload();
                    });
                } );
        </script>
    </body>
</html>
