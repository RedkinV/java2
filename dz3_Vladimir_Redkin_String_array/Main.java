package ru.geekbrains.java2.lesson3;

import java.util.HashMap;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        String[] strArr=new String[]{"aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","aaa","bbb","ccc","aaa","bbb","ccc" };

        MyHashMap<String,Integer> hm= new MyHashMap<>();

        for (int i = 0; i <strArr.length ; i++) {
            Integer res=1;
            if(hm.containsKey(strArr[i])){
               hm.put(strArr[i], res+1);
           } else hm.put(strArr[i], res);
        }

        System.out.println(hm.toString());
    }


}
