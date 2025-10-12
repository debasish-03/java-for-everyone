package javaio.stream;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamExample {
    public static void main(String[] args) {
        byte[] buf = { 35, 36, 37, 38 };
        try (ByteArrayInputStream byt = new ByteArrayInputStream(buf);) {
            int k = 0;
            while ((k = byt.read()) != -1) {
                char ch = (char) k;
                System.out.println("ASCII value of Character is:" + k + "; Special character is: " + ch);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
