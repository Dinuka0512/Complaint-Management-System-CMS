<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.ComplainDto" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin - Manage Compliments</title>
  <link rel="stylesheet" href="css/Dashboards.css" />
  <style>
    /* Modal Styling */
    .modal {
      display: none;
      position: fixed;
      z-index: 99;
      padding-top: 100px;
      left: 0; top: 0; width: 100%; height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.5);
    }

    .modal-content {
      background-color: #1e1e2f;
      margin: auto;
      padding: 30px;
      border: 1px solid #888;
      width: 400px;
      border-radius: 10px;
      color: #fff;
    }

    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
      cursor: pointer;
    }

    .modal-content input,
    .modal-content textarea {
      width: 100%;
      padding: 8px;
      margin: 8px 0;
      box-sizing: border-box;
      background: #2b2b3c;
      color: #fff;
      border: 1px solid #555;
      border-radius: 4px;
    }

    .modal-content button {
      background-color: #4CAF50;
      color: white;
      padding: 10px;
      width: 100%;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    .modal-content button:hover {
      background-color: #45a049;
    }

    .btn-submit {
      background-color: #4CAF50;
      color: white;
      padding: 6px 12px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }

    .btn-submit:hover {
      background-color: #3e8e41;
    }
  </style>

  <script>
    function openAnswerModal(complainId) {
      document.getElementById("complainIdInput").value = complainId;
      document.getElementById("answerModal").style.display = "block";
    }

    function closeModal() {
      document.getElementById("answerModal").style.display = "none";
    }

    window.onclick = function(event) {
      const modal = document.getElementById("answerModal");
      if (event.target === modal) {
        modal.style.display = "none";
      }
    }
  </script>
</head>
<body class="dark-theme">
<div class="dashboard-layout">
  <aside class="sidebar">
    <h2>CMS Admin</h2>
    <nav>
      <a href="AdminDashboard.jsp">Dashboard</a>
      <a href="AdminComplimentManage.jsp" class="active">Compliments</a>
      <a href="AdminAnswerManage.jsp">Answers</a>
      <a href="AdminUserManage.jsp">Users</a>
      <a href="LoginPage.jsp">Logout</a>
    </nav>
  </aside>

  <main class="dashboard-content">
    <h1>Manage Compliments</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>User</th>
        <th>Subject</th>
        <th>Message</th>
        <th>Status</th>
        <th>Date</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>101</td>
        <td>user1@example.com</td>
        <td>Great Service</td>
        <td>Loved the prompt support!</td>
        <td>Pending</td>
        <td>2025-06-15</td>
        <td>
          <button onclick="openAnswerModal('101')" class="btn-submit">Mark Solved</button>
        </td>
      </tr>
      <tr>
        <td>102</td>
        <td>user2@example.com</td>
        <td>Excellent Experience</td>
        <td>Staff was very helpful and friendly.</td>
        <td>Solved</td>
        <td>2025-06-14</td>
        <td>
          <!-- Already solved, no button -->
          <span style="color: green; font-weight: bold;">Solved</span>
        </td>
      </tr>
      <tr>
        <td>103</td>
        <td>user3@example.com</td>
        <td>Quick Response</td>
        <td>Response time was impressive.</td>
        <td>Pending</td>
        <td>2025-06-13</td>
        <td>
          <button onclick="openAnswerModal('103')" class="btn-submit">Mark Solved</button>
        </td>
      </tr>
      </tbody>
    </table>
  </main>
</div>

<!-- Modal for Answer Form -->
<div id="answerModal" class="modal">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <h2>Post Answer</h2>
    <form method="post" action="adminCompliment">
      <input type="hidden" id="complainIdInput" name="complainId" />

      <label for="subject">Subject:</label>
      <input type="text" name="subject" id="subject" required />

      <label for="message">Message:</label>
      <textarea name="message" rows="4" id="message" required></textarea>

      <button type="submit" name="action" value="solve">Submit Answer</button>
    </form>
  </div>
</div>
</body>
</html>
