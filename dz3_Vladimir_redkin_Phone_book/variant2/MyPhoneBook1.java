package ru.geekbrains.java2.lesson3_1_phone_book.variant2;

import java.util.HashMap;
import java.util.Iterator;

public class MyPhoneBook1<K,V> extends HashMap<K,V> {

    public void add(K key,V newPhoneEntry){
        if(!(newPhoneEntry instanceof PhoneEntry)) throw new IllegalArgumentException("Value не соответвует типу phoneEntry ");

        if (this.containsKey(key)){
            PhoneEntry oldPhoneList=(PhoneEntry) this.get(key);

            Iterator it=((PhoneEntry) newPhoneEntry).getNumberList().iterator();
            while (it.hasNext()){
                oldPhoneList.addNumber((String) it.next());
            }
        }else  this.put(key,newPhoneEntry);

    }


}
