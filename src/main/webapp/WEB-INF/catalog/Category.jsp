<%@include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="Main.html">Return to Main Menu</a>
</div>

<div id="Catalog">

	<h2>category name</h2>

	<table>
		<tr>
			<th>Product ID</th>
			<th>Name</th>
		</tr>
		<!-- 循环 -->
		<c:forEach var="product" items="${sessionScope.productList}">
		<tr>
			<td><a href="Product.jsp">${product.productId}</a>
			</td>
			<td>${product.name}</td>
		</tr>
		</c:forEach>
	</table>

</div>

<%@include file="../common/IncludeBottom.jsp"%>>
