package ru.geekbrains.java2.passwordCheck;

import java.util.Scanner;

public class MainPassCheck {

    public static void main(String[] args) {
        //String str="qSerdd8sdSSD";

        Scanner in=new Scanner(System.in);
        System.out.println("Введите логин: ");
        String log=in.nextLine();
        System.out.println("Введите пароль: ");
        String str=in.nextLine();

        System.out.println("Валидность пароль: "+isValidPass(str));

    }

    public static boolean isValidPass(String s){
        if(checkLenght(s)&&isAnyLowerCase(s)&&isAnyUpperCase(s)&&isAnyNumber(s)) return true;
        else return false;
    }

    public static boolean checkLenght(String str){
        if (str.length()<8) return false;
        else return true;
    }

    public static boolean isAnyUpperCase(String str){
        for (char c:str.toCharArray()) {
            if(Character.isLetter(c)&&Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
    public static boolean isAnyLowerCase(String str){
        for (char c:str.toCharArray()) {
            if(Character.isLetter(c)&&Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyNumber(String str){
        for (char c:str.toCharArray()) {
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

}
