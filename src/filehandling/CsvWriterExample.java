package filehandling;

import javaio.stream.AppConstant;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CsvWriterExample {
    public static void main(String[] args) {
        String csvFile = AppConstant.BASE_DIR + "/data.csv";

        // Sample data to write
        String[] header = {"Name", "Age", "Country"};
        String[][] data = {
                {"Alice", "30", "India"},
                {"Bob", "25", "USA"},
                {"Charlie", "35", "Canada"}
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile));) {
            bw.write(String.join(",", header));
            bw.newLine();

            for(String[] row: data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }

            System.out.println("CSV file written successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
