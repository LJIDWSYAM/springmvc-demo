package liujun.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class zijie {
    String test="hello world";
    String test1="你好";
    String test3="a";


    public static void main(String[] args) throws UnsupportedEncodingException {
        zijie zijie=new zijie();
        System.out.println(Arrays.toString(zijie.test1.getBytes()));
    }
}
