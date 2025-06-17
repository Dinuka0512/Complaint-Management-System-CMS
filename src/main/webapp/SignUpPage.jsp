<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- signup.html -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Sign Up | Compliment Management System</title>
    <link rel="stylesheet" href="css/logIn&SigninStyle.css" />
    <link rel="icon" type="image/x-icon" href="favicon/favicon.ico">
</head>
<body class="dark-theme">

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
    <script>
        alert("<%= error %>");
    </script>
    <% } %>

<div class="form-container">
    <h2>Create Account</h2>
    <p class="subtitle">Join to submit or manage IT compliments</p>
    <form action="signUp" method="post">
        <input type="text" placeholder="Full Name" required name="name" />
        <input type="email" placeholder="Email" required name="email" />
        <input type="password" placeholder="Password" required name="password"/>
        <select required name="role">
            <option value="">Select Role</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>
        <button type="submit">Register</button>
    </form>
    <p class="switch">Already have an account? <a href="LoginPage.jsp">Login</a></p>
</div>
</body>
</html>
