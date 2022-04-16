import java.sql.*;
import java.util.Scanner;

public class Admin {

    public static void add_currency() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DB.main();
            System.out.println("Какую валюту вы хотите пополнить");
            System.out.println("1 - USD");
            System.out.println("2 - EUR");
            System.out.println("3 - RUB");
            System.out.println("4 - KZT");
            System.out.println("5 - KGT");
            int n = sc.nextInt();
            System.out.println("На какую сумму");
            double count = sc.nextDouble();
            assert connection != null;
            PreparedStatement st1 = connection.prepareStatement("UPDATE currency SET sum_currency = sum_currency + ? WHERE currency_id = ? ");
            st1.setDouble(1, count);
            st1.setInt(2, n);
            st1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    public static void show_currency() {
        ;

        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM currency";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String sum = rs.getString(3);
                System.out.println(id + "   " + name + "   " + sum);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
