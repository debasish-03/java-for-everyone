package javaio.stream;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class BufferedWriterExample {
    public static void main(String[] args) throws Exception {
        FileWriter writer = new FileWriter(AppConstant.BASE_DIR + "/test1.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write("Welcome to java for everyone.");
        buffer.close();
        System.out.println("Success");
    }
}
