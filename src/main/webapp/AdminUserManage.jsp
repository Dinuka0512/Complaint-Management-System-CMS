<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.UserDto" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin - Manage Users</title>
  <link rel="stylesheet" href="css/Dashboards.css" />
  <style>
    .top-bar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }

    .btn-primary {
      padding: 8px 16px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .btn-danger {
      background-color: #e74c3c;
    }

    .btn-warning {
      background-color: #f39c12;
    }

    .modal {
      display: none;
      position: fixed;
      z-index: 1000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.6);
    }

    .modal-content {
      background-color: #3a3f47;
      border: 1px solid #555;
      margin: 10% auto;
      padding: 20px;
      border-radius: 5px;
      width: 400px;
    }

    .modal-content h2 {
      margin-top: 0;
    }

    .form-group {
      margin-bottom: 12px;
    }

    .form-group label {
      display: block;
      margin-bottom: 5px;
    }

    .form-group input {
      width: 100%;
      padding: 6px;
    }

    .close {
      float: right;
      font-size: 20px;
      cursor: pointer;
    }
  </style>
</head>
<body class="dark-theme">

<div class="dashboard-layout">
  <aside class="sidebar">
    <h2>CMS Admin</h2>
    <nav>
      <a href="AdminDashboard.jsp">Dashboard</a>
      <a href="AdminComplimentManage.jsp">Compliments</a>
      <a href="AdminAnswerManage.jsp">Answers</a>
      <a href="AdminUserManage.jsp" class="active">Users</a>
      <a href="LoginPage.jsp">Logout</a>
    </nav>
  </aside>

  <main class="dashboard-content">
    <div class="top-bar">
      <h1>Manage Users</h1>
      <button class="btn-primary" onclick="openModal('new')">Save New User</button>
    </div>

    <table>
      <thead>
      <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        ArrayList<UserDto> users = (ArrayList<UserDto>) session.getAttribute("allUsers");
        if (users != null) {
          for (UserDto user : users) {
      %>
      <tr>
        <td><%=user.getUId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getRole()%></td>
        <td>
          <button class="btn-warning" onclick="openModal('update', '<%=user.getUId()%>', '<%=user.getName()%>', '<%=user.getEmail()%>', '<%=user.getRole()%>')">Update</button>
          <form action="adminUser" method="post" style="display:inline;">
            <input type="hidden" name="action" value="delete" />
            <input type="hidden" name="userId" value="<%=user.getUId()%>" />
            <button type="submit" class="btn-danger">Delete</button>
          </form>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr><td colspan="5">No users found.</td></tr>
      <%
        }
      %>
      </tbody>
    </table>
  </main>
</div>

<!-- Floating Modal Form -->
<div id="userModal" class="modal">
  <div class="modal-content">
    <span class="close" onclick="closeModal()">&times;</span>
    <h2 id="modalTitle">New User</h2>
    <form method="post" action="adminUser">
      <input type="hidden" name="action" id="formAction" value="save" />
      <input type="hidden" name="userId" id="userId" />

      <div class="form-group">
        <label for="userName">Name</label>
        <input type="text" name="userName" id="userName" required />
      </div>

      <div class="form-group">
        <label for="userEmail">Email</label>
        <input type="email" name="userEmail" id="userEmail" required />
      </div>

      <div class="form-group">
        <label for="userPassword">Password</label>
        <input type="text" name="userPassword" id="userPassword" required />
      </div>

      <button type="submit" class="btn-primary">Submit</button>
    </form>
  </div>
</div>

<script>
  function openModal(mode, id = '', name = '', email = '', role = '') {
    document.getElementById('userModal').style.display = 'block';

    if (mode === 'new') {
      document.getElementById('modalTitle').innerText = 'Save New User';
      document.getElementById('formAction').value = 'save';
      document.getElementById('userId').value = '';
      document.getElementById('userName').value = '';
      document.getElementById('userEmail').value = '';
      document.getElementById('userRole').value = '';
    } else {
      document.getElementById('modalTitle').innerText = 'Update User';
      document.getElementById('formAction').value = 'update';
      document.getElementById('userId').value = id;
      document.getElementById('userName').value = name;
      document.getElementById('userEmail').value = email;
      document.getElementById('userRole').value = role;
    }
  }

  function closeModal() {
    document.getElementById('userModal').style.display = 'none';
  }

  // Close modal if click outside
  window.onclick = function(event) {
    const modal = document.getElementById('userModal');
    if (event.target === modal) {
      closeModal();
    }
  }
</script>

</body>
</html>
