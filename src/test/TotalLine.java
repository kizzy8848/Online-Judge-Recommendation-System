package test;

import java.lang.reflect.Array;

public class TotalLine {
    public static void main(String[] args) {

        String s="75+127+34+112+33+248+65+86+23+59+26+97+85+29+115+137+25+31+108+103+121+67+39+40+68+195+59+103+68+68+47+49+22+133+345+347+348+118";

        String[] a=s.split("\\+");

        int t=0;

        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]);
            t+=Integer.valueOf(a[i]);
        }
        System.out.println(t);
    }
}
