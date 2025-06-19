<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.AnswerDto" %>
<%@ page import="com.example.jspcmsfinal.dto.tm.AnswerTm" %>
<%@ page import="com.example.jspcmsfinal.dto.tm.UserAnswerTm" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin - Manage Answers</title>
  <link rel="stylesheet" href="css/Dashboards.css">
  <style>
    .modal-overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: rgba(0,0,0,0.6);
      display: none;
      justify-content: center;
      align-items: center;
      z-index: 1000;
    }
    .modal {
      background-color: #0d1117;
      padding: 30px;
      border-radius: 10px;
      width: 400px;
      position: relative;
    }
    .modal h2 {
      margin-top: 0;
    }
    .modal form input, .modal form textarea {
      width: 100%;
      margin-bottom: 15px;
      padding: 8px;
    }
    .modal form button {
      padding: 8px 16px;
      background: #007bff;
      color: white;
      border: none;
      cursor: pointer;
    }
    .close-btn {
      position: absolute;
      top: 8px;
      right: 12px;
      font-size: 20px;
      cursor: pointer;
      color: red;
    }
  </style>
</head>
<body class="dark-theme">
<div class="dashboard-layout">
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

  <main class="dashboard-content">
    <h1>Manage Answers</h1>
    <table>
      <thead>
      <tr>
        <th>User</th>
        <th>User Complain</th>
        <th>Answer</th>
      </tr>
      </thead>
      <tbody>
      <%
        ArrayList<UserAnswerTm> userAnswers = (ArrayList<UserAnswerTm>) session.getAttribute("userAnswers");
        if (userAnswers != null && !userAnswers.isEmpty()) {
          for (UserAnswerTm a : userAnswers) {
      %>
      <tr>
        <td><%= a.getUEmail() %></td>
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

  </main>
</div>

<!-- Floating Update Form -->
<div class="modal-overlay" id="updateModal">
  <div class="modal">
    <span class="close-btn" onclick="closeUpdateModal()">×</span>
    <h2>Update Answer</h2>
    <form method="post" action="adminAnswer">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="ansId" id="modalAnsId">
      <label>Subject:</label>
      <input type="text" name="subject" id="modalSubject" required>
      <label>Message:</label>
      <textarea name="message" id="modalMessage" required></textarea>
      <button type="submit">Update</button>
    </form>
  </div>
</div>

<script>
  function openUpdateModal(ansId, subject, message) {
    document.getElementById('modalAnsId').value = ansId;
    document.getElementById('modalSubject').value = subject;
    document.getElementById('modalMessage').value = message;
    document.getElementById('updateModal').style.display = 'flex';
  }

  function closeUpdateModal() {
    document.getElementById('updateModal').style.display = 'none';
  }
</script>
</body>
</html>
