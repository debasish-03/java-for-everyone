package filehandling;

import javaio.stream.AppConstant;

import java.io.File;

public class DeleteFile {
    public static void main(String[] args) {
        try {
            File f = new File(AppConstant.BASE_DIR + "/newfile.txt");
            if(f.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
