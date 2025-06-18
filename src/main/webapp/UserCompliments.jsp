<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.ComplainDto" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Compliments</title>
  <link rel="stylesheet" href="css/Dashboards.css" />
  <link rel="icon" href="favicon/favicon.ico" type="image/x-icon">
  <style>
    .form-wrapper {
      background-color: #161b22;
      padding: 25px;
      margin-top: 20px;
      border-radius: 8px;
      max-width: 600px;
    }

    .form-group {
      margin-bottom: 15px;
    }

    label {
      color: #94a9ff;
      display: block;
      margin-bottom: 6px;
      font-weight: bold;
    }

    input, textarea {
      width: 100%;
      padding: 10px;
      border: 1px solid #30363d;
      background-color: #0d1117;
      color: #f0f6fc;
      border-radius: 5px;
    }

    textarea {
      resize: vertical;
      min-height: 80px;
    }

    .btn-group {
      margin-top: 20px;
    }

    .btn-submit {
      background-color: #238636;
      color: white;
      padding: 10px 18px;
      margin-right: 10px;
      border: none;
      border-radius: 5px;
      font-weight: bold;
      cursor: pointer;
    }

    .btn-delete {
      background-color: #d32f2f;
      color: white;
      padding: 10px 18px;
      border: none;
      border-radius: 5px;
      font-weight: bold;
      cursor: pointer;
    }

    .btn-submit:hover {
      background-color: #2ea043;
    }

    .btn-delete:hover {
      background-color: #b91c1c;
    }

    table {
      margin-top: 30px;
      width: 100%;
      background-color: #161b22;
      color: #f0f6fc;
      border-collapse: collapse;
      border-radius: 6px;
      overflow: hidden;
    }

    th, td {
      padding: 12px;
      border-bottom: 1px solid #30363d;
    }

    th {
      background-color: #21262d;
    }

    .badge.solved {
      background-color: #238636;
      padding: 5px 10px;
      border-radius: 4px;
    }

    .badge.pending {
      background-color: #d29922;
      padding: 5px 10px;
      border-radius: 4px;
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
      <a href="UserProfileManage.jsp">Profile</a>
      <a href="LoginPage.jsp">Logout</a>
    </nav>
  </aside>

  <!-- Main Content -->
  <main class="dashboard-content">
    <h1>Manage Your Compliments</h1>

    <!-- ✅ Form Section -->
    <div class="form-wrapper">
      <form action="userCompliment" method="post">
        <div class="form-group">
          <label for="subject">Subject</label>
          <input type="text" name="subject" id="subject" required />
        </div>

        <div class="form-group">
          <label for="message">Message</label>
          <textarea name="message" id="message" required></textarea>
        </div>

        <div class="btn-group">
          <button type="submit" name="action" value="save" class="btn-submit">Save</button>
          <button type="submit" name="action" value="update" class="btn-submit">Update</button>
          <button type="submit" name="action" value="delete" class="btn-delete">Delete</button>
        </div>
      </form>
    </div>

    <!-- ✅ Table Section -->
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Status</th>
        <th>Date</th>
      </tr>
      </thead>
      <tbody>
      <%
        ArrayList<ComplainDto> list = (ArrayList<ComplainDto>) session.getAttribute("complimentList");
        if (list != null && !list.isEmpty()) {
          for (ComplainDto dto : list) {
      %>
      <tr>
        <td><%= dto.getComplainId() %></td>
        <td><%= dto.getSubject() %></td>
        <td><%= dto.getStatus() %></td>
        <td><%= dto.getDate() %></td>
      </tr>
      <%
        }
      } else {
      %>
      <tr><td colspan="5">No compliments found.</td></tr>
      <%
        }
      %>
      </tbody>
    </table>

  </main>
</div>
</body>
</html>
