<%@ include file="../common/IncludeTop.jsp"%>
<style type="text/css">
	table thead th {
		background-color: rgb(156, 186, 95);
		color: #fff;
		border-bottom-width: 0;
	}

	/* Column Style */
	table td {
		color: #000;
	}
	/* Heading and Column Style */
	table tr, table th {
		border-width: 1px;
		border-style: solid;
		border-color: rgb(156, 186, 95);
	}

	/* Padding and font style */
	table td, table th {
		padding: 5px 10px;
		font-size: 12px;
		font-family: Verdana;
		font-weight: bold;
	}
</style>
<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>

<div id="Catalog" align="center">

<div id="Cart">

<h2>Shopping Cart</h2>
	<form action="updateCartQuantities" method="post">
		<table align="center">
			<tr>
				<th><b>Item ID</b></th>
				<th><b>Product ID</b></th>
				<th><b>Description</b></th>
				<th><b>In Stock?</b></th>
				<th><b>Quantity</b></th>
				<th><b>List Price</b></th>
				<th><b>Total Cost</b></th>
				<th>&nbsp;</th>
			</tr>

			<c:if test="${sessionScope.cart.numberOfItems == 0}">
				<tr>
					<td colspan="8"><b>Your cart is empty.</b></td>
				</tr>
			</c:if>

			<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
				<tr class="bg">
					<td>
						<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
					</td>
					<td>
						${cartItem.item.product.productId}
					</td>
					<td>
						${cartItem.item.attribute1} ${cartItem.item.attribute2}
					    ${cartItem.item.attribute3} ${cartItem.item.attribute4}
					    ${cartItem.item.attribute5} ${cartItem.item.product.name}
					</td>
					<td>
						${cartItem.inStock}
					</td>
					<td>
						<input type="text" id="quantity" onblur="updateCart();" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
						<div id="cartMsg"></div>
						<script type="text/javascript" src="../../js/updateCart.js"></script>
					</td>
					<td>
						<!--format标签显示单价-->
						<fmt:formatNumber value="${cartItem.item.listPrice}"
						pattern="$#,##0.00" />
					</td>
					<td>
						<!--format标签显示总价-->
						<label id="total">
						<fmt:formatNumber value="${cartItem.total}"
						pattern="$#,##0.00" />
						</label>
					</td>
					<td>
						<a class="Button" href="removeItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
					</td>
				</tr>
				<script src="../../js/cartChange.js"></script>
			</c:forEach>
			<tr id="lastTR">
				<td colspan="7" id="lastTD">
					Sub Total:
					<label id="subtotal">
					<fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" />
					</label>
					<input type="submit" value="Update Cart"/>
				</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>

	<c:if test="${sessionScope.cart.numberOfItems > 0}">
		<a class="Button" href="newOrderForm?itemId=${cartItem.item.itemId}">Proceed to Checkout</a>
    </c:if>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>