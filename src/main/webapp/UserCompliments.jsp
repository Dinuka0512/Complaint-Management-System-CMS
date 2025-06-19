<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.jspcmsfinal.dto.ComplainDto" %>
<!DOCTYPE html>
<html>
<head>
  <title>Manage Compliments</title>
  <link rel="stylesheet" href="css/Dashboards.css" />
  <link rel="icon" href="favicon/favicon.ico" type="image/x-icon">
  <style>
    /* Your existing styles here, plus... */

    /* Floating add button */
    #btnAddNew {
      position: fixed;
      top: 20px;
      right: 20px;
      background-color: #238636;
      color: white;
      border: none;
      padding: 12px 20px;
      border-radius: 5px;
      font-weight: bold;
      cursor: pointer;
      z-index: 1001;
      box-shadow: 0 2px 6px rgba(0,0,0,0.3);
    }
    #btnAddNew:hover {
      background-color: #2ea043;
    }

    /* Modal background */
    .modal {
      display: none; /* Hidden by default */
      position: fixed;
      z-index: 1000;
      left: 0; top: 0;
      width: 100%; height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.5);
    }

    /* Modal content */
    .modal-content {
      background-color: #161b22;
      margin: 80px auto;
      padding: 25px;
      border-radius: 8px;
      max-width: 600px;
      color: #f0f6fc;
      position: relative;
      box-shadow: 0 5px 15px rgba(0,0,0,0.5);
    }

    /* Close button */
    .close-btn {
      position: absolute;
      top: 12px;
      right: 15px;
      font-size: 24px;
      font-weight: bold;
      color: #f0f6fc;
      cursor: pointer;
    }

    /* Form styles (reuse your form styles) */
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
      text-align: right;
    }
    .btn-submit {
      background-color: #238636;
      color: white;
      padding: 10px 18px;
      margin-left: 10px;
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
      margin-left: 10px;
    }
    .btn-submit:hover {
      background-color: #2ea043;
    }
    .btn-delete:hover {
      background-color: #b91c1c;
    }

    /* Table styles (same as before) */
    table {
      margin-top: 80px; /* give space for floating button */
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
      text-align: left;
    }
    th {
      background-color: #21262d;
    }
    /* Align action buttons right */
    td.actions {
      text-align: right;
      white-space: nowrap;
    }
    /* Status badges */
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
      <a href="UserAnswerManage.jsp">Answers</a>
      <a href="UserProfileManage.jsp">Profile</a>
      <a href="LoginPage.jsp">Logout</a>
    </nav>
  </aside>

  <!-- Main Content -->
  <main class="dashboard-content">
    <h1>Manage Your Compliments</h1>

    <!-- Floating Add New Compliment Button -->
    <button id="btnAddNew" onclick="openModalForAdd()">+ Add New Compliment</button>

    <!-- Compliments Table -->
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Status</th>
        <th>Date</th>
        <th class="actions">Actions</th>
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
        <td>
          <span class="badge <%= "Solved".equalsIgnoreCase(dto.getStatus()) ? "solved" : "pending" %>">
            <%= dto.getStatus() %>
          </span>
        </td>
        <td><%= dto.getDate() %></td>
        <td class="actions">
          <button type="button" onclick="openModalForUpdate('<%=dto.getComplainId()%>', '<%=dto.getSubject().replace("'", "\\'")%>', '<%=dto.getMessage().replace("'", "\\'")%>')" class="btn-submit">Update</button>

          <form action="userCompliment" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this compliment?');">
            <input type="hidden" name="id" value="<%=dto.getComplainId()%>" />
            <button type="submit" name="action" value="delete" class="btn-delete">Delete</button>
          </form>
        </td>
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

    <!-- Modal form for Add / Update -->
    <div id="modalForm" class="modal">
      <div class="modal-content">
        <span class="close-btn" onclick="closeModal()">&times;</span>
        <h2 id="modalTitle">Add New Compliment</h2>
        <form id="complimentForm" action="userCompliment" method="post">
          <input type="text" name="id" id="complimentId" disabled/>
          <div class="form-group">
            <label for="subject">Subject</label>
            <input type="text" name="subject" id="subject" required />
          </div>
          <div class="form-group">
            <label for="message">Message</label>
            <textarea name="message" id="message" required></textarea>
          </div>
          <div class="btn-group">
            <button type="submit" name="action" value="save" id="btnSave" class="btn-submit">Save</button>
            <button type="submit" name="action" value="update" id="btnUpdate" class="btn-submit" style="display:none;">Update</button>
            <button type="submit" name="action" value="delete" id="btnDelete" class="btn-delete" style="display:none;">Delete</button>
          </div>
        </form>
      </div>
    </div>

  </main>
</div>

<script>
  // Open modal for adding new compliment
  function openModalForAdd() {
    document.getElementById('modalTitle').innerText = 'Add New Compliment';
    document.getElementById('complimentId').value = '';
    document.getElementById('subject').value = '';
    document.getElementById('message').value = '';
    document.getElementById('btnSave').style.display = 'inline-block';
    document.getElementById('btnUpdate').style.display = 'none';
    document.getElementById('btnDelete').style.display = 'none';
    document.getElementById('modalForm').style.display = 'block';
  }

  // Open modal for updating existing compliment
  function openModalForUpdate(id, subject, message) {
    document.getElementById('modalTitle').innerText = 'Update Compliment';
    document.getElementById('complimentId').value = id;
    document.getElementById('subject').value = subject;
    document.getElementById('message').value = message;
    document.getElementById('btnSave').style.display = 'none';
    document.getElementById('btnUpdate').style.display = 'inline-block';
    document.getElementById('btnDelete').style.display = 'inline-block';
    document.getElementById('modalForm').style.display = 'block';
  }

  // Close modal
  function closeModal() {
    document.getElementById('modalForm').style.display = 'none';
  }

  // Close modal when clicking outside content area
  window.onclick = function(event) {
    var modal = document.getElementById('modalForm');
    if (event.target == modal) {
      closeModal();
    }
  }
</script>

</body>
</html>
