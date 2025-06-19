<%@ page import="com.example.jspcmsfinal.dto.tm.ComplainTm" %>
<%@ page import="java.util.ArrayList" %>
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
            <a href="AdminDashboard.jsp">Dashboard</a>
            <a href="AdminComplimentManage.jsp">Compliments</a>
            <a href="AdminAnswerManage.jsp">Answers</a>
            <a href="AdminUserManage.jsp">Users</a>
            <a href="LoginPage.jsp">Logout</a>
        </nav>
    </aside>

    <!-- Main content -->
    <main class="dashboard-content">
        <h1>Welcome, Admin</h1>

        <!-- Stats Overview -->
        <div class="stats-grid">
            <div class="card">
                <h3>Recent Compliments</h3>
                <p><%= session.getAttribute("comThisWeek") %> new this week</p>
            </div>
            <div class="card">
                <h3>Solved Compliments</h3>
                <p><%= session.getAttribute("comTotalSolve") %> total</p>
            </div>
            <div class="card">
                <h3>Total Compliments</h3>
                <p> <%= session.getAttribute("comTotal") %> registered</p>
            </div>
        </div>

        <!-- Compliment Management -->
        <section class="panel">
            <h2>Manage Compliments</h2>
            <table>
                <thead>
                <tr>
                    <th>User</th>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<ComplainTm> allCom = (ArrayList<com.example.jspcmsfinal.dto.tm.ComplainTm>) session.getAttribute("allCom");

                    if (allCom != null && !allCom.isEmpty()) {
                        for (com.example.jspcmsfinal.dto.tm.ComplainTm com : allCom) {
                %>
                <tr>
                    <td><%= com.getUEmail() %></td>
                    <td><%= com.getSubject() %></td>
                    <td><%= com.getMessage() %></td>
                    <td><%= com.getStatus() %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="5">No compliments found.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </section>
    </main>
</div>
</body>
</html>
