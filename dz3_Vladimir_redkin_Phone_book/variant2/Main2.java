package ru.geekbrains.java2.lesson3_1_phone_book.variant2;

import java.util.HashMap;
import java.util.Iterator;

public class Main2 {

    public static void main(String[] args) {

        MyPhoneBook1<String,PhoneEntry> pb1=new MyPhoneBook1<String,PhoneEntry>();

        pb1.add("AAA",new PhoneEntry("111"));
        pb1.add("BBB",new PhoneEntry("222"));
        pb1.add("AAA",new PhoneEntry("333"));
        pb1.add("AAA",new PhoneEntry("444"));
        pb1.add("CCC",new PhoneEntry("555"));

        System.out.println("AAA"+" "+pb1.get("AAA"));
        System.out.println("BBB"+" "+pb1.get("BBB"));

        System.out.println();
        System.out.println("Вывод всей телефонной книги");

        Iterator it=pb1.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
