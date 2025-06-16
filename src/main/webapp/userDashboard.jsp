<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>User Dashboard</title>
    <link rel="stylesheet" href="css/Dashboards.css" />
    <link rel="icon" type="image/x-icon" href="favicon/favicon.ico">
</head>
<body class="dark-theme">
<div class="dashboard-layout">
    <!-- Sidebar -->
    <aside class="sidebar">
        <h2>User Panel</h2>
        <nav>
            <a href="#">Dashboard</a>
            <a href="#">Submit Compliment</a>
            <a href="#">Profile</a>
            <a href="/login">Logout</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="dashboard-content">
        <h1>Welcome, [User]</h1>

        <!-- Compliment Stats -->
        <div class="stats-grid">
            <div class="card">
                <h3>Total Compliments</h3>
                <p>12</p>
            </div>
            <div class="card">
                <h3>Solved</h3>
                <p>7</p>
            </div>
            <div class="card">
                <h3>Pending</h3>
                <p>5</p>
            </div>
        </div>

        <!-- User Compliment History -->
        <section class="panel">
            <h2>Your Compliments</h2>
            <table>
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Subject</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>2025-06-12</td>
                    <td>Appreciated IT team for network support</td>
                    <td><span class="status solved">Solved</span></td>
                </tr>
                <tr>
                    <td>2025-06-14</td>
                    <td>Printer issue in office 4</td>
                    <td><span class="status pending">Pending</span></td>
                </tr>
                <tr>
                    <td>2025-06-15</td>
                    <td>Quick fix for laptop problem</td>
                    <td><span class="status solved">Solved</span></td>
                </tr>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>
