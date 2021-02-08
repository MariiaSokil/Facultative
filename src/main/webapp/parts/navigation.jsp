<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Faculty</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="/#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/student">Student</a>
      </li>
       <li class="nav-item">
              <a class="nav-link" href="/teacher">Teacher</a>
            </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="/admin">Administrator</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <button class="btn btn-outline-success my-2 my-sm-0" type="button"  data-toggle="modal" data-target="#exampleModalCenter">Login</button>
    </form>
  </div>
</nav>

<!-- Modal -->
<form class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" action="/login">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">Login form</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">x</span>
        </button>
      </div>
      <div class="modal-body">
       <div class="form-group">
           <label for="exampleInputEmail1">Email address</label>
           <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="uname">
           <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
         </div>
         <div class="form-group">
           <label for="exampleInputPassword1">Password</label>
           <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="psw">
         </div>
         <div class="form-check">
           <input type="checkbox" class="form-check-input" id="exampleCheck1">
           <label class="form-check-label" for="exampleCheck1">Check me out</label>
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary">Register</button>
        <button type="submit" class="btn btn-primary">Login</button>
      </div>
    </div>
  </div>
</form>