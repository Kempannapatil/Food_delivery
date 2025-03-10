<!DOCTYPE html>
<html lang="en">
<head>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tapfood.model.Restaurant" %>
<%@ page import="com.tapfood.model.User" %>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Listing</title>
    
    <style>
    
       * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    color: #333;
}

header {
    background-color: #ff5722;
    padding: 20px;
    text-align: center;
    color: white;
}

h1 {
    font-size: 2.5rem;
}

main {
    margin: 20px;
}

h2 {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 20px;
}

.restaurant-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
}

.restaurant-card {
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    transition: transform 0.3s;
}

.restaurant-card:hover {
    transform: translateY(-10px);
}

.restaurant-card img {
    width: 100%;
    height: 150px;
    object-fit: cover;
}

.restaurant-card h3 {
    font-size: 1.5rem;
    color: #ff5722;
    margin: 10px;
}

.restaurant-card p {
    margin: 10px;
    font-size: 1rem;
    color: #555;
}

footer {
    background-color: #ff5722;
    color: white;
    text-align: center;
    padding: 10px 0;
    position: fixed;
    width: 100%;
    bottom: 0;
}
    
    </style>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity=
    "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
</head>
<body>
<%User user=(User)session.getAttribute("user"); %>

    <header>
        <h1>Welcome ${user.getUsername()}</h1>
        
        
         <a href="index.jsp" class="btn btn-info ">logout</a>
        
    </header>

    <main>
        <section class="restaurant-list">
            <h2>Available Restaurants</h2>

            <div class="restaurant-container">
                <!-- Loop through the restaurant list from session -->
                <%
                    ArrayList<Restaurant> restaurantlist = (ArrayList<Restaurant>)session.getAttribute("restaurantlist");
                    for(Restaurant restaurant : restaurantlist){
                %>
                <div class="restaurant-card">
                    <img src="<%= restaurant.getImagePath() %>" alt="Restaurant Image">
                    <h3><%= restaurant.getRestaurantName() %></h3>
                    <p>Type: <%= restaurant.getCuisiveType() %></p>
                    <p>Delivery Time: <%= restaurant.getDeliveryTime() %> mins</p>
                    <p>Rating: <%= restaurant.getRating() %> ⭐</p>
                    <p><%= restaurant.getAddress() %></p>
                   <p><a href="menu?resId=<%=restaurant.getRestaurantId()%>">View Menu</a></p>

          
                </div>
                <%
                    }
                %>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 TapFood. All Rights Reserved.</p>
    </footer>
</body>
</html>
