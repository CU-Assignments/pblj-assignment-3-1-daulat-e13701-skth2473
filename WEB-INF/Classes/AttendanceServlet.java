import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    private Connection conn;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/yourDB", "root", "password"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollNo = request.getParameter("rollNo");
        String subject = request.getParameter("subject");
        String attendance = request.getParameter("attendance");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO attendance (rollNo, subject, attendance) VALUES (?, ?, ?)");
            ps.setString(1, rollNo);
            ps.setString(2, subject);
            ps.setString(3, attendance);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Attendance submitted successfully!</h3>");
            } else {
                out.println("<h3>Failed to submit attendance.</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Error while saving data.</h3>");
        }
    }
}
