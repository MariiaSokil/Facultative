<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="parts/meta.jsp" %>
<%@ include file="parts/header.jsp" %>
<link href="https://cdn.datatables.net/1.10.23/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
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
        <div class="container h-100">
            <div class="pages row h-100 justify-content-center align-items-center">
                <jsp:include page="parts/main-table.jsp"></jsp:include>
            </div>
        </div>
    </content>
    <footer class="mt-auto text-right">
        <%@ include file="parts/footer.jsp" %>
    </footer>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>