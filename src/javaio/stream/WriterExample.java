package javaio.stream;

import java.io.FileWriter;
import java.io.Writer;

public class WriterExample {
    public static void main(String[] args) {
        try (Writer w = new FileWriter(AppConstant.BASE_DIR + "/test1.txt");) {
            String data = "Writer is an abstract class";
            w.write(data);
            System.out.println("done");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
