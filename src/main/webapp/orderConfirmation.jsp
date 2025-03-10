<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tapfood.model.Cart" %>
<%@ page import="com.tapfood.model.CartItem" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>

    <h1>Order Confirmation</h1>
    <p>Your order has been placed successfully!</p>

    <h2>Order Details:</h2>
    <table>
        <thead>
            <tr>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Total Price</th>
            </tr>
        </thead>
        <tbody>
            <%
                Cart cart = (Cart) session.getAttribute("cart");
                double totalOrderPrice = 0; // Declare totalOrderPrice here

                if (cart != null && !cart.getItems().isEmpty()) {
                    for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                        CartItem item = entry.getValue();
                        double itemTotal = item.getPrice() * item.getQuantity();
                        totalOrderPrice += itemTotal; // Accumulate total price
            %>
            <tr>
                <td><%= item.getItemname() %></td>
                <td><%= item.getQuantity() %></td>
                <td><%= String.format("%.2f", itemTotal) %></td>
            </tr>
            <%
                    } // End of for loop

                    // Set the total order price in the session after calculating
                    session.setAttribute("totalOrderPrice", totalOrderPrice);
            %>
            </tbody>
        </table>

        <h3>Total Amount: 
            <%
                // Retrieve totalOrderPrice from session
                Object totalOrderPriceObj = session.getAttribute("totalOrderPrice");
                Double retrievedTotalOrderPrice = null;

                if (totalOrderPriceObj instanceof Double) {
                    retrievedTotalOrderPrice = (Double) totalOrderPriceObj;
                } else if (totalOrderPriceObj instanceof Number) {
                    retrievedTotalOrderPrice = ((Number) totalOrderPriceObj).doubleValue();
                }

                if (retrievedTotalOrderPrice == null) {
                    retrievedTotalOrderPrice = 0.0; // Default to 0.0 if not set
                }
            %>
            <%= String.format("%.2f", retrievedTotalOrderPrice) %>
        </h3> <!-- Display the total amount -->
    <%
                } else {
                    out.print("<p>No items in the cart.</p>");
                }
    %>
    
     <a href="home.jsp" class="btn btn-primary">Back to Home</a>
    
</body>
</html>
