<%@ page import="java.util.List" %>
<%@ page import="com.tapfood.model.Menu" %>
<%@ page import="com.tapfood.model.User" %>
<%@ page import="com.tapfood.daoimpl.MenuDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: white; }
        .menu-container { margin: 20px; }
        .menu-card { background-color: white; border-radius: 8px; padding: 15px; margin: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }
        .menu-card h5 { color: #ff5722; }
    </style>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>

<header class="bg-primary text-white text-center p-4">
    <h1>Menu</h1>
    <h3>Welcome, <%= user != null ? user.getUsername() : "Guest" %></h3>
</header>

<main class="menu-container">
    <div class="container">
        <h2 class="text-center">Available Menu Items</h2>

        <div class="row">
            <%
               List<Menu> menuList = (List<Menu>) session.getAttribute("menulist");
            
               if (menuList != null && !menuList.isEmpty()) {
                   for (Menu menuItem : menuList) {
            %>
            <div class="col-md-4">
                <div class="menu-card">
                    <h5><%= menuItem.getItemName() %></h5>
                    <p>Price: ₹<%= String.format("%.2f", menuItem.getPrice()) %></p> <!-- Price displayed with Rupee symbol -->
                    <p>Available: <%= menuItem.isAvailable() ? "Yes" : "No" %></p>

                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="menuId" value="<%= menuItem.getMenuId() %>">
                        <input type="hidden" name="restaurantId" value="<%= menuItem.getRestaurantId() %>">
                        <input type="hidden" name="itemName" value="<%= menuItem.getItemName() %>">
                        <input type="hidden" name="price" value="<%= menuItem.getPrice() %>">

                        <!-- Quantity Input Field -->
                        <div class="form-group">
                            <label for="quantity<%= menuItem.getMenuId() %>">Quantity:</label>
                            <input type="number" name="quantity" id="quantity<%= menuItem.getMenuId() %>" value="1" min="1" class="form-control" style="width: 70px; display: inline-block;">
                        </div>

                        <button type="submit" class="btn btn-success">Add to Cart</button>
                    </form>
                </div>
            </div>
            <% 
                   } 
               } else { 
            %>
            <p class="text-center">No menu items available at the moment.</p>
            <% 
               } 
            %>
        </div>
    </div>
</main>

<div class="container">
    <footer class="bg-white text-center p-3">
        <p>&copy; 2024 TapFood. All Rights Reserved.</p>
    </footer>
</div>
</body>
</html>
