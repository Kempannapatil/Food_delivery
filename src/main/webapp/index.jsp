<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="index.css">
</head>

<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="login" method="POST">
            <div class="input-group">
                <label for="#email">Email</label>
                <input type="text" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
            <p class="signup-link">Don't have an account? <a href="signup.jsp">Sign up</a></p>
        </form>
    </div>
</body>
</html>