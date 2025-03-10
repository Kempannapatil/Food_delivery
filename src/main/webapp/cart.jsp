<%@ page import="java.util.Map" %>
<%@ page import="com.tapfood.model.Cart" %>
<%@ page import="com.tapfood.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart - TapFood</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="cart.css">
</head>
<body>
<header>
    <h1>Your Shopping Cart</h1>
</header>

<main class="container">
    <h2 class="text-center">Items in Your Cart</h2>
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
    %>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                double totalPrice = 0.0;
                Map<Integer, CartItem> items = cart.getItems();
                for (CartItem item : items.values()) {
                    double itemTotal = item.getPrice() * item.getQuantity();
                    totalPrice += itemTotal;
            %>
            <tr>
                <td><%= item.getItemname() %></td>
                <td>
                    <form action="updateCart" method="post" style="display:inline;">
                        <input type="hidden" name="itemId" value="<%= item.getItemid() %>">
                        <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" style="width: 70px; display: inline-block;">
                        <button type="submit" class="btn btn-warning">Update</button>
                    </form>
                </td>
                <td>₹<%= String.format("%.2f", item.getPrice()) %></td>
                <td>₹<%= String.format("%.2f", itemTotal) %></td>
                <td>
                    <form action="removeItem" method="post" style="display:inline;">
                        <input type="hidden" name="itemId" value="<%= item.getItemid() %>">
                        <button type="submit" class="btn btn-danger">Remove</button>
                    </form>
                </td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" class="text-right total-price">Total</td>
                <td class="total-price">₹<%= String.format("%.2f", totalPrice) %></td>
            </tr>
        </tbody>
    </table>

    <!-- Proceed to Pay Button -->
    <div class="text-center proceed-btn">
        <form action="checkout.jsp" method="get">
            <button type="submit" class="btn btn-primary">Proceed to Pay</button>
        </form>
    </div>
    <%
        } else {
    %>
    <p class="text-center">Your cart is empty.</p>
    <%
        }
    %>
</main>

<div class="container">
    <footer>
        <p>&copy; 2024 TapFood. All Rights Reserved.</p>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
