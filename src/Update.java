import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;


public class Update {
    public static void main(String[] args) {
        black_list();
        good_list();
        credit_4();
    }


    public static void credit_4() {
        int st = 0;
            try {
                Connection connection = DB.main();
                String sql = "SELECT new_client_id FROM new_client where status = 1 LIMIT 1";
                assert connection != null;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    st = rs.getInt(1);
                }

            } catch (SQLException e) {
                System.out.println(e);
            }

        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO client (name_client) SELECT name_new_client FROM new_client WHERE new_client_id =" + st;
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();


            Connection connection2 = DB.main();
            String sql2 = "INSERT INTO client_data (age, phone_number, city, personal_number, job, salary_client) " +
                    "SELECT age, phone_number, city, personal_number, job, salary_client FROM new_client_data WHERE new_client_id = " + st;
            assert connection2 != null;
            PreparedStatement preparedStatement2 = connection2.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();


            Connection connection4 = DB.main();
            String sql4 = "SELECT * FROM new_client_data_credit WHERE new_client_id = " + st;
            assert connection4 != null;
            Statement statement = connection4.createStatement();
            ResultSet rs = statement.executeQuery(sql4);
            String[] s = new String[5];
            while (rs.next()) {
                s[0] = rs.getString(1);
                s[1] = rs.getString(2);
                s[2] = rs.getString(3);
                s[3] = rs.getString(4);
                s[4] = rs.getString(5);

            }


            Calendar calendar = Calendar.getInstance();
            Date startDate = new Date(calendar.getTime().getTime());

            Connection connection5 = DB.main();
            String sql5 = "INSERT INTO client_status (sum_credit, sum_pay, sum_pay_credit, date_start, date_end, percent) VALUE (?, ?, ?, ?, ?, ?) ";
            assert connection5 != null;
            PreparedStatement preparedStatement5 = connection5.prepareStatement(sql5);
            preparedStatement5.setDouble(1, Double.parseDouble(s[2]));
            preparedStatement5.setDouble(2, 0.0);
            preparedStatement5.setDouble(3, Double.parseDouble(s[4]));
            preparedStatement5.setDate(4, startDate);
            preparedStatement5.setDate(5, Date.valueOf(Time1.date(Integer.parseInt(s[1]))));
            preparedStatement5.setDouble(6, Double.parseDouble(s[3]));
            preparedStatement5.executeUpdate();
            preparedStatement5.close();


            Connection connection6 = DB.main();
            String sql6 = "DELETE FROM new_client_data_credit WHERE new_client_id = " + st;
            assert connection6 != null;
            PreparedStatement preparedStatement6 = connection6.prepareStatement(sql6);
            preparedStatement6.executeUpdate();
            preparedStatement6.close();


            Connection connection3 = DB.main();
            String sql3 = "DELETE FROM new_client_data WHERE new_client_id = " + st;
            assert connection3 != null;
            PreparedStatement preparedStatement3 = connection3.prepareStatement(sql3);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();


            Connection connection1 = DB.main();
            String sql1 = "DELETE FROM new_client WHERE new_client_id = " + st;
            assert connection1 != null;
            PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();


            Connection connection7 = DB.main();
            String sql7 = "DELETE FROM good_client WHERE good_client = " + st;
            PreparedStatement preparedStatement7 = connection1.prepareStatement(sql1);
            preparedStatement7.executeUpdate();
            preparedStatement7.close();

            System.out.println("Done");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void delete(int n) {
        try {
            Connection connection = DB.main();
            String sql = "DELETE FROM client_status WHERE client_id = " + n;
            String sql1 = "DELETE FROM client_data WHERE client_id = " + n;
            String sql2 = "DELETE FROM credit_history WHERE client_id = " + n;
            String sql3 = "DELETE FROM client WHERE client_id = " + n;
            assert connection != null;

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();

            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();


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
            delete(n);


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

    public static void good_list() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT client_id FROM client_status WHERE sum_pay > client_status.sum_pay_credit";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
               delete(rs.getInt(1));

            }
        } catch (SQLException e) {
            System.out.println(e);

        }
    }
}
