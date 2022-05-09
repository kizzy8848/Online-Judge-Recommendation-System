package Recommender.data;

import java.io.File;
import java.io.FileOutputStream;

public class WriteTxt {
    public String path;

    public void writeToTXT(String str){
        FileOutputStream o = null;

        byte[] buff = new byte[]{};
        try{
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            buff=str.getBytes();
            o=new FileOutputStream(file,true);
            o.write(buff);
            o.flush();
            o.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public WriteTxt(String path){
        this.path=path;
    }
}
