import java.sql.*;
import java.util.Scanner;

public class Auth {
    public static void main(String[] args) {
        Auth.main();
    }

    public static void main() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {

            try {
                Connection connection = DB.main();
                String sql = "SELECT * FROM admin";
                assert connection != null;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                System.out.println("Ведите логин");
                String log = sc.next();
                System.out.println("Ведите пароль");
                String password_admin = sc.next();
                while (rs.next()) {
                    String log_correct = rs.getString(4);
                    String pass_correct = rs.getString(5);
                    if (log.equals(log_correct) && password_admin.equals(pass_correct)) {
                        flag = true;
                        break;
                    }

                }
                if (!flag)
                    System.out.println("Error");

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (flag) {
            Menu.main_admin();
        }


    }

}
