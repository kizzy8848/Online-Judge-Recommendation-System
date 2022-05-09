package Recommender.data;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * BufferedOutputStream:缓冲输出流
 * FileOutPutStream:文件输出流
 */
public class StringOutPutToFile {
    public void write(String filename,String content){
        try {
            FileOutputStream fos=new FileOutputStream(filename);

            BufferedOutputStream bos=new BufferedOutputStream(fos);

            bos.write(content.getBytes(),0,content.getBytes().length);

            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}