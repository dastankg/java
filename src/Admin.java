import java.sql.*;
import java.util.Scanner;
import java.util.Calendar;

public class Admin {

    public static void credit_4(int n) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Что было дальше");
        String decide = sc.next();
        if (decide.equals("Yes")) {
            try {
                Connection connection = DB.main();
                String sql = "INSERT INTO client (name_client) SELECT name_new_client FROM new_client WHERE new_client_id =" + n;
                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();


                Connection connection2 = DB.main();
                String sql2 = "INSERT INTO client_data (age, phone_number, city, personal_number, job, salary_client) " +
                        "SELECT age, phone_number, city, personal_number, job, salary_client FROM new_client_data WHERE new_client_id = " + n;
                assert connection2 != null;
                PreparedStatement preparedStatement2 = connection2.prepareStatement(sql2);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();

                ;
                Connection connection4 = DB.main();
                String sql4 = "SELECT * FROM new_client_data_credit WHERE new_client_id = " + n;
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
                java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

                Connection connection5 = DB.main();
                String sql5 = "INSERT INTO client_status (sum_credit, sum_pay, sum_pay_credit, date_start, date_end, percent, " +
                        "status_credit) VALUE (?, ?, ?, ?, ?, ?, ?) ";
                assert connection5 != null;
                PreparedStatement preparedStatement5 = connection5.prepareStatement(sql5);
                preparedStatement5.setDouble(1, Double.parseDouble(s[2]));
                preparedStatement5.setDouble(2, 0.0);
                preparedStatement5.setDouble(3, Double.parseDouble(s[4]));
                preparedStatement5.setDate(4, startDate);
                preparedStatement5.setDate(5, Date.valueOf(Time1.date(Integer.parseInt(s[1]))));
                preparedStatement5.setDouble(6, Double.parseDouble(s[3]));
                preparedStatement5.setBoolean(7, false);
                preparedStatement5.executeUpdate();
                preparedStatement5.close();


                Connection connection6 = DB.main();
                String sql6 = "DELETE FROM new_client_data_credit WHERE new_client_id = " + n;
                assert connection6 != null;
                PreparedStatement preparedStatement6 = connection6.prepareStatement(sql6);
                preparedStatement6.executeUpdate();
                preparedStatement6.close();


                Connection connection3 = DB.main();
                String sql3 = "DELETE FROM new_client_data WHERE new_client_id = " + n;
                assert connection3 != null;
                PreparedStatement preparedStatement3 = connection3.prepareStatement(sql3);
                preparedStatement3.executeUpdate();
                preparedStatement3.close();


                Connection connection1 = DB.main();
                String sql1 = "DELETE FROM new_client WHERE new_client_id = " + n;
                assert connection1 != null;
                PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1);
                preparedStatement1.executeUpdate();
                preparedStatement1.close();

                System.out.println("Done");

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public static void credit() {

        try {
            Connection connection = DB.main();
            String sql = "SELECT new_client_id, name_new_client FROM new_client";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Список клиентов");
            System.out.println("id   " + "name   ");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                System.out.println(id + "   " + name);
            }
            credit_1();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void credit_2(int n) {
        try {
            Connection connection = DB.main();
            String sql = "SELECT new_client_id, month_credit, sum_credit, percent, sum_after_percent FROM new_client_data_credit " +
                    "inner JOIN new_client using (new_client_id) WHERE new_client_id = " + n;

            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String month = rs.getString(2);
                String sum_credit = rs.getString(3);
                String percent = rs.getString(4);
                String sum_after_percent = rs.getString(5);
                System.out.println(id + "   " + month + "   " + sum_credit + "  " + percent + "  " + sum_after_percent);
            }
            credit_4(n);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void credit_1() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Какую заявку хотите посмотреть");
            int n = sc.nextInt();
            Connection connection = DB.main();
            String sql1 = "SELECT new_client_id, name_new_client, age, phone_number, city, personal_number, job, " +
                    "salary_client FROM new_client_data INNER JOIN new_client USING (new_client_id) WHERE new_client_id = " + n;
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql1);
            System.out.println("id   " + "name   " + "   age     " + "number   " + "    city   " + "INN   " + "     job   " + "salary");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String phone_number = rs.getString(4);
                String city = rs.getString(5);
                String INN = rs.getString(6);
                String job = rs.getString(7);
                String salary = rs.getString(8);
                System.out.println(id + "   " + name + "   " + "   " + age + "      " + phone_number + "   " + city + "    " + INN + "  " + job + "     " + salary);
                credit_2(n);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    public static void buy_history() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT buy_currency_id, name_currency, sum_buy_currency, date_buy FROM buy_currency " +
                    "INNER JOIN currency c on buy_currency.currency_id = c.currency_id";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("ID" + "  " + "Name" + "  " + "Sum      Date");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String sum = rs.getString(3);
                String date = rs.getString(4);
                System.out.println(id + "   " + name + "   " + sum + "   " + date);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void sell_history() {
        try {
            Connection connection = DB.main();
            String sql = "SELECT sell_currency_id, name_currency, sum_sell_currency, date_buy FROM sell_currency " +
                    "INNER JOIN currency c on sell_currency.currency_id = c.currency_id";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("ID" + "  " + "Name" + "  " + "Sum      Date");
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String sum = rs.getString(3);
                String date = rs.getString(4);
                System.out.println(id + "   " + name + "   " + sum + "   " + date);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void get_currency() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DB.main();
            System.out.println("Какую валюту вы хотите взять");
            System.out.println("1 - USD");
            System.out.println("2 - EUR");
            System.out.println("3 - RUB");
            System.out.println("4 - KZT");
            System.out.println("5 - KGT");
            int n = sc.nextInt();
            System.out.println("На какую сумму");
            double count = sc.nextDouble();
            assert connection != null;
            PreparedStatement st1 = connection.prepareStatement("UPDATE currency SET sum_currency = sum_currency - ? WHERE currency_id = ? ");
            st1.setDouble(1, count);
            st1.setInt(2, n);
            st1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Done");
    }

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
        System.out.println("Done");


    }

    public static void show_currency() {


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
