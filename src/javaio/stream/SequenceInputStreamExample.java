package javaio.stream;

import java.io.FileInputStream;
import java.io.SequenceInputStream;

public class SequenceInputStreamExample {

    public static void main(String[] args) {
        String file1 = AppConstant.BASE_DIR + "/test2.txt";
        String file2 = AppConstant.BASE_DIR + "/buffer_out.txt";
        readAllStream(file1, file2);
    }

    private static void readAllStream(String file1, String file2) {
        try (
                FileInputStream f1 = new FileInputStream(file1);
                FileInputStream f2 = new FileInputStream(file2);
                SequenceInputStream seqInStream = new SequenceInputStream(f1, f2);
                ) {

            StringBuilder sb = new StringBuilder();
            int i;
            while( (i = seqInStream.read()) != -1) {
                sb.append((char) i);
            }
            System.out.println(sb);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
