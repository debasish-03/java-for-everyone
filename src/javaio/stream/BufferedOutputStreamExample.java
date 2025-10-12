package javaio.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class BufferedOutputStreamExample {
    public static void main(String[] args) {
        String file = AppConstant.BASE_DIR + "/buffer_out.txt";
        String data = "I am a Senior Software Engineer. " +
                "I have good hands on experiance with Java developement.";
        writeData(file, data);
        readData(file);
    }

    private static void writeData(String file, String data) {
        try (
                FileOutputStream fileOut = new FileOutputStream(file);
                BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
                ) {
            byte[] b = data.getBytes(StandardCharsets.UTF_8);
            bufferOut.write(b);
            bufferOut.flush();
            System.out.println("success....");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void readData(String file) {
        try (
                FileInputStream fileIn = new FileInputStream(file);
                BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
                ) {

              StringBuilder sb = new StringBuilder();
//            byte[] byteArr = bufferIn.readAllBytes();
//            StringBuilder sb = new StringBuilder();
//            for (byte b: byteArr) {
//                sb.append((char) b);
//            }

            int i;
            while((i = bufferIn.read()) != -1) {
                sb.append((char) i);
            }
            System.out.println(sb);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
