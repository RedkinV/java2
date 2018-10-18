package ru.geekbrains.java2.lesson3_1_phone_book.variant1;

import java.util.HashMap;


public class MyPhoneBook extends HashMap<String, String> {

    public void add(String key, String newValue) {
        String value;
        if (this.containsKey(key)) {
            value=this.get(key);
            this.replace(key,value+", "+newValue);
        } else this.put(key, newValue);

    }




}
