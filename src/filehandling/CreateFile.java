package filehandling;

import javaio.stream.AppConstant;

import java.io.File;

public class CreateFile {
    public static void main(String[] args) {
        try {
            File f = new File(AppConstant.BASE_DIR + "/newfile.txt");
            if(f.createNewFile()) {
                System.out.println("File "+ f.getName() + " is created successfully.");
            } else {
                System.out.println("File "+ f.getName() + " already exists.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
