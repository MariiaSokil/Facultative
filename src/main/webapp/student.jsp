
    <%@ page import="com.epam.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/meta.jsp" %>
<%@ include file="parts/header.jsp" %>
</head>
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
               <td>DIFwewejgp</td>
               <td>3wfwf3%></td>
               <td>3wfrwff></td>
             </tr>
           </tbody>
         </table>
       </div>
    </content>
    <footer class="mt-auto text-right">
        <%@ include file="parts/footer.jsp" %>
    </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>

</body>