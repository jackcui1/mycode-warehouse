<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script>
	$(document).ready(function(){
        $('.table').DataTable({
            "lengthMenu": [[1,2,3,5,10,-1], [1,2,3,5,10, "All"]],
        });
    });
</script>

<div class="container-wrapper">
	<div class="container">

		<div class="page-header">
			<h1>Products Inventory Page</h1>
			<p class="lead">This is the product inventory page!</p>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Photo Thumb</th>
					<th>Product Name</th>
					<th>Category</th>
					<th>Condition</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach var="product" items="${productList}">
				<tr>
					<td style="width:250px; height:170px;text-align:center; vertical-align:middle" ><img
						src="<c:url value="/resources/images/${product.productId}.png"/>"
						alt="image" style="max-height:100%; max-width:100%"/></td>
					<td>${product.productName }</td>
					<td>${product.productCategory }</td>
					<td>${product.productCondition }</td>
					<td>${product.productPrice }</td>
					<td><a
						href="<spring:url value='/product/productList/viewProduct/${product.productId}'/>"><span
							class="glyphicon glyphicon-info-sign"></span></a> <a
						href="<spring:url value='/admin/product/deleteProduct/${product.productId}'/>"><span
							class="glyphicon glyphicon-remove"></span></a> <a
						href="<spring:url value='/admin/product/editProduct/${product.productId}'/>"><span
							class="glyphicon glyphicon-pencil"></span></a></td>
				</tr>
			</c:forEach>

		</table>
		<a href="<spring:url value="/admin/product/addProduct"/>"
			class="btn btn-primary">Add Product</a>
		<%@ include file="/WEB-INF/template/footer.jsp"%>