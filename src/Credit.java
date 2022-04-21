import java.sql.*;
import java.util.Scanner;

public class Credit {
    public static void main() {
        menu();
    }
    public static void menu(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать");
        System.out.println("У нас кредиты выдаются от 10к до 100к сом");
        System.out.println("1 - месяц = 5%");
        System.out.println("2 - месяц = 7.5%");
        System.out.println("3 - месяц = 10%");
        System.out.println("4 - месяц = 12.5%");
        System.out.println("5 - месяц = 15%");
        System.out.println("6 - месяц = 17.5%");
        System.out.println("Выберите сумму которую хоитте получить");
        double d = sc.nextDouble();
        System.out.println("Выберите месяц");
        int n = sc.nextInt();
        double[] l = {5, 7.5, 10, 12.5, 15, 17.5};
        System.out.println("Вам придется вернуть");
        System.out.println((d * (l[n - 1] / 100)) + d);
        System.out.println("Cогласны");
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        if (s.equals("Yes")) {
            System.out.println("Name");
            sb.append(sc.next()).append(" ");
            System.out.println("Last");
            sb.append(sc.next());
            System.out.println("Сколько вам лет");
            int age = sc.nextInt();
            System.out.println("Phone");
            String number = sc.next();
            System.out.println("City");
            String city = sc.next();
            System.out.println("INN");
            String p_num = sc.next();
            System.out.println("Job");
            String j = sc.next();
            boolean job = true;
            if (j.equals("Yes")) {
                job = true;
            } else {
                job = false;
            }
            System.out.println("Salary");
            double salary = sc.nextDouble();
            if (check(p_num)) {
                if (check_good(p_num)) {
                    System.out.println("У вас хорошая кредитная история вы можете забрать деньги сразу");
                } else {
                    System.out.println("Ваша заявка принята и в скором времени мы дадим ответ");
                    write_date(sb.toString(), age, number, city, p_num, job, salary, n, d);

                }
            }
            Menu.main();
        }

    }

    public static void write_date(String name, int age, String n, String c, String p_n, boolean job, double salary, int m, double s) {
        try {
            Connection connection = DB.main();
            String sql = "INSERT INTO new_client (name_new_client)" +
                    "VALUES (?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
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
