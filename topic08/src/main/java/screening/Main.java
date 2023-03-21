package screening;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        try {
            String query = "SELECT * FROM Employee WHERE ID=110";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println(rs.getInt("ID"));
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("dsds");
    }

}
