package com.tank.springcloud.springbootclient.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtils {
    public static void main(String[] args) {
        String str = "ABCD";
        String str1 = "BCDE";
        String str2 = "CDEF";
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(str1);
        list.add(str2);
        List<String> strings = Arrays.asList(str, str1, str2);
        //System.out.printf(strings.get(0));
        strings.forEach(System.out::println);

        boolean flag = addNum(1,5);
        if(!flag){
            System.out.println("111");
            System.out.println(dataFlag("cd2") );
        }
        System.out.println("2222"+flag);
    }


    public static boolean addNum(int a, int b) {
        if (a + b == 5) {
            return true;
        }
        return false;
    }

    public static boolean dataFlag(String str){
        if(str.contains("abc")){
            return true ;
        }
        return false ;
    }


}
