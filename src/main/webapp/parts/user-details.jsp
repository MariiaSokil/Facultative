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