package javaio.stream;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class ByteArrayOutputStreamExample {
    public static void main(String[] args) {

        String file1 = AppConstant.BASE_DIR + "/test1.txt";
        String file2 = AppConstant.BASE_DIR + "/test2.txt";
        String data = "Hello, I am from future!";

        writeAll(file1, file2, data);

    }

    private static void writeAll(String file1, String file2, String data) {
        try (
                FileOutputStream f1 = new FileOutputStream(file1);
                FileOutputStream f2 = new FileOutputStream(file2);
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                ) {

            byteOut.writeBytes(data.getBytes());
            byteOut.writeTo(f1);
            byteOut.writeTo(f2);

            System.out.println("success...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
