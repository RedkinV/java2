package ru.geekbrains.java2.lesson3_1_phone_book.variant1;

public class Main {

    public static void main(String[] args) {

        MyPhoneBook contact=new MyPhoneBook();


        contact.add("Ivanov","111");
        contact.add("Petrov","22222");
        contact.add("Sidorov","44444");
        contact.add("Ivanov","333");
        contact.add("Ivanov","555555");

        System.out.println("Ivanov "+contact.get("Ivanov"));
        System.out.println("Petrov "+contact.get("Petrov"));
        System.out.println("Sidorov "+contact.get("Sidorov"));

    }



}
