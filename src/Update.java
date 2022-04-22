import java.sql.*;
import java.time.LocalDate;


public class Update {
    public static void main(String[] args) {

    }

    public static void delete(int n) {
        try {
            Connection connection = DB.main();
            String sql = "DELETE FROM client_status WHERE client_id = " + n;
            String sql1 = "DELETE FROM client_data WHERE client_id = " + n;
            String sql2 = "DELETE FROM client WHERE client_id = " + n;
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();


        } catch (SQLException e) {
            System.out.println(e);
        }
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
                LocalDate date = LocalDate.parse(s);
                String c = Time1.date_now();
                LocalDate date1 = LocalDate.parse(c);


                if (date.compareTo(date1) < 0) {
                    double d = rs.getDouble(3);
                    double b = rs.getDouble(4);
                    int n = rs.getInt(1);
                    if (d < b) {
                        add_black(n);
                        delete(n);
                    } else {
                        add_good(n);
                        delete(n);
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
