<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<script>
	$(document).ready(function(){
        var searchCondition = '${searchCondition}';

        $('.table').DataTable({
            "lengthMenu": [[3,5,10,-1], [3,5,10, "All"]],
            "oSearch": {"sSearch": searchCondition}
        });
    });

</script>

	<div class="container-wrapper">
		<div class="container">

			<div class="page-header">
				<h1>All Products</h1>
				<p class="lead">Checkout all the awesome products available now!
				</p>
			</div>

			<table class="table table-striped table-hover">
				<thead>
					<tr class="bg-success">
						<th>Photo Thumb</th>
						<th>Product Name</th>
						<th>Category</th>
						<th>Condition</th>
						<th>price</th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach var="product" items="${products}">
					<tr>
						<td style="width:250px; height:150px;text-align:center; vertical-align:middle" ><img
						src="<c:url value="/resources/images/${product.productId}.png"/>"
						alt="image" style="max-height:100%; max-width:100%"/></td>
						<td>${product.productName }</td>
						<td>${product.productCategory }</td>
						<td>${product.productCondition }</td>
						<td>${product.productPrice }</td>
						<td><a href="<spring:url value='/product/productList/viewProduct/${product.productId}'/>"><span class="glyphicon glyphicon-info-sign"></span></a></td>
					</tr>
				</c:forEach>

			</table>
<%@ include file="/WEB-INF/template/footer.jsp" %>