<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.jspcmsfinal.dto.ComplainDto" %>
<%@ page import="com.example.jspcmsfinal.dto.tm.UserAnswerTm" %>
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
            <a href="userDashboard.jsp">Dashboard</a>
            <a href="UserCompliments.jsp">Compliments</a>
            <a href="UserAnswerManage.jsp">Answers</a>
            <a href="UserProfileManage.jsp">Profile</a>
            <a href="LoginPage.jsp">Logout</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="dashboard-content">
        <h1>Welcome, <%= session.getAttribute("name") %></h1>

        <!-- Compliment Stats -->
        <div class="stats-grid">
            <div class="card">
                <h3>Total Compliments</h3>
                <p><%=session.getAttribute("allCount")%></p>
            </div>
            <div class="card">
                <h3>Solved</h3>
                <p><%=session.getAttribute("solved")%></p>
            </div>
            <div class="card">
                <h3>Pending</h3>
                <p><%=session.getAttribute("pending")%></p>
            </div>
        </div>

        <!-- User Compliment History -->
        <section class="panel">
            <h2>Your Compliments</h2>
            <table>
                <thead>
                <tr>
                    <th>Subject</th>
                    <th>Message</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<ComplainDto> complimentList = (ArrayList<ComplainDto>) session.getAttribute("complimentList");
                    if (complimentList != null && !complimentList.isEmpty()) {
                        for (ComplainDto dto : complimentList) {
                %>
                <tr>
                    <td><%= dto.getSubject() %></td>
                    <td><%= dto.getMessage() %></td>
                    <td><%= dto.getDate() %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="3">No compliments available.</td>
                </tr>
                <%
                    }
                %>
                </tbody>

            </table>
        </section>

        <section class="panel">
            <h2>Answers For your Compliments</h2>
            <table>
                <thead>
                <tr>
                    <th>User</th>
                    <th>User Complain</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<UserAnswerTm> userAnswers = (ArrayList<UserAnswerTm>) session.getAttribute("userAnswers");
                    if (userAnswers != null && !userAnswers.isEmpty()) {
                        for (UserAnswerTm a : userAnswers) {
                %>
                <tr>
                    <td><%= a.getComSubject() + " | " + a.getComMessage() + " | " + a.getComDate() %></td>
                    <td><%= a.getAnsSubject() + " | " + a.getAnsMessage() + " | " + a.getAnsDate() %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="3">No answers found.</td></tr>
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
