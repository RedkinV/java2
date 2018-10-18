package ru.geekbrains.java2.lesson4_1;

import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_BodyIndex {
    public static void main(String[] args) {
        String str="118 2.05 \n 106 1.77\n 87 1.83\n 45 1.12\n 70 1.87\n 54 1.57\n 105 1.76 \n 50 1.96\n 114 1.76\n 72 2.45 \n 53 2.10\n 66 2.25 \n " +
                "54 1.50 \n 95 1.62\n 86 1.72\n 62 1.57\n 65 2.24\n 72 1.43\n 93 2.01\n 109 3.01\n 106 2.97\n 77 1.69\n 114 2.09\n 98 1.72\n 85 2.46\n"+
                "113 1.94\n 53 1.77\n 106 2.30\n";


        String pattern="\\D*((\\d+)\\D+(\\d+\\.+\\d+)).*\n";

        Pattern p=  Pattern.compile(pattern);
        Matcher m=p.matcher(str);


        while(m.find()) {
            System.out.println("Для пары: "+m.group(1)+" "+bodyIndex(m.group(2),m.group(3)));
        }
    }


    public static String bodyIndex(String w, String h){ // пока не знаю формулу индекса теля, поэтому пусть будет просто деление одного на другое
        float weight=Float.parseFloat(w); //проверка не нужна т.к. передаем точно цифры
        float height=Float.parseFloat(h);
        float res=weight/(height*height);

        if(res< 18.5 )  return "under";
        if(res>= 18.5&&res < 25.0)  return "norm";
        if(res>= 25&&res < 30)  return "over";
        if(res>= 30)  return "obese";
        return "за пределами разумного.";

    }
}
