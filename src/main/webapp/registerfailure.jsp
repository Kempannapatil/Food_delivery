<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Failed</title>
   
   
   <style>
   
   * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Arial', sans-serif;
    background-color: #f0f4f8;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.failure-container {
    text-align: center;
}

.failure-card {
    background-color: #fff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
}

h1 {
    color: #e74c3c;
    margin-bottom: 15px;
}

p {
    color: #333;
    margin-bottom: 25px;
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #e74c3c;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.btn:hover {
    background-color: #c0392b;
}
   
   </style>
   
</head>
<body>
    <div class="failure-container">
        <div class="failure-card">
            <h1>Registration Failed</h1>
            <p>There was an error with your registration. Please try again.</p>
            <a href="signup.jsp" class="btn">Try Again</a>
        </div>
    </div>
</body>
</html>
