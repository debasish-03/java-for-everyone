package filehandling;

import javaio.stream.AppConstant;

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        try {
            File f = new File(AppConstant.BASE_DIR + "/newfile.txt");
            if(f.exists()) {
                System.out.println("File name is: "+ f.getName());
                System.out.println("File path: "+ f.getAbsolutePath());
                System.out.println("Is file writeable: "+ f.canWrite());
                System.out.println("Is file readable: "+ f.canRead());
                System.out.println("File length: "+ f.length());
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
