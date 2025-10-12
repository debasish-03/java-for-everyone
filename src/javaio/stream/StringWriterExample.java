package javaio.stream;

import java.io.*;

public class StringWriterExample {
    public static void main(String[] args) throws Exception {
        StringWriter writer = new StringWriter();

        FileInputStream fileInput = new FileInputStream(AppConstant.BASE_DIR + "/test1.txt");
        BufferedInputStream buffer = new BufferedInputStream(fileInput);

        int i;
        while( (i = buffer.read()) != -1) {
            writer.write(i);
        }

        System.out.println(writer.toString());

    }
}
