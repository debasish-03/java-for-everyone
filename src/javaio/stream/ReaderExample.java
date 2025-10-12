package javaio.stream;


import java.io.FileReader;
import java.io.Reader;

public class ReaderExample {

    public static void main(String[] args) {
        try (Reader reader = new FileReader(AppConstant.BASE_DIR + "/test1.txt");) {
            StringBuilder sb = new StringBuilder();

            int i;
            while( (i = reader.read()) != -1) {
                sb.append((char) i);
            }
            System.out.println(sb);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
