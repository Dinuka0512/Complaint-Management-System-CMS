<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.ComplainDto" %>
<%@ page import="com.example.jspcmsfinal.model.UserModel" %>
<%@ page import="com.example.jspcmsfinal.db.DBConnectionPool" %>
<%@ page import="com.example.jspcmsfinal.dto.UserDto" %>
<%@ page import="java.sql.Connection" %>
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

    .btn-delete {
      margin-top: 2px;
      background-color: #d32f2f;
      color: white;
      padding: 10px 18px;
      border: none;
      border-radius: 5px;
      font-weight: bold;
      cursor: pointer;
    }

    .btn-delete:hover {
      background-color: #b91c1c;
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
      <%
        ArrayList<ComplainDto> complains = (ArrayList<ComplainDto>) session.getAttribute("complains");

        UserModel userModel = new UserModel();

        if (complains != null && !complains.isEmpty()) {
          for (ComplainDto dto : complains) {
            UserDto user = userModel.getUserById(dto.getUId(), DBConnectionPool.getConnection());
      %>
      <tr>
        <td><%=dto.getComplainId()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=dto.getSubject()%></td>
        <td><%=dto.getMessage()%></td>
        <td><%=dto.getStatus()%></td>
        <td><%=dto.getDate()%></td>
        <td>
          <button class="btn-submit" onclick="openAnswerModal('<%=dto.getComplainId()%>')">Answer</button>
          <form action="adminCompliment" method="post" style="display:inline;">
            <input type="hidden" name="complainId" value="<%= dto.getComplainId() %>">
            <input type="hidden" name="action" value="delete">
            <button type="submit" class="btn-delete">Delete</button>
          </form>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr><td colspan="7">Compliments are not yet available.</td></tr>
      <%
        }
      %>
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
