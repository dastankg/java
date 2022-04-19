
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class train {
    public static void main(String[] args) {


        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

    }


        public static void addDays() {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
            cal.add(Calendar.DATE, 5 * 30); //minus number would decrement the days
            System.out.println(cal.getTime());
            System.out.println(cal.get(Calendar.YEAR));
            System.out.println(cal.get(Calendar.MONTH));
            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        }

    }

