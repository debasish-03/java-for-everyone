package javaio.stream;

import java.io.FileReader;

public class FileReaderExample {
    public static void main(String args[])throws Exception{
        String file = AppConstant.BASE_DIR + "/test1.txt";
        FileReader fr=new FileReader(file);
        int i;
        while((i=fr.read())!=-1)
            System.out.print((char)i);
        fr.close();
    }
}
