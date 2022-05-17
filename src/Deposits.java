import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;

public class Deposits {

    public static void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Вы можете открыть депозит по этим пунктам");


        System.out.println("1 - Сроком на 1 год с процентной ставкой 10% в год");
        System.out.println("2 - Сроком на 2 года с процентной ставкой 12% в год");
        System.out.println("3 - Сроком на 3 года с процентной ставкой 15% в год");
        System.out.println("Хотите продолжить?");
        System.out.println("Yes | No\n--> ");
        String s = sc.next().toLowerCase(Locale.ROOT);
        if (s.equals("yes")) {
            System.out.print("Выберите пунк!\n--> ");
            int n = sc.nextInt();
            System.out.print("Name\n--> ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("telephone number");
            String p;
            while (true) {
                System.out.print("--> ");
                p = sc.next();
                if (p.length() != 9) {
                    System.out.println("Не верный формат напишите еще раз");
                } else {
                    break;
                }
            }
            System.out.println("Age");
            int age = sc.nextInt();
            System.out.println("INN");
            String inn;
            while (true) {
                System.out.print("--> ");
                inn = sc.next();
                if (inn.length() != 14) {
                    System.out.println("Не верный формат напишите еще раз");
                } else {
                    break;
                }
            }
            System.out.println("Sum");
            double d = sc.nextDouble();
            int m = 0;
            if (n == 1) {
                m = 10;
            } else if (n == 2) {
                m = 12;
            } else {
                m = 15;
            }


            double per = 0;
            if (n == 1) {
                per = 0.1;
            } else if (n == 2) {
                per = 0.12;
            } else {
                per = 0.15;
            }


            try {
                Connection connection = DB.main();
                String sql = "INSERT INTO deposits (name_client, phone, age, inn, sum, sum_get, date_start, date_end)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                assert connection != null;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, p);
                preparedStatement.setString(3, String.valueOf(age));
                preparedStatement.setString(4, inn);
                preparedStatement.setDouble(5, d);
                for (int i = 1; i <= n; i++) {
                    d = (d * per) + d;
                    System.out.println(d);

                }
                preparedStatement.setDouble(6, roundAvoid(d));
                preparedStatement.setDate(7, Date.valueOf(Time1.date_now()));
                preparedStatement.setDate(8, Date.valueOf(Time1.date(m)));
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public static void print() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM deposits";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.printf("%-15s", "ID");
            System.out.printf("%-15s", "Name");
            System.out.println();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                System.out.printf("%-15s", id);
                System.out.printf("%-15s", name);
                System.out.println();
            }
            System.out.println("Если хотите увидеть подробную инфу о клиенте напишите ID");
            System.out.print("-->");
            int n = sc.nextInt();


            Connection connection1 = DB.main();
            String sql1 = "SELECT * FROM deposits WHERE client_id = "+n;
            assert connection1 != null;
            Statement statement1 = connection1.createStatement();
            ResultSet rs1 = statement1.executeQuery(sql1);
            System.out.printf("%-15s", "ID");
            System.out.printf("%-15s", "Name");
            System.out.printf("%-15s", "Phone");
            System.out.printf("%-15s", "Age");
            System.out.printf("%-15s", "Inn");
            System.out.printf("%-15s", "Sum");
            System.out.printf("%-15s", "Sum_get");
            System.out.printf("%-15s", "Date_start");
            System.out.printf("%-15s", "Date_end");
            System.out.println();
            while (rs1.next()){
                System.out.printf("%-15s", rs1.getString(1));
                System.out.printf("%-15s", rs1.getString(2));
                System.out.printf("%-15s", rs1.getString(3));
                System.out.printf("%-15s", rs1.getString(4));
                System.out.printf("%-15s", rs1.getString(5));
                System.out.printf("%-15s", rs1.getString(6));
                System.out.printf("%-15s", rs1.getString(7));
                System.out.printf("%-15s", rs1.getString(8));
                System.out.printf("%-15s", rs1.getString(9));
            }


        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void get() {
        Scanner sc = new Scanner(System.in);
        String inn = sc.next();
        int n = 0;
        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM deposits where inn = "+inn+"";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                n = rs.getInt(1);
                System.out.println("h1");
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        if (n == 0){
            System.out.println("У нас нету такого клиента");
        } else {
            try {
                System.out.println(n);
                Connection connection = DB.main();
                String sql = "SELECT * FROM deposits where client_id = "+n;
                assert connection != null;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                LocalDate date = LocalDate.parse("2000-04-12");;
                LocalDate date1 = LocalDate.parse("2000-04-12");
                while (rs.next()) {
                    date = LocalDate.parse(rs.getString(9));
                    date1 = LocalDate.parse(Time1.date_now());
                }
                if (date1.compareTo(date) > 0){
                    System.out.println("Вы сможете забрать все свои деньги");
                    System.out.println("Хотите");
                    String s = sc.next();
                    if (s.equals("yes")){
                        try {
                            Connection connection1 = DB.main();
                            String sql1 = "DELETE FROM deposits WHERE client_id = " + n;
                            assert connection1 != null;
                            PreparedStatement preparedStatement = connection1.prepareStatement(sql1);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        } catch (SQLException e){
                            System.out.println(e);
                        }


                    } else {

                    }
                }  System.out.println(date);

            } catch (SQLException e){
                System.out.println(e);
            }
        }



    }

    public static double roundAvoid(double value) {
        int places = 2;
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}

