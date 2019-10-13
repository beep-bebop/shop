
<%@include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="Product.html">Return
		to Product</a>
</div>

<div id="Catalog">

	<table>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
				<b> </b>
			</td>
		</tr>
		<tr>
			<td>
				<b>
					<font size="4"> 
					</font>
				</b>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
        			Back ordered.
			</td>
		</tr>
		<tr>
			<td>
				${sessionScope.item.listPrice}
			</td>
		</tr>

		<tr>
			<td>
				<a class="Button" href="../cart/Cart.html">Add to Cart</a>
			</td>
		</tr>
	</table>

</div>

<%@include file="../common/IncludeBottom.jsp"%>>


