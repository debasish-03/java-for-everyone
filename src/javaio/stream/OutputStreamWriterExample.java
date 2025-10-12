package javaio.stream;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class OutputStreamWriterExample {
    public static void main(String[] args) {

        try {
            OutputStream outputStream = new FileOutputStream(AppConstant.BASE_DIR + "/output.txt");
            Writer outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write("Hello World");

            outputStreamWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
