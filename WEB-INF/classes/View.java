import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/View")
public class View extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<Data> dataList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student",
                    "postgres", "Rajdr039*");
            String sql = "SELECT * FROM details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String className = resultSet.getString("class");
                String seatNo = resultSet.getString("seatno");
                String totalMarks = resultSet.getString("totalmarks");
                String percentage = resultSet.getString("percentage");

                Data data = new Data(name, className, seatNo, totalMarks, percentage);
                dataList.add(data);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            jsonBuilder.append("{")
                    .append("\"name\":\"").append(data.getName()).append("\",")
                    .append("\"class\":\"").append(data.getClassName()).append("\",")
                    .append("\"seatno\":\"").append(data.getSeatNo()).append("\",")
                    .append("\"totalmarks\":\"").append(data.getTotalMarks()).append("\",")
                    .append("\"percentage\":\"").append(data.getPercentage()).append("\"")
                    .append("}");
            if (i < dataList.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        String json = jsonBuilder.toString();

        out.println(json);
    }

    private static class Data {
        private String name;
        private String className;
        private String seatNo;
        private String totalMarks;
        private String percentage;

        public Data(String name, String className, String seatNo, String totalMarks, String percentage) {
            this.name = name;
            this.className = className;
            this.seatNo = seatNo;
            this.totalMarks = totalMarks;
            this.percentage = percentage;
        }

        public String getName() {
            return name;
        }

        public String getClassName() {
            return className;
        }

        public String getSeatNo() {
            return seatNo;
        }

        public String getTotalMarks() {
            return totalMarks;
        }

        public String getPercentage() {
            return percentage;
        }
    }
}
