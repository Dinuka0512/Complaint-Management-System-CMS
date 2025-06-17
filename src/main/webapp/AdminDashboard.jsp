<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/Dashboards.css" />
    <link rel="icon" type="image/x-icon" href="favicon/favicon.ico">
</head>
<body class="dark-theme">
<div class="dashboard-layout">
    <!-- Sidebar -->
    <aside class="sidebar">
        <h2>CMS Admin</h2>
        <nav>
            <a href="#">Dashboard</a>
            <a href="#">Compliments</a>
            <a href="#">Users</a>
            <a href="/login">Logout</a>
        </nav>
    </aside>

    <!-- Main content -->
    <main class="dashboard-content">
        <h1>Welcome, Admin</h1>

        <!-- Stats Overview -->
        <div class="stats-grid">
            <div class="card">
                <h3>Recent Compliments</h3>
                <p>15 new this week</p>
            </div>
            <div class="card">
                <h3>Solved Compliments</h3>
                <p>132 total</p>
            </div>
            <div class="card">
                <h3>Total Users</h3>
                <p>24 registered</p>
            </div>
        </div>

        <!-- Compliment Management -->
        <section class="panel">
            <h2>Manage Compliments</h2>
            <table>
                <thead>
                <tr>
                    <th>User</th>
                    <th>Message</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>john@example.com</td>
                    <td>Great response from IT!</td>
                    <td><span class="status pending">Pending</span></td>
                    <td><button>Mark as Solved</button></td>
                </tr>
                <tr>
                    <td>sara@domain.com</td>
                    <td>Helped me with server issues.</td>
                    <td><span class="status solved">Solved</span></td>
                    <td><button disabled>Solved</button></td>
                </tr>
                </tbody>
            </table>
        </section>

        <!-- User Management -->
        <section class="panel">
            <h2>Manage Users</h2>
            <table>
                <thead>
                <tr>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>admin@domain.com</td>
                    <td>Admin</td>
                    <td><button disabled>Edit</button></td>
                </tr>
                <tr>
                    <td>user@domain.com</td>
                    <td>User</td>
                    <td><button>Edit</button></td>
                </tr>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>
