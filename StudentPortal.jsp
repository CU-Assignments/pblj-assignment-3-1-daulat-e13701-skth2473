<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Student Attendance Portal</title></head>
<body>
    <h2>Enter Attendance</h2>
    <form action="AttendanceServlet" method="post">
        Roll No: <input type="text" name="rollNo"><br><br>
        Subject: <input type="text" name="subject"><br><br>
        Attendance (Present/Absent): <input type="text" name="attendance"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
