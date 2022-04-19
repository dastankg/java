import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Pay {
    public static void main(String[] args) {


    }

    public static void pay() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Напишите ваш INN");
        String inn = sc.next();
        System.out.println("Напишите сумму");
        double d = sc.nextDouble();
        new_data(d, inn);
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
}
