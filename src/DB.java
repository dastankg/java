
import java.sql.*;
public class DB {
    public static Connection main() {
        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "dastan";
        String password = "Dastanchik1";

        try {
            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println(e);
        }


        return null;
    }

}

