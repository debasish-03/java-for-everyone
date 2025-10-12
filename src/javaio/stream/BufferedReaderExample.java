package javaio.stream;

import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedReaderExample {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader(AppConstant.BASE_DIR + "/test1.txt");
        BufferedReader buffer = new BufferedReader(reader);
        int i;
        while((i = buffer.read()) != -1) {
            System.out.print((char) i);
        }
        buffer.close();
    }
}
