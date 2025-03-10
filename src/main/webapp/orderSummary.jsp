<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tapfood.model.Order" %>
<%@ page import="com.tapfood.model.OrderItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Summary</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"> <!-- Include your CSS here -->
</head>
<body>
    <div class="container mt-5">
    
     <a href="home.jsp" class="btn btn-primary">Back to Home</a>
    
        <h1>Order Summary</h1>

        <% 
            // Retrieve the Order object from the request
            Order order = (Order) request.getAttribute("order");
            if (order != null) {
                List<OrderItem> orderItems = order.getOrderItems();
        %>
            <h2>Order ID: <%= order.getOrderId() %></h2>
            <p><strong>Restaurant ID:</strong> <%= order.getRestaurantId() %></p>
            <p><strong>User ID:</strong> <%= order.getUserId() %></p>
            <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
            <p><strong>Total Amount:</strong> <%= order.getTotalAmount() %></p>
            <p><strong>Status:</strong> <%= order.getStatus() %></p>
            <p><strong>Payment Mode:</strong> <%= order.getPaymentMode() %></p>

            <h3>Order Items</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Item Total</th>
                    </tr>
                </thead>
                <tbody>
                <% 
                    if (orderItems != null && !orderItems.isEmpty()) {
                        for (OrderItem item : orderItems) { 
                %>
                    <tr>
                        <td><%= item.getItemName() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td><%= item.getTotal() %></td>
                    </tr>
                <% 
                        } 
                    } else { 
                %>
                    <tr>
                        <td colspan="3" class="text-center">No items found in this order.</td>
                    </tr>
                <% 
                    } 
                %>
                </tbody>
            </table>
        <% 
            } else { 
        %>
            <p class="text-danger">Order not found.</p>
        <% 
            } 
        %>
       
    </div>
    
    <script src="js/bootstrap.bundle.min.js"></script> <!-- Include your JS here -->
</body>
</html>
