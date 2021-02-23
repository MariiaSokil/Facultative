<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
    <%@ include file="parts/meta.jsp" %>
    <%@ include file="parts/header.jsp" %>
    <link href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                    <h2><fmt:message key="jsp_common.table.container.course_list"/></h2>
                       <form class="form-inline">
                          <button class="btn btn-outline-success" type="submit"><fmt:message key="admin_jsp.button.add_new_course"/></button>
                       </form>
                </div>
                <div class="container">
                <table id="adminCourses" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th><fmt:message key="jsp_common.table.courses_list.title"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.topic"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.duration"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.status"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.start_date"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.teacher"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.enrollment"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.price"/></th>
                            <th><fmt:message key="jsp_common.table.courses_list.action"/></th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                             <th><fmt:message key="jsp_common.table.courses_list.title"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.topic"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.duration"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.status"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.start_date"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.teacher"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.enrollment"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.price"/></th>
                             <th><fmt:message key="jsp_common.table.courses_list.action"/></th>
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
        <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.js" type="text/javascript"></script>
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
                                         return   '<div class="d-grid gap-2 d-md-flex justify-content-between"><button type="button" class="btn btn-outline-warning" id="editCourseBtn">Edit</button>' +
                                         '<button class="btn btn-outline-danger" id="removeCourseBtn">Remove</button></div>';
                                 }
                             }
                        ]
                });

                $('#adminCourses tbody').on( 'click', '#removeCourseBtn', function () {
                    var row = myTable.row($(this).parents('tr')).data();
                    $.ajax({
                        url: "/courses2?id=" + row.id + "&enrollment=" + row.enrollment,
                        type: "get"
                    }).always(function() {
                        myTable.ajax.reload();
                    });
                } );
                $('#adminCourses tbody').on( 'click', '#editCourseBtn', function () {
                          var row = myTable.row($(this).parents('tr')).data();
                          var day = ("0" + row.startDate.dayOfMonth).slice(-2);
                          var month = ("0" + row.startDate.monthValue).slice(-2);
                          var today = row.startDate.year + "-" + month + "-" + day;

                           $.ajax({
                              url: "/teachers"
                          }).always(function(teachers) {
                            $.each(teachers, function( index, teacher ) {
                              var o = new Option(teacher.firstName + ' ' + teacher.lastName, teacher.id);
                              /// jquerify the DOM object 'o' so we can use the html method
                              $(o).html(teacher.firstName + ' ' + teacher.lastName);
                              $("#teacher").append(o);
                            });

                            $('#course-id').val(row.id);
                            $('#title').val(row.title);
                            $('#topic').val(row.category.name);
                            $('#topic-id').val(row.category.id);
                            $('#duration').val(row.duration);
                            $('#start').val(today);
                            $("#status").val(row.status).change();
                            $('#teacher').val(row.teacher.id).change();
                            $('#price').val(row.price);
                            $('#enrollment').val(row.enrollment);
                            $('#exampleModal').modal('show');
                          });


               });
        </script>
    </body>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Course details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="/courses2" method="POST">
           <div class="form-group">
                    <div class="input-group mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">Course Id:</span>
                      </div>
                      <input  readonly="readonly" type="number" class="form-control" id="course-id" name="id" aria-describedby="basic-addon3">
                    </div>

          </div>
          <div class="form-group">
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon3">Title:</span>
              </div>
              <input type="text" class="form-control" id="title" name="title" aria-describedby="basic-addon3">
            </div>
          </div>
          <div class="form-group">
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon3">Topic:</span>
              </div>
              <input  readonly="readonly" type="text" class="form-control" id="topic" name="category_name" aria-describedby="basic-addon3">
              <input  hidden="true" type="text" class="form-control" id="topic-id" name="category_id">
            </div>
          </div>
           <div class="form-group">
                 <div class="input-group mb-3">
                   <div class="input-group-prepend">
                     <span class="input-group-text" id="basic-addon3">Duration:</span>
                   </div>
                   <input type="number" class="form-control" id="duration" name="duration" aria-describedby="basic-addon3">
                 </div>
           </div>
            <div class="form-group">
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon3">Status:</span>
                  </div>
                  <select class="custom-select" id="status" name="status">
                      <option selected value="COMING_SOON">COMING_SOON</option>
                      <option value="ONGOING">ONGOING</option>
                      <option value="COMPLETED">COMPLETED</option>
                    </select>
                </div>
           </div>
            <div class="form-group">
               <div class="input-group mb-3">
                 <div class="input-group-prepend">
                   <span class="input-group-text" id="basic-addon3">Start Date</span>
                 </div>
                 <input type="date" class="form-control" id="start" name="start_date" aria-describedby="basic-addon3">
               </div>
            </div>
             <div class="form-group">
               <div class="input-group mb-3">
                 <div class="input-group-prepend">
                   <span class="input-group-text" id="basic-addon3">Teacher:</span>
                 </div>
                  <select class="custom-select" id="teacher" name="teacher_id">
                  </select>
               </div>
             </div>
               <div class="form-group">
                <div class="input-group mb-3">
                  <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon3">Enrollment</span>
                  </div>
                  <input  readonly="readonly" type="text" class="form-control" id="enrollment" name="enrollment" aria-describedby="basic-addon3">
                </div>
              </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon3">Price:</span>
                    </div>
                    <input type="text" class="form-control" id="price" name="price" aria-describedby="basic-addon3">
                  </div>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                </div>
        </form>
      </div>
    </div>
  </div>
</div>
</html>
