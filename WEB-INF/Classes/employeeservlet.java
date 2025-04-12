import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String empId = request.getParameter("empId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            if (empId != null && !empId.isEmpty()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE id = ?");
                ps.setInt(1, Integer.parseInt(empId));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("Employee Found:<br>");
                    out.println("ID: " + rs.getInt("id") + "<br>");
                    out.println("Name: " + rs.getString("name") + "<br>");
                    out.println("Department: " + rs.getString("department") + "<br>");
                } else {
                    out.println("No employee found with ID: " + empId);
                }
            } else {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

                out.println("<h3>All Employees</h3>");
                while (rs.next()) {
                    out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + "<br>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Database error.");
        }
    }
}
