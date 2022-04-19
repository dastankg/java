import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class train {
    public static void main(String[] args) {
        String path = "demo.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            ArrayList<String> arr= new ArrayList<>();
            for (String[] row : reader) {
                arr.add(Arrays.toString(row));
            }
            System.out.println(arr);
            System.out.println(arr.get(1));
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Finished reading a CSV");
        }
    }




//        public static void addDays() {
//            Calendar cal = Calendar.getInstance();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            cal.add(Calendar.DATE, 5 * 30); //minus number would decrement the days
//            System.out.println(cal.getTime());
//            System.out.println(cal.get(Calendar.YEAR));
//            System.out.println(cal.get(Calendar.MONTH));
//            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
//        }

    }


