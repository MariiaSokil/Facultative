 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
<table id="example" class="display" style="width:100%">
    <thead>
        <tr>
            <th><fmt:message key="main_table_jsp.thead.title"/></th>
            <th><fmt:message key="main_table_jsp.thead.topic"/></th>
            <th><fmt:message key="main_table_jsp.thead.duration"/></th>
            <th><fmt:message key="main_table_jsp.thead.status"/></th>
            <th><fmt:message key="main_table_jsp.thead.start_date"/></th>
            <th><fmt:message key="main_table_jsp.thead.teacher"/></th>
            <th><fmt:message key="main_table_jsp.thead.enrollment"/></th>
            <th><fmt:message key="main_table_jsp.thead.price"/></th>
            <th><fmt:message key="main_table_jsp.thead.action"/></th>
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
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/main-table-init.js"></script>