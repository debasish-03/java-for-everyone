package filehandling;

import javaio.stream.AppConstant;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvReaderExample {
    public static void main(String[] args) {
        String csvFile = AppConstant.BASE_DIR + "/data.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
