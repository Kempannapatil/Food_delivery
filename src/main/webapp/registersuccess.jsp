<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Success</title>
    <link rel="stylesheet" href="styles.css">
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

.success-container {
    text-align: center;
}

.success-card {
    background-color: #fff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    max-width: 400px;
    width: 100%;
}

h1 {
    color: #2ecc71;
    margin-bottom: 15px;
}

p {
    color: #333;
    margin-bottom: 25px;
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    background-color: #2ecc71;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.btn:hover {
    background-color: #27ae60;
}
    
    </style>
    
</head>
<body>
    <div class="success-container">
        <div class="success-card">
            <h1>Registration Successful!</h1>
            <p>Your account has been successfully created.</p>
            <a href="index.jsp" class="btn">Go to Dashboard</a>
        </div>
    </div>
</body>
</html>
