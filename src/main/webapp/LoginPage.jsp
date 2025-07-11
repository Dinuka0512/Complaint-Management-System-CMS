<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- login.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Login | Compliment Management System</title>
    <link rel="stylesheet" href="css/logIn&SigninStyle.css" />
    <link rel="icon" type="image/x-icon" href="favicon/favicon.ico">
</head>
<body class="dark-theme">
<div class="form-container">
    <h2>Welcome Back</h2>
    <p class="subtitle">Log in to manage IT compliments</p>
    <form action="login" method="post">
        <input type="email" placeholder="Email" required name="email" />
        <input type="password" placeholder="Password" required name="password"/>
        <button type="submit">Login</button>
    </form>
    <p class="switch">Don't have an account? <a href="SignUpPage.jsp">Sign up</a></p>
</div>
</body>
</html>
