import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class CSVLibrary {
    public static void main(String[] args) {
        CSVLibrary csvLibrary = new CSVLibrary();
        csvLibrary.readCSVFile();
    }

    public static void main() {
        CSVLibrary csvLibrary = new CSVLibrary();
        csvLibrary.readCSVFile();
    }

    public static String CSV(int n) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("test.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> allRows = null;
        try {
            assert reader != null;
            allRows = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        assert allRows != null;
        String[] usd = Arrays.toString(allRows.get(1)).split(",");
        String[] eur = Arrays.toString(allRows.get(2)).split(",");
        String[] rus = Arrays.toString(allRows.get(3)).split(",");
        String[] kzt = Arrays.toString(allRows.get(4)).split(",");
        if (n == 1) {
            return usd[3];
        } else if (n == 2) {
            return eur[3];
        } else if (n == 3) {

            return kzt[3];
        } else {
            return rus[3];
        }

    }

    public void readCSVFile() {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("test.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> allRows = null;
        try {
            assert reader != null;
            allRows = reader.readAll();
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        assert allRows != null;
        int c = 0;
        Date date = new Date();
        System.out.println(date);
        System.out.println();

        for (String[] row : allRows) {
            for (String i : row) {
                if (c < 1) {
                    System.out.print(i + "     ");
                    c++;
                } else if (i.equals("Buy")) {
                    System.out.print(i + "        ");
                } else if (i.equals("Доллар")) {
                    System.out.print(i + "    ");
                } else {
                    System.out.print(i + "      ");
                }
            }
            System.out.println();
        }
    }
}
