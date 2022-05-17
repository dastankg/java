import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class Credit {
    public static void main() {
        menu();
    }

    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать");
        System.out.println("У нас кредиты выдаются от 10к до 100к сом");
        System.out.println("1 - месяц = 10%");
        System.out.println("2 - месяца = 12%");
        System.out.println("3 - месяца = 15%");
        System.out.println("4 - месяца = 20%");
        System.out.println("5 - месяцев = 25%");
        System.out.println("6 - месяцев = 30%");
        System.out.println("Напишите сумму которую хотите получить");
        System.out.print("--> ");
        double d = sc.nextDouble();
        System.out.println("Выберите месяц");
        System.out.print("--> ");
        int n = sc.nextInt();
        double[] l = {10, 12, 15, 20, 25, 30};
        System.out.println("Вам придется вернуть");
        System.out.println((d * (l[n - 1] / 100)) + d);
        System.out.println("Хотите продолжить?");
        System.out.println("Yes | No");
        System.out.print("--> ");
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        String[] time_data = new String[2];
        if (s.toLowerCase(Locale.ROOT).equals("yes")) {
            System.out.println("Name");
            System.out.print("--> ");
            sb.append(sc.next()).append(" ");
            System.out.println("Last");
            System.out.print("--> ");
            sb.append(sc.next());
            System.out.println("Сколько вам лет?");
            System.out.print("--> ");
            int age = sc.nextInt();
            if (age < 18) {
                System.out.println("Извините но еще нет 18!");
            } else {
                while (true) {
                    System.out.println("Телефонный номер");
                    System.out.print("--> ");
                    String number = sc.next();
                    if (number.length() != 9) {
                        System.out.println("Не верный формат напишите еще раз");
                    } else {
                        time_data[0] = number;
                        break;
                    }
                }

                System.out.println("City");
                System.out.print("--> ");
                String city = sc.next();
                boolean flag = true;
                boolean flag1 = true;
                while (true) {
                    System.out.println("INN");
                    System.out.print("--> ");
                    String p_num = sc.next();
                    if (p_num.length() != 14) {
                        System.out.println("Не верный формат напишите еще раз");
                    } else {
                        if (check(p_num)) {
                            if (check_good(p_num)) {
                                System.out.println("У вас хорошая кредитная история вы можете забрать деньги сразу");
                                flag1 = false;
                                break;
                            } else {
                                time_data[1] = p_num;
                                break;
                            }
                        } else {
                            System.out.println("У вас плохая кредитная история вы не можете получить взайм!");
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    System.out.println("Job \n Yes | No");
                    System.out.print("--> ");
                    String j = sc.next();
                    boolean job = j.toLowerCase(Locale.ROOT).equals("yes");

                    System.out.println("Salary");
                    System.out.print("--> ");
                    double salary = sc.nextDouble();
                    if (flag1) {
                        System.out.println("Ваша заявка принята и в скором времени мы дадим ответ");
                        write_date(sb.toString(), age, time_data[0], city, time_data[1], job, salary, n, d, 0);

                    } else {
                        write_date(sb.toString(), age, time_data[0], city, time_data[1], job, salary, n, d, 1);
                    }
                }

            }
            Menu.main();
        }
    }


    public static void write_date(String name, int age, String n, String c, String p_n, boolean job, double salary, int m, double s, int q) {
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO new_client (name_new_client, status)" +
                    "VALUES (?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, q);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO new_client_data (age, phone_number, city, personal_number, job, salary_client)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, n);
            preparedStatement.setString(3, c);
            preparedStatement.setString(4, p_n);
            preparedStatement.setBoolean(5, job);
            preparedStatement.setDouble(6, salary);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        try {
            double[] l = {5, 7.5, 10, 12.5, 15, 17.5};
            Connection connection = DB.main();
            String sql = "INSERT INTO new_client_data_credit (month_credit, sum_credit, percent, sum_after_percent)" +
                    "VALUES (?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, m);
            preparedStatement.setDouble(2, s);
            preparedStatement.setDouble(3, l[m - 1]);
            preparedStatement.setDouble(4, (s * (l[m - 1] / 100)) + s);
            ;
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println(e);
        }


    }

    public static boolean check(String j) {
        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM bad_client";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String c = rs.getString(3);
                String i = rs.getString(3);
                if (j.equals(i)) {
                    return false;
                }


            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    public static boolean check_good(String j) {
        try {
            Connection connection = DB.main();
            String sql = "SELECT * FROM good_client";
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String c = rs.getString(3);
                String i = rs.getString(3);
                if (j.equals(i)) {
                    return true;
                }


            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


}
