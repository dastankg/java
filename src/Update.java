
import java.sql.*;
import java.util.Date;

public class Update {
    public static void main(String[] args) {
        black_list();
    }

    public static void add_good(int n) {
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO good_client SELECT client_id, name_client, personal_number FROM client_data " +
                    "INNER JOIN client USING (client_id) WHERE client_id =" + n;
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void add_black(int n) {
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO bad_client SELECT client_id, name_client, personal_number FROM client_data " +
                    "INNER JOIN client USING (client_id) WHERE client_id =" + n;
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void black_list() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM client_status";

            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String s = rs.getString(6);
                Date date1 = new Date(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]),
                        Integer.parseInt(s.split("-")[2]));
                Date date2 = new Date(Integer.parseInt(Time.date_now().split("-")[0]),
                        Integer.parseInt(Time.date_now().split("-")[1]), Integer.parseInt(Time.date_now().split("-")[2]));
                if (date1.compareTo(date2) < 0) {
                    double d = rs.getDouble(3);
                    double b = rs.getDouble(4);
                    int n = rs.getInt(1);
                    if (d < b) {
                        add_black(n);
                    } else {
                        add_good(n);
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
