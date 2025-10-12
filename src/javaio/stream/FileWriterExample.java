package javaio.stream;

import java.io.FileWriter;

public class FileWriterExample {
    public static void main(String args[]){
        try{
            String file = AppConstant.BASE_DIR + "/test1.txt";
            FileWriter fw=new FileWriter(file);
            fw.write("Welcome to java for everyone.");
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");
    }
}
