<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Profile Settings</title>
    <link rel="stylesheet" href="css/Dashboards.css" />
    <link rel="icon" type="image/x-icon" href="favicon/favicon.ico">
    <style>
        .profile-card {
            background-color: #161b22;
            padding: 30px;
            border-radius: 10px;
            max-width: 500px;
            margin: 30px auto;
            box-shadow: 0 0 15px rgba(78, 160, 255, 0.1);
        }

        .profile-card h2 {
            color: #58a6ff;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            color: #bcd1f9;
            margin-bottom: 6px;
            font-weight: 500;
        }

        .form-group input {
            width: 100%;
            padding: 10px 14px;
            background-color: #0d1117;
            border: 1px solid #30363d;
            color: #f0f6fc;
            border-radius: 6px;
            font-size: 15px;
        }

        .form-group input[readonly] {
            background-color: #1a1e27;
            color: #94a9ff;
        }

        .btn-save {
            background-color: #238636;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        .btn-save:hover {
            background-color: #2ea043;
        }
    </style>
</head>
<body class="dark-theme">
<div class="dashboard-layout">

    <!-- Sidebar -->
    <aside class="sidebar">
        <h2>User Panel</h2>
        <nav>
            <a href="userDashboard.jsp">Dashboard</a>
            <a href="UserCompliments.jsp">Compliments</a>
            <a href="UserAnswerManage.jsp">Answers</a>
            <a href="UserProfileManage.jsp">Profile</a>
            <a href="LoginPage.jsp">Logout</a>
        </nav>
    </aside>

    <!-- Main -->
    <main class="dashboard-content">
        <div class="profile-card">
            <h2>Update Profile</h2>

            <form action="userProfile" method="post">
                <div class="form-group">
                    <label for="name">Full Name</label>
                    <input type="text" name="name" id="name" value="<%= session.getAttribute("name") %>" required />
                </div>

                <div class="form-group">
                    <label for="email">Email (readonly)</label>
                    <input type="email" id="email" value="<%= session.getAttribute("email") %>" readonly/>
                </div>

                <div class="form-group">
                    <label for="role">Role</label>
                    <input type="text" id="role" value="<%= session.getAttribute("role") %>" readonly />
                </div>

                <div class="form-group">
                    <label for="password">New Password</label>
                    <input type="password" name="password" id="password" placeholder="Enter new password (optional)" />
                </div>

                <button type="submit" class="btn-save">Save Changes</button>
            </form>
        </div>
    </main>
</div>
</body>
</html>
