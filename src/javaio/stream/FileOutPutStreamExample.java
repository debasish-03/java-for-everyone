package javaio.stream;

import java.io.FileOutputStream;

public class FileOutPutStreamExample {

    public static void main(String[] args) {

        // 1. write primitive data
        String  filepath1 = AppConstant.BASE_DIR + "/test1.txt";
        writePrimitiveData(filepath1, 1000);

        // 2. write string data
        String filepath2 = AppConstant.BASE_DIR + "/test2.txt";
        String data = "Welcome to Java for everyone";
        writeStringData(filepath2, data);
    }

    private static void writeStringData(String filepath, String data) {
        try (FileOutputStream out = new FileOutputStream(filepath)) {
            byte[] b = data.getBytes();
            out.write(b);
            out.flush();
            System.out.println("string data written successfully...");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void writePrimitiveData(String filepath, int data) {
        try (FileOutputStream out = new FileOutputStream(filepath);){
            out.write(data);
            out.flush();
            System.out.println("primitive data written successfully...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
