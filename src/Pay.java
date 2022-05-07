import java.util.Calendar;
import java.sql.*;
import java.util.Scanner;

public class Pay {
    public static void main(String[] args) {


    }

    public static void pay() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Напишите ваш INN");
            String inn = sc.next();
            System.out.println("Напишите сумму");
            if (check_inn(inn)) {
                double d = sc.nextDouble();
                int n = 1;
                try {
                    Connection connection = DB.main();
                    String sql = "SELECT client_id,  personal_number FROM client_data WHERE personal_number = " + inn;
                    assert connection != null;
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);
                    while (rs.next()) {
                        n = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                new_data(d, inn);
                history_pay(n, d);
                break;
            } else {
                System.out.println("Не верные данные повторите еще раз");
            }


        }
    }

    public static void new_data(double n, String inn) {
        try {
            Connection connection = DB.main();
            assert connection != null;
            PreparedStatement st = connection.prepareStatement("UPDATE client_status SET sum_pay = sum_pay + ? " +
                    "WHERE client_id = (SELECT client_data.client_id FROM client_data WHERE personal_number = ?)");
            st.setDouble(1, n);
            st.setString(2, inn);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    public static void history_pay(int n, double d) {
        try {
            Connection connection = DB.main();
            assert connection != null;
            PreparedStatement st = connection.prepareStatement("INSERT INTO credit_history (client_id, date_pay, sum_pay) " +
                    "value (?, ?, ?)");

            st.setDouble(1, n);

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            st.setDate(2, startDate);
            st.setDouble(3, d);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static boolean check_inn(String s) {
        try {
            Connection connection = DB.main();
            assert connection != null;
            String sql = "SELECT personal_number FROM client_data";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (s.equals(rs.getString(1))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}

