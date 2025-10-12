package javaio.stream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderExample {
    public static void main(String[] args) {
        try  {
            InputStream stream = new FileInputStream(AppConstant.BASE_DIR + "/output.txt");
            Reader reader = new InputStreamReader(stream);
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }

            reader.close();
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {

        }
    }
}
