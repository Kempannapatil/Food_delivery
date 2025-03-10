<%@ page import="java.util.Map" %>
<%@ page import="com.tapfood.model.Cart" %>
<%@ page import="com.tapfood.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - TapFood</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="checkout.css">
</head>
<body>
<header>
    <h1>Checkout</h1>
</header>

<main class="container">
    <h2 class="text-center">Review Your Order</h2>

    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            double totalPrice = 0.0;
            Map<Integer, CartItem> items = cart.getItems();
    %>

    <!-- Order Summary Table -->
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (CartItem item : items.values()) {
                    double itemTotal = item.getPrice() * item.getQuantity();
                    totalPrice += itemTotal;
            %>
            <tr>
                <td><%= item.getItemname() %></td>
                <td><%= item.getQuantity() %></td>
                <td>₹<%= String.format("%.2f", item.getPrice()) %></td>
                <td>₹<%= String.format("%.2f", itemTotal) %></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" class="text-right font-weight-bold">Grand Total</td>
                <td class="font-weight-bold">₹<%= String.format("%.2f", totalPrice) %></td>
            </tr>
        </tbody>
    </table>

    <!-- Payment Method Selection -->
    <h3>Select Payment Method</h3>
    <form action="placeOrder" method="post"> <!-- Change action to placeOrder -->
        <div class="form-group">
            <label for="paymentMode">Payment Method:</label>
            <select name="paymentMode" id="paymentMode" class="form-control" required>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
            </select>
        </div>

        <!-- Place Order Button -->
        <div class="text-center">
            <button type="submit" class="btn btn-success">Place Order</button>
        </div>
    </form>

    <%
        } else {
    %>
    <p class="text-center">Your cart is empty. <a href="menu.jsp">Add items</a> to proceed.</p>
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
