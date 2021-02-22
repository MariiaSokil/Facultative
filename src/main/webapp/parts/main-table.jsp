 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
<table id="example" class="display" style="width:100%">
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
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.23/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/main-table-init.js"></script>