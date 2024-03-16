import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


                try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {

            
        String totalMarks = request.getParameter("totalMarks");

        int totalMark = Integer.parseInt(totalMarks);

        double percentage = (double) totalMark / 500 * 100;

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student","postgres", "Rajdr039*");
            System.out.println("connection successful");
              
            PreparedStatement st = conn.prepareStatement("insert into details(NAME,CLASS,SEATNO,TOTALMARKS,PERCENTAGE) values(?,?,?,?,?)");
            st.setString(1, request.getParameter("name"));
            st.setString(2, request.getParameter("class"));
            st.setString(3, request.getParameter("seatNo"));
            st.setInt(4, totalMark);
            st.setDouble(5, percentage);
            st.executeUpdate();
  
           
            st.close();
            conn.close();
        response.sendRedirect("index.jsp");
        } catch (Exception e) {
                System.out.println(e);
        }

    }
}
