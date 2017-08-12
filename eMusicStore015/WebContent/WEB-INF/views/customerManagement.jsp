<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

	<div class="container-wrapper">
		<div class="container">

			<div class="page-header">
				<h1>Customer Management page</h1>
				<p class="lead">This is the customer management page.
				</p>
			</div>

			<table class="table table-striped table-hover">
				<thead>
					<tr class="bg-success">
						<th>Name</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Username</th>
						<th>Enable</th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach var="customer" items="${customerList}">
					<tr>
						<td>${customer.customerName}</td>
						<td>${customer.customerEmail}</td>
						<td>${customer.customerPhone}</td>
						<td>${customer.userName}</td>
						<td>${customer.enabled}</td>
						<td><a href="<spring:url value='/admin/customer/delete/${customer.customerId}'/>"><span
							class="glyphicon glyphicon-remove"></span></a> <a
						href="<spring:url value='/admin/customer/edit/${customer.customerId}'/>"><span
							class="glyphicon glyphicon-pencil"></span></a></td>
					</tr>
				</c:forEach>

			</table>
<%@ include file="/WEB-INF/template/footer.jsp" %>