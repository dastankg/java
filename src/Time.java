
import java.util.Calendar;


public class Time {
    public static void main(String[] args) {
    }

    public static String date(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, ((n + 1) * 30)); //minus number would decrement the days
        String y = String.valueOf(cal.get(Calendar.YEAR));
        String m = String.valueOf(cal.get(Calendar.MONTH));
        String d = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        return (y + "-" + m + "-" + d);

    }

    public static String date_now() {
        Calendar cal = Calendar.getInstance();
        String y = String.valueOf(cal.get(Calendar.YEAR));
        String m = String.valueOf(cal.get(Calendar.MONTH));
        String d = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        return (y + "-" + m + "-" + d);

    }
}