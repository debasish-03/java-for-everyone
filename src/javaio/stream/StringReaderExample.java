package javaio.stream;

import java.io.IOException;
import java.io.StringReader;

public class StringReaderExample {
    public static void main(String[] args) throws IOException {
        String data = "Hello, Welcome to Java for everyone!";
        StringReader reader = new StringReader(data);
        int i;
        while( (i = reader.read()) != -1) {
            System.out.print((char) i);
        }
        reader.close();
    }
}
