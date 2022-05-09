package Recommender.data;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * BufferedInputStream:缓冲输入流
 * FileInputStream:文件输入流
 */
public class FileReadToString {
    public  void read(String filename){
        try {
            FileInputStream fis=new FileInputStream(filename);
            BufferedInputStream bis=new BufferedInputStream(fis);
            String content=null;
            //自定义缓冲区
            byte[] buffer=new byte[10240];
            int flag=0;
            while((flag=bis.read(buffer))!=-1){
                content+=new String(buffer, 0, flag);
            }
            System.out.println(content);
            //关闭的时候只需要关闭最外层的流就行了
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}