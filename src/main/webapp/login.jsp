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
        <header>
            <jsp:include page="parts/navigation.jsp"></jsp:include>
        </header>
        <content>
            <div class="container">
                <form action="/login" method="post">
                  <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" placeholder="Enter email" id="email" name="uname">
                  </div>
                  <div class="form-group">
                    <label for="pwd">Password:</label>
                    <input type="password" class="form-control" placeholder="Enter password" id="pwd" name="psw">
                  </div>
                  <div class="form-group form-check">
                    <label class="form-check-label">
                      <input class="form-check-input" type="checkbox"> Remember me
                    </label>
                  </div>
                  <c:if test = "${message ne null}">
                     <p style="color: red">${message}<br>
                  </c:if>
                  <button type="submit" class="btn btn-primary">Submit</button>
                  <button id="register_btn" type="button" class="btn btn-primary">Register</button>
                </form>
            </div>
        </content>
        <footer class="mt-auto text-right">
            <%@ include file="parts/footer.jsp" %>
        </footer>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script>
            $('#register_btn').on('click', function () {
                          $('#exampleModal').modal('show');
            });
        </script>
    </body>


<!-- Registration form for a new use -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">User details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>


      <div class="modal-body">
        <form action="/user" method="POST">
           <div class="form-group">
                    <div class="input-group mb-3">
                      <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">First name:</span>
                      </div>
                      <input type="text" class="form-control" id="firstName" name="firstName" aria-describedby="basic-addon3">
                    </div>

          </div>
          <div class="form-group">
            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text" id="basic-addon3">Last name:</span>
              </div>
              <input type="text" class="form-control" id="lastName" name="lastName" aria-describedby="basic-addon3">
            </div>
          </div>

           <div class="form-group">
                 <div class="input-group mb-3">
                   <div class="input-group-prepend">
                     <span class="input-group-text" id="basic-addon3">Login(email):</span>
                   </div>
                   <input type="email" class="form-control" id="email" name="email" aria-describedby="basic-addon3">
                 </div>
           </div>
                <div class="form-group">
                  <div class="input-group mb-3">
                    <div class="input-group-prepend">
                      <span class="input-group-text" id="basic-addon3">Password:</span>
                    </div>
                    <input type="password" class="form-control" id="password" name="password" aria-describedby="basic-addon3">
                  </div>
                </div>
                <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Submit</button>
                </div>
        </form>
      </div>
    </div>
  </div>
</div>
</html>