<%@ include file="../common/IncludeTop.jsp"%>
<style type="text/css">
	table{
		border-collapse: collapse;
		width:60%;
		border:1px solid #c6c6c6 !important;
		margin-bottom:20px;
	}
	table th{
		border-collapse: collapse;
		border-right:1px solid #c6c6c6 !important;
		border-bottom:1px solid #c6c6c6 !important;
		background-color:#ddeeff !important;
		padding:5px 9px;
		font-size:15px;
		font-weight:normal;
		text-align:center;
	}
	table td{
		border-collapse: collapse;
		border-right:1px solid #c6c6c6 !important;
		border-bottom:1px solid #c6c6c6 !important;
		padding:5px 9px;
		font-size:15px;
		font-weight:normal;
		text-align:center;
		word-break: break-all;
	}
	table tr:nth-child(odd){
		background-color:#fff !important;
	}
	table tr:nth-child(even){
		background-color: #f8f8f8 !important;
	}
</style>
<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

<h2 align="center">${sessionScope.category.name}</h2>
<table>
	<tr>
		<th>Product ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="product" items="${sessionScope.productList}">
		<tr>
			<td>
			     <a href="viewProduct?productId=${product.getProductId()}">${product.getProductId()}</a>
			</td>
			<td>
				${product.getName()}
			</td>
		</tr>
	</c:forEach>
</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>


