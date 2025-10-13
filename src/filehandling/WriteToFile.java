package filehandling;

import javaio.stream.AppConstant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class WriteToFile {
    public static void main(String[] args) {
        String file = AppConstant.BASE_DIR + "/newfile.txt";
        String data = "I am using `FileWriter` for writing data to file. ";
        writeDataToFile(file, data);
        System.out.println("###----------------------------###");
        readDataFromFile(file);
        System.out.println("###----------------------------###");
        readDataFromFileUsingScanner(file);
    }

    private static void writeDataToFile(String file, String data) {
        try (FileWriter writer = new FileWriter(file);) {
            writer.write(data);
            System.out.println("Data is written successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void readDataFromFile(String file) {
        try (FileReader r = new FileReader(file);) {
            int i;
            while ((i = r.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void readDataFromFileUsingScanner(String file) {
        try {
            File f = new File(file);
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                System.out.println(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
