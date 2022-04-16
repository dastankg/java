import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;


public class Buy {
    public static String main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Какую валюту вы хотите купить?");
        System.out.println("1 - USD");
        System.out.println("2 - EUR");
        System.out.println("3 - RUB");
        System.out.println("4 - KZT");
        int n = sc.nextInt();
        System.out.println("Сколько");
        double c = sc.nextDouble();
        double count = c * Double.parseDouble(CSVLibrary.CSV(n).replace(']', '0'));
        System.out.println("Вам придется заплатить " + count + " som");
        System.out.println("Уверены?   Yes | No ");
        String s = sc.next();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        if (check(n, count)) {
            try {


                Connection connection = DB.main();
                if (s.equals("Yes")) {
                    String sql = "INSERT INTO sell_currency (currency_id, sum_sell_currency, date_buy)" +
                            "VALUES (?, ?, ?)";
                    assert connection != null;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, n);
                    preparedStatement.setDouble(2, c);
                    preparedStatement.setDate(3, startDate);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();

                    PreparedStatement st = connection.prepareStatement("UPDATE currency " +
                            "SET sum_currency = sum_currency - ? WHERE currency_id = ? ");
                    st.setDouble(1, c);
                    st.setInt(2, n);
                    st.executeUpdate();
                    st.close();

                    PreparedStatement st1 = connection.prepareStatement("UPDATE currency " +
                            "SET sum_currency = sum_currency + ? WHERE currency_id = ? ");
                    st1.setDouble(1, count);
                    st1.setInt(2, 5);
                    st1.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else {
            return ("Извините но мы не можем сделать операцию  на сумму " + count);
        }

        return "Done";

    }


    public static boolean check(int n, double d) {


        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM currency";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                double c = rs.getDouble(3);
                if (id == n) {
                    return (d <= c);
                }


            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }


}
