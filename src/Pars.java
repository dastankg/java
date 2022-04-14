import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Pars {
    public static void main(String[] args) throws Exception {
        TestCSV.main(create());
    }


    public static ArrayList<Double> create() throws Exception {
        URL oracle = new URL("https://www.nbkr.kg/XML/daily.xml");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        ArrayList<Double> arr = new ArrayList<>();

        ;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("<Value>")) {
                int index = inputLine.indexOf("</Value>");
                arr.add(Double.parseDouble(inputLine.substring(7, index).trim().replace(',', '.')));
            }
        }
        return arr;
    }

    private static boolean isDigit(char s) throws NumberFormatException {
        try {
            Integer.parseInt(String.valueOf(s));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main() {
    }


    public static class TestCSV {
        static void main(ArrayList<Double> d) {
            try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

                StringBuilder sb = new StringBuilder();
                sb.append("id");
                sb.append(',');
                sb.append("Name");
                sb.append(',');
                sb.append("Buy");
                sb.append(',');
                sb.append("Sell");
                sb.append('\n');
                String[] s = {"Доллар", "Евро", "Тенге", "Рубль"};
                int c = 0;
                for (Double i : d) {
                    sb.append(c + 1);
                    sb.append(',');
                    sb.append(s[c]);
                    sb.append(',');
                    sb.append(roundAvoid(i));
                    sb.append(',');
                    sb.append(roundAvoid(i*1.03));
                    sb.append('\n');
                    c++;
                }

                writer.write(sb.toString());
                writer.close();

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        public static double roundAvoid(double value) {
            int places = 2;
            double scale = Math.pow(10, places);
            return Math.round(value * scale) / scale;
        }



    }
}
