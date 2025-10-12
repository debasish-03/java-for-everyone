package javaio.stream;

import java.io.FileInputStream;

public class FileInputStreamExample {

    public static void main(String[] args) {
        readPrimitiveData(AppConstant.BASE_DIR + "/test1.txt");
        readStringData(AppConstant.BASE_DIR + "/test2.txt");
    }

    private static void readPrimitiveData(String file) {
        try(FileInputStream in = new FileInputStream(file)) {
            int value = in.read();
            System.out.println("primitive value: "+ value);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void readStringData(String file) {
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] value = in.readAllBytes();
            StringBuilder res = new StringBuilder();
            for (byte b: value) {
                res.append((char) b);
            }
            System.out.println(res);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
