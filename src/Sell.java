import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalDate;

public class Sell {
    public static String main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/mysql";
        String username = "dastan";
        String password = "Dastanchik1";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Какую валюту вы хотите купить?");
            System.out.println("1 - USD");
            System.out.println("2 - EUR");
            System.out.println("3 - RUB");
            System.out.println("4 - KZT");
            int n = sc.nextInt();
            System.out.println("Сколько");
            double c = sc.nextDouble();
            System.out.println("Уверены?   Yes | No ");
            String s = sc.next();
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
            double count = c * Double.parseDouble(CSVLibrary.CSV(n).replace(']', '0'));
            if (s.equals("Yes")) {
                String sql = "INSERT INTO buy_currency (currency_id, sum_buy_currency, date_buy)" + "VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, n);
                preparedStatement.setDouble(2, c);
                preparedStatement.setDate(3, startDate);
                preparedStatement.executeUpdate();
                preparedStatement.close();

                PreparedStatement st = connection.prepareStatement("UPDATE currency " +
                        "SET sum_currency = sum_currency + ? WHERE currency_id = ? ");
                st.setDouble(1, c);
                st.setInt(2, n);
                st.executeUpdate();
                st.close();

                PreparedStatement st1 = connection.prepareStatement("UPDATE currency " +
                        "SET sum_currency = sum_currency - ? WHERE currency_id = ? ");
                st1.setDouble(1, count);
                st1.setInt(2, 5);
                st1.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return "Done";

    }


}