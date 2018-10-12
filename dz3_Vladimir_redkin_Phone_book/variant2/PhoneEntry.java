package ru.geekbrains.java2.lesson3_1_phone_book.variant2;

import java.util.Iterator;
import java.util.LinkedList;

public class PhoneEntry {
    private LinkedList<String> numberList;

    public PhoneEntry(String newNumber) {
        numberList=new LinkedList<>();
        this.numberList.add(newNumber);
    }

    public LinkedList<String> getNumberList() {
        return numberList;
    }
    public void addNumber(String number){
        this.numberList.addLast(number);
    }
    public String getNumbers (){
        String result="";
        Iterator it=numberList.iterator();
        while(it.hasNext()){
            result=result+it.next()+", ";
        }
        return result;
    }


    @Override
    public String toString() {
        return "Numbers:"+ numberList;
    }
}
