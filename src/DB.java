
import java.sql.*;
public class DB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "dastan";
        String password = "Dastanchik1";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("sql");
            String sql = "SELECT * FROM program";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }


    }
}

